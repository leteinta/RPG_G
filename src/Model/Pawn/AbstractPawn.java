package src.Model.Pawn;

import src.Model.AbstractMapItem;
import src.Model.Coordinate;

public abstract class AbstractPawn extends AbstractMapItem {
    protected String name;
    protected int healthPoint;
    protected int healthPointMax;
    protected int level;

    public AbstractPawn(Coordinate coordinate, String name, int healthPointMax, int level) {
        super(coordinate);
        this.name = name;
        this.healthPoint = healthPointMax;
        this.healthPointMax = healthPointMax;
        this.level = level;
    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getHealthPoint() {return healthPoint;}
    public void decreaseHealthPoint(int healthPoint) {
        this.healthPoint -= healthPoint;
    }
    public void increaseHealthPoint(int healthPoint) {
        this.healthPoint += healthPoint;
        if(this.healthPoint > this.healthPointMax){
            this.healthPoint = this.healthPointMax;
        }
    }

    public int getHealthPointMax() {return healthPointMax;}
    public void increaseHealthPointMax(int healthPointMax) {
        this.healthPointMax += healthPointMax;
    }

    public int getLevel() {return level;}
    public void incrementLevel() {
        this.level++;
    }

    public abstract String getClassName();

    public boolean isDestroyed(){
        return this.healthPoint <= 0;
    }
}
