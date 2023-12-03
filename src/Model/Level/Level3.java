package src.Model.Level;

import src.Model.Coordinate;
import src.Model.Exit;
import src.Model.Map;
import src.Model.Pawn.AbstractPlayer;

import java.util.HashMap;
import java.util.List;

public class Level3 extends AbstractLevel{
    public Level3(AbstractPlayer player) throws Exception {
        super(2, player);
    }

    protected Map initMap(AbstractPlayer player) {
        player.setCoordinate(new Coordinate(0, 6));
        return new Map(15, 7, player, new Exit(new Coordinate(14,0)));
    }

    protected java.util.Map<Integer, List<Integer>> getCoordinateWalls() {
        java.util.Map<Integer, List<Integer>> coordinates = new HashMap<>();
        coordinates.put(0, List.of(1, 5, 10));
        coordinates.put(1, List.of(3, 5, 8, 10, 12, 13));
        coordinates.put(2, List.of(0, 2, 7, 10, 12, 13));
        coordinates.put(3, List.of(3, 6, 9));
        coordinates.put(4, List.of(1, 5, 11, 13));
        coordinates.put(5, List.of(1, 4, 5, 7, 8, 10, 13));
        coordinates.put(6, List.of(1, 2, 7, 12));
        return coordinates;
    }

    protected void buildMonsters() throws Exception {
        this.buildItems(10, "tarantula");
        this.buildItems(5, "anaconda");
    }

    protected void buildObstacles() throws Exception {
        this.buildItems(10, "barrel");
    }

    public AbstractLevel nextLevel() {
        return null;
    }
}
