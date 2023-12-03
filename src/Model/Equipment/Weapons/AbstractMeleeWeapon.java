package src.Model.Equipment.Weapons;

import src.Model.Equipment.AbstractEquipment;

public abstract class AbstractMeleeWeapon extends AbstractEquipment {
    private int resistance;
    private final int resistanceMax;

    public AbstractMeleeWeapon(int price, int damage, int range, int resistanceMax) {
        super(price, damage, range);
        this.resistance = resistanceMax;
        this.resistanceMax = resistanceMax;
    }

    public int getResistance() {return resistance;}
    public void decreaseResistance(int resistance) {this.resistance -= resistance;}

    public int getResistanceMax() {return resistanceMax;}
}
