package Carwash;

import Simulator.*;
import Random.*;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Håller koll på vilket läge som biltvätten befinner sig i.
 */

public class CarWashState extends SimState {
    private static int totalFastMashines = 2;
    private static int totalSlowMashines = 2;
    static int availableFastMachines = 2;
    static int availableSlowMachines = 2;

    static double distributionFastLower = 2.8;
    static double distributionFastUpper = 4.6;
    static double distributionSlowLower = 3.5;
    static double distributionSlowUpper = 6.7;

    static int seed = 1234;
    static double lambda = 2.0;

    static int maxSizeOfQueue = 5;
    static int rejectedCars = 0;
    static String onGoingEvent = "";

    static double queueTime = 0;
    static double idleTime = 0;
    private double prevQueueTime = 0;
    private double prevCurrentTime = 0;

    private UniformRandomStream slowMachineTime = new UniformRandomStream(distributionSlowLower,distributionSlowUpper,seed);
    private UniformRandomStream fastMachineTime  = new UniformRandomStream(distributionFastLower,distributionFastUpper,seed);
    private ExponentialRandomStream timeToNextCarArrival = new ExponentialRandomStream(lambda,seed);

    static double currentTime = 0.00;

    /**
     * Kallas varje gång ett nytt event läggs till för att tilldela den en tid.
     * @return ankomsttiden
     */

    double newEventTime() {
        currentTime += timeToNextCarArrival.next();
        return currentTime;
    }

    /**
     * @return tiden det tog i den snabba maskinen.
     */

    double getFastWashTime() {
        return fastMachineTime.next();
    }

    /**
     * @return tiden det tog i den långsamma maskinen.
     */

    double getSlowWashTime(){
        return slowMachineTime.next();
    }

    /**
     * Uppdaterar tiden som går till spillo, alltså när en maskin är ledig och simulatorn tuggar på
     * @param e eventen som behandlas
     */

    void updateidleTime(Event e){
        idleTime += (e.time - prevCurrentTime) * (availableFastMachines + availableSlowMachines);
        prevCurrentTime = e.time;
    }

    /**
     * Uppdaterar kötiden i FIFO, alltså innan de åkt in i maskinerna
     * @param e
     */

    void updateQueueTime(Event e){
        queueTime += (e.time - prevQueueTime) * FIFO.carQueue.size();
        prevQueueTime = e.time;
    }

    /**
     * Kontrollerar om det finns någon snabb maskin ledig. Annars får den gå till FIFO kön eller bli avvisad.
     * @return true om det finns en ledig
     */

    static boolean fastAvailable(){
        return availableFastMachines > 0;
    }

    /**
     * Kontrollerar om det finns någon långsam maskin ledig. Annars får den gå till FIFO kön eller bli avvisad.
     * @return true om det finns en ledig
     */

    static boolean slowAvailable(){
        return availableSlowMachines > 0;
    }

    /**
     * @return mängden snabba maskiner som finns i simuleringen
     */

    static int getTotalFastMachines(){
        return totalFastMashines;
    }

    /**
     * @return mängden långsamma maskiner som finns i simuleringen
     */

    static int getTotalSlowMachines(){
        return totalSlowMashines;
    }

    /**
     * @return mängden bilar som blivit avvisade från simuleringen pga att FIFO kön är full.
     */

    static int rejectedCars(){
        return rejectedCars;
    }
}