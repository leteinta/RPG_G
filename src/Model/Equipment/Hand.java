package src.Model.Equipment;

public class Hand extends AbstractEquipment {
    public Hand() {
        super(0, 5, 1);
    }

    @Override
    public String getClassName() {
        return "hand";
    }
}
