package src.Model.Level;

import src.Model.*;
import src.Model.Pawn.AbstractPawn;
import src.Model.Pawn.AbstractPlayer;
import src.Model.Pawn.Monsters.Tarantula;
import src.Model.Pawn.Monsters.Anaconda;
import src.Model.Pawn.Obstacles.Barrel;
import src.Service.NotifyObserver;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

public abstract class AbstractLevel {
    private final int number;
    protected final Map map;
    protected final java.util.Map<String, AbstractMapItem> items = new HashMap<>();

    public AbstractLevel(int number, AbstractPlayer player) throws Exception {
        this.number = number;
        this.map = this.initMap(player);
        this.items.put(player.getCoordinate().toString(), player);
        this.items.put(this.map.getExit().getCoordinate().toString(), this.map.getExit());
        this.buildWalls();
        this.buildMonsters();
        this.buildObstacles();
        this.map.initialize(this.items);
        NotifyObserver.notify("level-start", number);
    }

    public int getNumber() {return number;}

    public Map getMap() {
        return map;
    }

    protected void buildWalls(){
        for(java.util.Map.Entry<Integer, List<Integer>> coordinateEntry: this.getCoordinateWalls().entrySet()){
            for(Integer x: coordinateEntry.getValue()){
                Coordinate coordinate = new Coordinate(x, coordinateEntry.getKey());
                this.items.put(coordinate.toString(), new Wall(coordinate));
            }
        }
    }

    protected void buildItems(int numberOfItems, String typePawn) throws Exception {
        Random random = new Random();
        for (int i = 0; i < numberOfItems; i++) {
            Coordinate coordinate;
            do{
                coordinate = new Coordinate(random.nextInt(this.map.getGrid()[0].length), random.nextInt(this.map.getGrid().length));
            } while (this.items.containsKey(coordinate.toString()));
            AbstractPawn pawn = this.createPawn(typePawn, coordinate);
            pawn.setName(pawn.getName()+" "+(i+1));
            this.items.put(coordinate.toString(), pawn);
        }
    }

    private AbstractPawn createPawn(String type, Coordinate coordinate) throws Exception {
        return switch (type) {
            case "barrel" -> new Barrel(coordinate);
            case "tarantula" -> new Tarantula(coordinate);
            case "anaconda" -> new Anaconda(coordinate);
            default -> throw new Exception("Type of pawn unknown!");
        };
    }

    protected abstract Map initMap(AbstractPlayer player);
    protected abstract java.util.Map<Integer, List<Integer>> getCoordinateWalls();
    protected abstract void buildMonsters() throws Exception;
    protected abstract void buildObstacles() throws Exception;

    public abstract AbstractLevel nextLevel() throws Exception;
}
