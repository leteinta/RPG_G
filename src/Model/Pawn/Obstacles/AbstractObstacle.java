package src.Model.Pawn.Obstacles;

import src.Model.Coordinate;
import src.Model.Pawn.AbstractPawn;
import src.Service.Translator;

public abstract class AbstractObstacle extends AbstractPawn {
    public AbstractObstacle(Coordinate coordinate, int healthPointMax, int level) {
        super(coordinate, "",  healthPointMax, level);
        this.name = Translator.getInstance().getLanguage().getText("pawn-"+getClassName());
    }
}
