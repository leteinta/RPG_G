package src.Controller;

import src.Model.Coordinate;
import src.Model.Equipment.AbstractEquipment;
import src.Model.Level.AbstractLevel;
import src.Model.Level.Level2;
import src.Model.Map;
import src.Model.Pawn.AbstractPlayer;
import src.Model.Store;
import src.Service.FactionFactory;
import src.Service.NotifyObserver;
import src.Service.Translator;
import src.View.Terminal.GameTerminal;
import src.View.Terminal.NotifyObserverTerminal;

import java.util.ArrayList;
import java.util.List;

public class TerminalController {
    private static Store store;
    private static final GameTerminal game = new GameTerminal();

    public static void main(String[] args) throws Exception {
        Translator.getInstance().setLanguage("fr");
        NotifyObserver.initInstance(new NotifyObserverTerminal());
        store = new Store();

        String namePlayer = game.getNamePlayer();
        String faction = game.getFaction();
        AbstractPlayer player = FactionFactory.createFaction(faction, namePlayer);

        AbstractLevel level = new Level2(player);
        boolean gameOver = false;
        while (!gameOver && level != null){
            boolean exitReached = false;
            while (!gameOver && !exitReached) {
                game.displayMap(level.getMap());
                action(level.getMap());
                gameOver = level.getMap().getPlayer().isDestroyed();
                exitReached = level.getMap().exitReached();
                if(!gameOver && !exitReached){
                    level.getMap().playMonsters();
                    level.getMap().getPlayer().endOfRound();
                    gameOver = level.getMap().getPlayer().isDestroyed();
                }
            }
            if(exitReached){
                NotifyObserver.notify("level-win", level.getNumber());
                level = level.nextLevel();
            }
        }
        if(level == null){
            game.win();
        }else{
            game.lost();
        }
    }

    private static void action(Map map) throws Exception {
        List<String> actions = new ArrayList<>();
        actions.add("move");
        actions.add("target");
        actions.add("store");
        actions.add("inventory");
        actions.add("end");
        String action = game.getAction(actions);
        switch (action) {
            case "move" -> {
                List<Coordinate> coordinates = map.getCoordinatesForMove(map.getPlayer(), true);
                if(coordinates.size() > 0){
                    map.moveOrganism(map.getPlayer(), coordinates, game.getCoordinate(coordinates));
                }else{
                    NotifyObserver.notify("nothing-coordinate");
                }
            }
            case "target" -> {
                List<Coordinate> coordinates = map.getCoordinatesForTarget(map.getPlayer());
                if(coordinates.size() > 0){
                    map.targetPawn(map.getPlayer(), coordinates, game.getCoordinate(coordinates));
                }else{
                    NotifyObserver.notify("nothing-coordinate");
                }
            }
            case "store" -> {
                AbstractEquipment equipment = game.buyEquipment(store.getEquipments(), map.getPlayer().getMoney());
                if(!map.getPlayer().buyEquipment(equipment)){
                    NotifyObserver.notify("terminal-store-not-enough-money");
                }
            }
            case "inventory" -> map.getPlayer().setEquipmentSelected(game.selectEquipment(map.getPlayer()));
            default -> throw new Exception("Action is unknown!");
        }
    }
}
