package src.Model.Equipment.Weapons;

import src.Model.Equipment.AbstractEquipment;

public abstract class AbstractProjectileWeapon extends AbstractEquipment {
    private int ammo;
    private final int ammoMax;

    public AbstractProjectileWeapon(int price, int damage, int range, int ammoMax) {
        super(price, damage, range);
        this.ammo = ammoMax;
        this.ammoMax = ammoMax;
    }

    public int getAmmo() {return ammo;}
    public void decrementAmmo() {this.ammo--;}

    public int getAmmoMax() {return ammoMax;}
}
