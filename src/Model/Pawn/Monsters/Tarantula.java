package src.Model.Pawn.Monsters;

import src.Model.Coordinate;

public class Tarantula extends AbstractMonster {
    public Tarantula(Coordinate coordinate) {
        super(coordinate, 250, 20, 1, 20, 10, 1, 20);
    }

    @Override
    public String getClassName() {
        return "tarantula";
    }
}
