package Carwash;

import java.util.ArrayList;
import Simulator.*;
import java.util.NoSuchElementException;

/**
 * @author Hugo Söderström, Alibn Rubinson
 * En klass som ska agera som kö för bilar om alla maskiner är fulla
 */

public class FIFO {
    static ArrayList<Event> carQueue = new ArrayList<Event>();

    /**
     * @return true om kön är tom
     */

    public static boolean isEmpty() {
        return carQueue.isEmpty();
    }

    /**
     * Lägger till ett event längs bak i kön
     * @param e
     */

    public static void add(Event e) {
        carQueue.add(e);
    }

    /**
     * En metod som tar bort elementet längst fram i kön och kan kasta ett felmeddelande.
     * @throws NoSuchElementException kastas om kön är tom.
     */

    public static void remove() throws NoSuchElementException {
        if (carQueue.isEmpty()) {
            throw new NoSuchElementException("Kön är tom");
        }
        carQueue.removeFirst();
    }

    /**
     * Hämtar första elementet i kön och kan kasta felmeddelande.
     * @return första elementet i kön.
     * @throws NoSuchElementException kastas om kön är tom.
     */

    public static Event get() throws NoSuchElementException {
        if (carQueue.isEmpty()) {
            throw new NoSuchElementException("Kön är tom");
        }
        return carQueue.getFirst();
    }

    /**
     * @return storleken på kön av typen "int".
     */

    public static int getSize() {
        return carQueue.size();
    }

    /**
     * @return maximala storleken på kön av typen "int".
     */

    public static int maximumSize(){
        return CarWashState.maxSizeOfQueue;
    }
}