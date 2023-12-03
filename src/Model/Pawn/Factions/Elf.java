package src.Model.Pawn.Factions;

import src.Model.Equipment.Weapons.Bow;
import src.Model.Pawn.AbstractPlayer;

public class Elf extends AbstractPlayer {
    public Elf(String name) {
        super(name, 150, 40, 10, 2, 50);
        this.equipments.add(new Bow());
        this.equipmentSelected = this.equipments.get(0);
    }

    @Override
    public String getClassName() {
        return "elf";
    }
}
