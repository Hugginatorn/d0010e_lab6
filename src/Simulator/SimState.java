package Simulator;

import java.util.Observable;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Håller koll på vilket stadie simulatorn befinner sig i.
 */

public class SimState extends Observable {
    private static boolean simulatorRunning = true;

    /**
     * @return true om Simulatorn är i gång
     */

    public boolean simulatorIsRunning() {
        return simulatorRunning;
    }

    /**
     * Sätter värdet på "simulatorRunning" variabeln.
     * @param b
     */

    public void setSimulatorRunning(boolean b) {
        simulatorRunning = b;
    }

    /**
     * Denna kallas från varje event i flödes ordningen och när något av stadierna har ändras så är denna metod ansvarig för att
     * säga till view att det sker.
     * @param event
     */

    public void observable(Event event) {
        setChanged();
        notifyObservers(event);
    }
}