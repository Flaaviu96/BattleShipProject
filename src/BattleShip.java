

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BattleShip {
    private final String name;
    private final int lenghtOfShip;
    private int counter = 0;
    private boolean isSunked;
    private List<Coordinates> coordinatesList;
     BattleShip(String name, int lenghtOfShip) {
        this.name = name;
        this.lenghtOfShip = lenghtOfShip;
        coordinatesList = new ArrayList<>();
    }
    void addCoordinates(Coordinates coordinates){
         coordinatesList.add(coordinates);
    }
    List<Coordinates> getCoordinatesList() {
         return coordinatesList;
    }
    void incrementSizeOfShip(){
         counter+=1;
    }
    int getCounter() {
         return counter;
    }
    int getLenghtOfShip() {
         return  lenghtOfShip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BattleShip that = (BattleShip) o;
        return lenghtOfShip == that.lenghtOfShip && counter == that.counter && isSunked == that.isSunked && Objects.equals(name, that.name) && Objects.equals(coordinatesList, that.coordinatesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lenghtOfShip, counter, isSunked, coordinatesList);
    }

    boolean isSunked() {
         return isSunked;
    }
    String getName() {
         return name;
    }
    void setSunked(boolean isSunked) {
         this.isSunked = isSunked;
    }
}
