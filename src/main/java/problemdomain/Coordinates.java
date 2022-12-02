package problemdomain;
import java.util.Objects;

public class Coordinates {
    private final int x;
    private final int y;

    // constructor assigns passed values to x and y
    public Coordinates(int x1, int y1){
        x = x1;
        y = y1;
    }

    // getter methods

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    //NEEDS COMMENTS
    @Override
    public boolean equals(Object obj){

        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Coordinates newCoords = (Coordinates) obj;
        boolean isEqual = (x == newCoords.x && y == newCoords.y);
        return isEqual;
    }

    @Override
    public int hashCode(){
        return Objects.hash(x,y); //returns the hash??????
    }

}
