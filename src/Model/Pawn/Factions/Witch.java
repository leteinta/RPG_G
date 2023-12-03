package src.Model.Pawn.Factions;

import src.Model.Pawn.AbstractPlayer;
import src.Model.Equipment.Spells.Fire;

public class Witch extends AbstractPlayer {
    public Witch(String name) {
        super(name, 120, 60, 10, 1, 30);
        this.equipments.add(new Fire());
        this.equipmentSelected = this.equipments.get(0);
    }

    @Override
    public String getClassName() {
        return "witch";
    }
}
