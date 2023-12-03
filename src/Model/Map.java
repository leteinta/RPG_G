package src.Model;

import src.Model.Pawn.*;
import src.Model.Pawn.Monsters.AbstractMonster;
import src.Service.NotifyObserver;

import java.util.*;

public class Map {
    private final AbstractMapItem[][] grid;
    private final AbstractPlayer player;
    private final List<AbstractMonster> monsters = new ArrayList<>();
    private final Exit exit;

    public Map(int gridSizeX, int gridSizeY, AbstractPlayer player, Exit exit) {
        this.grid = new AbstractMapItem[gridSizeY][gridSizeX];
        this.player = player;
        this.exit = exit;
    }

    public AbstractMapItem[][] getGrid() {return grid;}

    public AbstractPlayer getPlayer() {return player;}

    public Exit getExit() {return exit;}

    public void initialize(java.util.Map<String, AbstractMapItem> items) throws Exception {
        for(AbstractMapItem item: items.values()) {
            if(grid[item.getCoordinate().getY()][item.getCoordinate().getX()] != null){
                throw new Exception("This cell '"+item.getCoordinate()+"' is already used!");
            }
            grid[item.getCoordinate().getY()][item.getCoordinate().getX()] = item;
            if(item instanceof AbstractMonster monster){
                this.monsters.add(monster);
            }
        }
    }

    public List<Coordinate> getCoordinatesForMove(AbstractOrganism organism, boolean isPlayer) {
        java.util.Map<String, Coordinate> possibleCoordinates = new HashMap<>();
        this.coordinatesForMove(possibleCoordinates, organism.getCoordinate(), organism.getMoveSpeed(), isPlayer);
        return new ArrayList<>(possibleCoordinates.values());
    }
    
    private void coordinatesForMove(java.util.Map<String, Coordinate> possibleCoordinates, Coordinate coordinate, int distance, boolean isPlayer){
        if(distance > 0){
            int x = coordinate.getX();
            int y = coordinate.getY();
            Coordinate coordinateMove;
            if(x-1 >= 0 && (this.grid[y][x-1] == null || (this.grid[y][x-1] instanceof Exit && isPlayer))){
                coordinateMove = new Coordinate(x-1, y);
                possibleCoordinates.put(coordinateMove.toString(), coordinateMove);
                this.coordinatesForMove(possibleCoordinates, coordinateMove, distance-1, isPlayer);
            }
            if(x+1 < this.grid[0].length && (this.grid[y][x+1] == null || (this.grid[y][x+1] instanceof Exit && isPlayer))){
                coordinateMove = new Coordinate(x+1, y);
                possibleCoordinates.put(coordinateMove.toString(), coordinateMove);
                this.coordinatesForMove(possibleCoordinates, coordinateMove, distance-1, isPlayer);
            }
            if(y-1 >= 0 && (this.grid[y-1][x] == null || (this.grid[y-1][x] instanceof Exit && isPlayer))){
                coordinateMove = new Coordinate(x, y-1);
                possibleCoordinates.put(coordinateMove.toString(), coordinateMove);
                this.coordinatesForMove(possibleCoordinates, coordinateMove, distance-1, isPlayer);
            }
            if(y+1 < this.grid.length && (this.grid[y+1][x] == null || (this.grid[y+1][x] instanceof Exit && isPlayer))){
                coordinateMove = new Coordinate(x, y+1);
                possibleCoordinates.put(coordinateMove.toString(), coordinateMove);
                this.coordinatesForMove(possibleCoordinates, coordinateMove, distance-1, isPlayer);
            }
        }
    }

    public List<Coordinate> getCoordinatesForTarget(AbstractOrganism organism) {
        java.util.Map<String, Coordinate> possibleCoordinates = new HashMap<>();
        if(organism instanceof AbstractPlayer){
            possibleCoordinates.put(organism.getCoordinate().toString(), organism.getCoordinate());
        }
        this.coordinatesForTarget(possibleCoordinates, organism, organism.getCoordinate(), organism.getEquipmentSelected().getRange());
        return new ArrayList<>(possibleCoordinates.values());
    }

