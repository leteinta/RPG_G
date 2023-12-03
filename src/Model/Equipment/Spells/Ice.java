package src.Model.Equipment.Spells;

public class Ice extends AbstractSpell {
    public Ice() {
        super(100, 20, 2, 8);
    }

    @Override
    public String getClassName() {
        return "ice";
    }
}
