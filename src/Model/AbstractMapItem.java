package src.Model;

public abstract class AbstractMapItem {
    private Coordinate coordinate;

    public AbstractMapItem(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {return coordinate;}
    public void setCoordinate(Coordinate coordinate) {this.coordinate = coordinate;}
}
