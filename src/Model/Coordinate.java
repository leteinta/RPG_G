package src.Model;

public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate() {
        this(0, 0);
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public String toString(){
        return this.x+"|"+this.y;
    }
}