    private void coordinatesForTarget(java.util.Map<String, Coordinate> possibleCoordinates, AbstractOrganism organism, Coordinate coordinate, int range) {
        if(range > 0){
            int x = coordinate.getX();
            int y = coordinate.getY();
            Coordinate coordinateTarget;
            if(x-1 >= 0 && (this.grid[y][x-1] instanceof AbstractPawn || this.grid[y][x-1] == null)){
                coordinateTarget = new Coordinate(x-1, y);
                if(this.grid[y][x-1] instanceof AbstractPawn && (organism instanceof AbstractPlayer || organism.getClass() != this.grid[y][x-1].getClass())){
                    possibleCoordinates.put(coordinateTarget.toString(), coordinateTarget);
                }
                this.coordinatesForTarget(possibleCoordinates, organism, coordinateTarget, range-1);
            }
            if(x+1 < this.grid[0].length && (this.grid[y][x+1] instanceof AbstractPawn || this.grid[y][x+1] == null)){
                coordinateTarget = new Coordinate(x+1, y);
                if(this.grid[y][x+1] instanceof AbstractPawn && (organism instanceof AbstractPlayer || organism.getClass() != this.grid[y][x+1].getClass())){
                    possibleCoordinates.put(coordinateTarget.toString(), coordinateTarget);
                }
                this.coordinatesForTarget(possibleCoordinates, organism, coordinateTarget, range-1);
            }
            if(y-1 >= 0 && (this.grid[y-1][x] instanceof AbstractPawn || this.grid[y-1][x] == null)){
                coordinateTarget = new Coordinate(x, y-1);
                if(this.grid[y-1][x] instanceof AbstractPawn && (organism instanceof AbstractPlayer || organism.getClass() != this.grid[y-1][x].getClass())){
                    possibleCoordinates.put(coordinateTarget.toString(), coordinateTarget);
                }
                this.coordinatesForTarget(possibleCoordinates, organism, coordinateTarget, range-1);
            }
            if(y+1 < this.grid.length && (this.grid[y+1][x] instanceof AbstractPawn || this.grid[y+1][x] == null)){
                coordinateTarget = new Coordinate(x, y+1);
                if(this.grid[y+1][x] instanceof AbstractPawn && (organism instanceof AbstractPlayer || organism.getClass() != this.grid[y+1][x].getClass())){
                    possibleCoordinates.put(coordinateTarget.toString(), coordinateTarget);
                }
                this.coordinatesForTarget(possibleCoordinates, organism, coordinateTarget, range-1);
            }
        }
    }

    public void moveOrganism(AbstractOrganism organism, List<Coordinate> possibleCoordinates, Coordinate coordinate) throws Exception {
        if (possibleCoordinates.contains(coordinate)) {
            grid[organism.getCoordinate().getY()][organism.getCoordinate().getX()] = null;
            organism.setCoordinate(coordinate);
            grid[coordinate.getY()][coordinate.getX()] = organism;
        }else{
            throw new Exception("Invalid coordinate!");
        }
    }

    public void targetPawn(AbstractOrganism organism, List<Coordinate> possibleCoordinates, Coordinate coordinate) throws Exception {
        if (possibleCoordinates.contains(coordinate)) {
            AbstractPawn pawn = (AbstractPawn) grid[coordinate.getY()][coordinate.getX()];
            if(organism.target(pawn)){
                NotifyObserver.notify("attack-destroy", organism.getName(), pawn.getName());
                grid[coordinate.getY()][coordinate.getX()] = null;
                if(pawn instanceof AbstractMonster monster){
                    this.monsters.remove(monster);
                }
            }
        }else{
            throw new Exception("Invalid coordinate!");
        }
    }

    public void playMonsters() throws Exception {
        for(int i=0; i<this.monsters.size(); i++){
            AbstractMonster monster = this.monsters.get(i);
            SplittableRandom random = new SplittableRandom();
            if(random.nextInt(2) == 1){
                List<Coordinate> coordinates = this.getCoordinatesForTarget(monster);
                if(coordinates.size() > 0){
                    this.targetPawn(monster, coordinates, coordinates.get(random.nextInt(coordinates.size())));
                }
            }else if(random.nextInt(2) == 1){
                List<Coordinate> coordinates = this.getCoordinatesForMove(monster, false);
                if(coordinates.size() > 0){
                    this.moveOrganism(monster, coordinates, coordinates.get(random.nextInt(coordinates.size())));
                }
            }
            monster.endOfRound();
            if(monster.isDestroyed()){
                this.grid[monster.getCoordinate().getY()][monster.getCoordinate().getX()] = null;
                this.monsters.remove(monster);
            }
        }
    }

    public boolean exitReached(){
        return this.player.getCoordinate().toString().equals(this.exit.getCoordinate().toString());
    }
}


