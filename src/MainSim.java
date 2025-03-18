import Simulator.*;
import Carwash.*;

public class MainSim {
    public static void main(String[] args) {
        EventQueue eventQueue = new EventQueue();
        SortedSequence sortedSequence = new SortedSequence(eventQueue);
        CarWashState CWstate = new CarWashState();
        SimState simState = new SimState();
        CarWashView CWview = new CarWashView(simState);
        CWstate.addObserver(CWview);
        sortedSequence.sort(new Stop());
        sortedSequence.sort(new Start(CWstate));
        Simulator s = new Simulator(sortedSequence, CWstate, CWview, eventQueue);
        s.run();
    }
}