package src.Model.Equipment.Weapons;

public class Bow extends AbstractProjectileWeapon {
    public Bow() {
        super(50, 10, 2, 15);
    }

    @Override
    public String getClassName() {
        return "bow";
    }
}
