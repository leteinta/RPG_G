package src.Model.Pawn.Obstacles;

import src.Model.Coordinate;

public class Barrel extends AbstractObstacle {
    public Barrel(Coordinate coordinate) {
        super(coordinate,  70, 1);
    }

    @Override
    public String getClassName() {
        return "barrel";
    }
}
