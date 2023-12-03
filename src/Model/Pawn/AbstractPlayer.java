package src.Model.Pawn;

import src.Model.Coordinate;
import src.Model.Equipment.AbstractEquipment;
import src.Service.NotifyObserver;

public abstract class AbstractPlayer extends AbstractOrganism {
    public AbstractPlayer(String name, int healthPointMax, int magicPointMax, int strength, int moveSpeed, int money) {
        super(new Coordinate(), name, healthPointMax, 1, 20, magicPointMax, strength, moveSpeed, money);
    }

    public boolean buyEquipment(AbstractEquipment equipment) {
        if(equipment.getPrice() <= this.money) {
            this.equipments.add(equipment);
            this.equipmentSelected = equipment;
            this.decreaseMoney(equipment.getPrice());
            NotifyObserver.notify("store-success-bought", equipment.getName());
            return true;
        } else {
            return false;
        }
    }
}