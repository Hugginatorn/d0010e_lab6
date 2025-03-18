package Carwash;

import Simulator.*;

/**
 * @author Hugo Söderström, Albin Rubinson
 * En klass som sköter stop eventen av simulationen.
 */

public class Stop extends Event{
    public Stop(){
        time = 15.00;
    }

    /**
     * Denna metod kallas när simulationen ska avslutas.
     * @param sortedSequence
     * @param simState
     */

    public void Execute(SortedSequence sortedSequence, SimState simState){
        CarWashState.onGoingEvent = "STOP";
        simState.setSimulatorRunning(false);
        CarWashState CWstate = (CarWashState) simState;
        CWstate.updateQueueTime(this);
        CWstate.observable(this);
    }
}
