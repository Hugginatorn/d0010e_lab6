package Carwash;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Håller koll på hur många bilar som skapats, där "carNumber" är static för att den används i att skapa ett unikt ID för
 * respektive bil.
 */

public class CarFactory {
    private static int carNumber = -1;

    /**
     * Håller koll på hur många bilar som finns i simuleringen och använder den privata klass variabeln samt numberOfCars som
     * inkrementeras vid varje anrop till metoden.
     * @return skapar ett nytt Car objekt och returnerar det.
     */

    public static Car createCar() {
        carNumber++;
        return new Car(carNumber);
    }

    /**
     * @return antalet bilar som skapats
     */

    public static int numberOfCars() {
        return carNumber;
    }
}
