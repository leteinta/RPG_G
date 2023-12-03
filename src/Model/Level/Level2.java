package src.Model.Level;

import src.Model.*;
import src.Model.Pawn.AbstractPlayer;

import java.util.HashMap;
import java.util.List;

public class Level2 extends AbstractLevel{
    public Level2(AbstractPlayer player) throws Exception {
        super(2, player);
    }

    protected Map initMap(AbstractPlayer player) {
        player.setCoordinate(new Coordinate(0, 0));
        return new Map(10, 5, player, new Exit(new Coordinate(9,4)));
    }

    protected java.util.Map<Integer, List<Integer>> getCoordinateWalls() {
        java.util.Map<Integer, List<Integer>> coordinates = new HashMap<>();
        coordinates.put(0, List.of(1, 5));
        coordinates.put(1, List.of(3, 5, 8));
        coordinates.put(2, List.of(0, 2, 7));
        coordinates.put(3, List.of(3, 6, 9));
        coordinates.put(4, List.of(1, 5));
        return coordinates;
    }

    protected void buildMonsters() throws Exception {
        this.buildItems(6, "tarantula");
    }

    protected void buildObstacles() throws Exception {
        this.buildItems(10, "barrel");
    }

    public AbstractLevel nextLevel() throws Exception {
        return new Level3(map.getPlayer());
    }
}
