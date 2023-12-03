package src.Model.Pawn.Factions;

import src.Model.Equipment.Weapons.Axe;
import src.Model.Pawn.AbstractPlayer;

public class Warrior extends AbstractPlayer {
    public Warrior(String name) {
        super(name, 180, 20, 15, 1, 30);
        this.equipments.add(new Axe());
        this.equipmentSelected = this.equipments.get(0);
    }

    @Override
    public String getClassName() {
        return "warrior";
    }
}
