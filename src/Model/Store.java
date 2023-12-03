package src.Model;

import src.Model.Equipment.AbstractEquipment;
import src.Model.Equipment.Spells.Fire;
import src.Model.Equipment.Spells.Ice;
import src.Model.Equipment.Weapons.Axe;
import src.Model.Equipment.Weapons.Bow;
import src.Model.Equipment.Weapons.Hammer;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<AbstractEquipment> equipments;

    public Store() {
        this.equipments = new ArrayList<>();
        this.equipments.add(new Bow());
        this.equipments.add(new Hammer());
        this.equipments.add(new Axe());
        this.equipments.add(new Fire());
        this.equipments.add(new Ice());
    }

    public List<AbstractEquipment> getEquipments() {return equipments;}
}
