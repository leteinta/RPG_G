package src.Model.Pawn.Monsters;

import src.Model.Coordinate;
import src.Model.Pawn.AbstractOrganism;
import src.Service.Translator;

public abstract class AbstractMonster extends AbstractOrganism {
    public AbstractMonster(Coordinate coordinate, int healthPointMax, int experiencePointMax, int level, int magicPointMax, int strength, int moveSpeed, int money) {
        super(coordinate, "", healthPointMax, level, experiencePointMax, magicPointMax, strength, moveSpeed, money);
        this.name = Translator.getInstance().getLanguage().getText("pawn-"+getClassName());
    }
}
