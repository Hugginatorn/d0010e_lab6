package Carwash;
import Simulator.*;

/**
 * @author Hugo Söderström, Alibn Rubinson
 * En klass som sköter start eventen av simulationen.
 */

public class Start extends Event {
    public Start(SimState simState) {
        time = 0.00;
    }

    /**
     * Startar simulationen och skapar nästa "CarArrivalEvent" event.
     * @param sortedSequence
     * @param simState
     */

    public void Execute(SortedSequence sortedSequence, SimState simState) {
        CarWashState.onGoingEvent = "START";
        simState.setSimulatorRunning(true);
        sortedSequence.sort(new CarArrivalEvent(sortedSequence, simState));
        simState.observable(this);
    }
}
