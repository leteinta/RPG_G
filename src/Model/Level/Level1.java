package src.Model.Level;

import src.Model.Coordinate;
import src.Model.Exit;
import src.Model.Map;
import src.Model.Pawn.AbstractPlayer;

import java.util.HashMap;
import java.util.List;

public class Level1 extends AbstractLevel{
    public Level1(AbstractPlayer player) throws Exception {
        super(1, player);
    }

    protected Map initMap(AbstractPlayer player) {
        player.setCoordinate(new Coordinate(0, 0));
        return new Map(6, 4, player, new Exit(new Coordinate(5,3)));
    }

    protected java.util.Map<Integer, List<Integer>> getCoordinateWalls() {
        java.util.Map<Integer, List<Integer>> coordinates = new HashMap<>();
        coordinates.put(0, List.of(2, 4, 5));
        coordinates.put(1, List.of(0, 2));
        coordinates.put(2, List.of(2, 4));
        coordinates.put(3, List.of(0, 4));
        return coordinates;
    }

    protected void buildMonsters() {}

    protected void buildObstacles() throws Exception {
        this.buildItems(8, "barrel");
    }

    public AbstractLevel nextLevel() throws Exception {
        return new Level2(map.getPlayer());
    }
}
