package src.Model.Equipment.Weapons;

public class Hammer extends AbstractMeleeWeapon {
    public Hammer() {
        super(150, 25, 1, 150);
    }

    @Override
    public String getClassName() {
        return "hammer";
    }
}
