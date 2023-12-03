package src.Model.Equipment.Weapons;

public class Axe extends AbstractMeleeWeapon {
    public Axe() {
        super(100, 15, 1, 100);
    }

    @Override
    public String getClassName() {
        return "axe";
    }
}
