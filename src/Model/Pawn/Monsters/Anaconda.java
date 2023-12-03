package src.Model.Pawn.Monsters;

import src.Model.Coordinate;

public class Anaconda extends AbstractMonster {
    public Anaconda(Coordinate coordinate) {
        super(coordinate, 350, 40, 2, 30, 30, 2, 50);
    }

    @Override
    public String getClassName() {
        return "anaconda";
    }
}
