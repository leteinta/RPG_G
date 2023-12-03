package src.Model.Equipment;

import src.Model.Pawn.AbstractOrganism;
import src.Service.Translator;

public abstract class AbstractEquipment {
    private final String name;
    private final int price;
    private final int damage;
    private final int range;

    public AbstractEquipment(int price, int damage, int range) {
        this.name = Translator.getInstance().getLanguage().getText("equipment-"+this.getClassName());
        this.price = price;
        this.damage = damage;
        this.range = range;
    }

    public String getName() {return name;}

    public int getPrice() {return price;}

    public int getDamage() {return damage;}

    public int getRange() {return range;}

    public abstract String getClassName();

    public void effect(AbstractOrganism organism) throws Exception {}
}
