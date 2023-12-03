package src.Model.Pawn;

import src.Model.Coordinate;
import src.Model.Equipment.Hand;
import src.Model.Equipment.Weapons.AbstractProjectileWeapon;
import src.Model.Equipment.Weapons.AbstractMeleeWeapon;
import src.Model.Equipment.Spells.AbstractSpell;
import src.Model.Equipment.AbstractEquipment;
import src.Service.NotifyObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOrganism extends AbstractPawn {
    private int experiencePoint;
    private int experiencePointMax;
    private int magicPoint;
    private int magicPointMax;
    private int strength;
    private final int moveSpeed;
    protected int money;
    protected AbstractEquipment equipmentSelected;
    protected final List<AbstractEquipment> equipments = new ArrayList<>();

    public AbstractOrganism(Coordinate coordinate, String name, int healthPointMax, int level, int experiencePointMax, int magicPointMax, int strength, int moveSpeed, int money) {
        super(coordinate, name, healthPointMax, level);
        this.experiencePoint = 0;
        this.experiencePointMax = experiencePointMax;
        this.magicPointMax = magicPointMax;
        this.magicPoint = magicPointMax;
        this.strength = strength;
        this.moveSpeed = moveSpeed;
        this.money = money;
        this.equipmentSelected = new Hand();
    }

    public int getExperiencePoint() {return experiencePoint;}
    public void increaseExperiencePoint(int experiencePoint) {this.experiencePoint += experiencePoint;}
    public void decreaseExperiencePoint(int experiencePoint) {this.experiencePoint -= experiencePoint;}

    public int getExperiencePointMax() {return experiencePointMax;}

    public int getMagicPoint() {return magicPoint;}
    public void increaseMagicPoint(int magicPoint) {this.magicPoint += magicPoint;}
    public void decreaseMagicPoint(int magicPoint) {this.magicPoint -= magicPoint;}

    public int getMagicPointMax() {return magicPointMax;}
    public void increaseMagicPointMax(int magicPointMax) {
        this.magicPointMax += magicPointMax;
        if(this.magicPoint > this.magicPointMax){
            this.magicPoint = this.magicPointMax;
        }
    }

    public int getStrength() {return strength;}
    public void increaseStrength(int strength) {this.strength += strength;}
    public void decreaseStrength(int strength) {this.strength -= strength;}

    public int getMoveSpeed() {return moveSpeed;}

    public int getMoney() {return money;}
    public void increaseMoney(int money) {this.money += money;}
    public void decreaseMoney(int money) {this.money -= money;}

    public AbstractEquipment getEquipmentSelected() {return equipmentSelected;}
    public void setEquipmentSelected(AbstractEquipment equipmentSelected) {this.equipmentSelected = equipmentSelected;}

    public List<AbstractEquipment> getEquipments() {return equipments;}

    public void endOfRound(){}

    public boolean target(AbstractPawn pawn) throws Exception {
        int damage = this.equipmentSelected.getDamage()*this.strength;
        boolean removeEquipment = false;
        if(this.equipmentSelected instanceof AbstractSpell spell){
            if(spell.getMagicPointConsumption() < this.magicPoint){
                this.decreaseMagicPoint(spell.getMagicPointConsumption());
            }else{
                this.equipmentSelected = new Hand();
                NotifyObserver.notify("attack-not-enough-magic-point", this.name);
            }
        }else if(this.equipmentSelected instanceof AbstractProjectileWeapon projectileWeapon){
            projectileWeapon.decrementAmmo();
            if(projectileWeapon.getAmmo() == 0){
                removeEquipment = true;
            }
        }else if(this.equipmentSelected instanceof AbstractMeleeWeapon meleeWeapon){
            meleeWeapon.decreaseResistance(this.equipmentSelected.getDamage());
            if(meleeWeapon.getResistance() <= 0){
                removeEquipment = true;
            }
        }
        pawn.decreaseHealthPoint(damage);
        if(pawn instanceof AbstractOrganism){
            this.equipmentSelected.effect((AbstractOrganism) pawn);
        }
        if(damage > 0){
            NotifyObserver.notify("attack-health-point", this.name, damage, pawn.getName());
        }
        if(removeEquipment){
            NotifyObserver.notify("attack-equipment-lost", this.name, this.equipmentSelected.getName());
            this.equipments.remove(this.equipmentSelected);
            this.equipmentSelected = new Hand();
        }
        if(pawn.isDestroyed()){
            this.increaseExperiencePoint((int)Math.pow(pawn.getLevel()+1, 3));
            while(this.experiencePoint >= this.experiencePointMax){
                this.decreaseExperiencePoint(this.experiencePointMax);
                this.experiencePointMax = this.experiencePointMax * 2;
                this.incrementLevel();
                this.increaseStrength(2);
                this.increaseHealthPointMax(100);
                this.increaseMagicPointMax(15);
                this.healthPoint = this.healthPointMax;
                this.magicPoint = magicPointMax;
                NotifyObserver.notify("attack-new-level", this.name, this.level);
            }
            if(pawn instanceof AbstractOrganism){
                this.increaseMoney(((AbstractOrganism) pawn).getMoney());
            }
            return true;
        }else{
            return false;
        }
    }
}
