package Simulator;

import java.util.Observer;

/**
 * @author Hugo Söderström, Albin Rubinson
 * En abstrakt klass som implementerar "Observer". Den skapar metoder som "CarWashView" kan extenda för att hålla koll på "SimState"
 */

public abstract class SimView implements Observer {
    protected SimState simState;
    public SimView(SimState simState) {
        this.simState = simState;
    }

    public abstract void startPrint();
    public abstract void stopPrint();
}
