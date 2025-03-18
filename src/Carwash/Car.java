package Carwash;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Denna klass skapar ett "car" objekt med ett individuelt ID.
 */

public class Car {
    private int carID;
    String lastMaschine = "";

    /**
     * Car konstruktor som skapar car objekten med ett ID.
     * @param carID
     */

    public Car(int carID){
        this.carID = carID;
    }

    /**
     * @return car objektens ID
     */

    public int carID(){
        return carID;
    }

    /**
     * Håller koll på vilken den senaste använda maskinen var
     * @return vilken maskin bilen lämna
     */

    public String lastMaschine(){
        return lastMaschine;
    }
}
