package Simulator;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Instansmetoder samt en "run()" metod som startar hela simulatorn genom att antopa metoderna från respektive klasser. I
 * helhet ansvarar Simulatorn för att kalla varje events execute metod.
 */

public class Simulator {
    private SimState simState;
    private SimView simView;
    private EventQueue eventQueue;
    private SortedSequence sortedSequence;

    /**
     * Tar följande parametrar
     * @param sortedSequence
     * @param simState
     * @param simView
     * @param eventQueue
     */

    public Simulator(SortedSequence sortedSequence, SimState simState, SimView simView, EventQueue eventQueue) {
        this.sortedSequence = sortedSequence;
        this.simState = simState;
        this.simView = simView;
        this.eventQueue = eventQueue;
    }

    /**
     * Anropas från Main metoden och kommer se till att så länge Simstates kör variabel "simulatorRunning" är true kommer
     * metoden att gå igenom EventQueue och anropa vardera events execute. Annars anropas "stopPrint" som sköter slututskriften.
     */

    public void run(){
        simView.startPrint();
        while(simState.simulatorIsRunning() == true){
            Event onGoingEvent = eventQueue.getInitialEvent();
            onGoingEvent.Execute(sortedSequence, simState);
        }
        simView.stopPrint();
    }
}
