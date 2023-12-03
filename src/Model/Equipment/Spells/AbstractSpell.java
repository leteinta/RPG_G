package src.Model.Equipment.Spells;

import src.Model.Equipment.AbstractEquipment;

public abstract class AbstractSpell extends AbstractEquipment {
    private final int magicPointConsumption;

    public AbstractSpell(int price, int damage, int range, int magicPointConsumption) {
        super(price, damage, range);
        this.magicPointConsumption = magicPointConsumption;
    }

    public int getMagicPointConsumption() {return magicPointConsumption;}
}
