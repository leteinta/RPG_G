package src.Model.Equipment.Spells;

public class Fire extends AbstractSpell {
    public Fire() {
        super(80, 10, 3, 5);
    }

    @Override
    public String getClassName() {
        return "fire";
    }
}
