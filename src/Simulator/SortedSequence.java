package Simulator;

/**
 * @author Hugo Söderström, Albin Rubinson
 * En klass som tar emot event och sorterar dem efter vilken tid de har. Storeringen går efter tid och bygger kön efter det.
 */

public class SortedSequence {
    EventQueue eventQueue;
    public SortedSequence(EventQueue eventQueue) {
        this.eventQueue = eventQueue;
    }

    /**
     * Sorterar eventen efter dess tid.
     * @param event
     */

    public void sort(Event event){
        if (eventQueue.getEvents().size() == 0){
            eventQueue.getEvents().add(event);
        }
        else{
            for (int i = 0 ; i < eventQueue.getEvents().size() ; i++){
                if ((event.time) < eventQueue.getEvents().get(i).time){
                    eventQueue.getEvents().add(i,event);
                    break;
                }
                else if (i == eventQueue.getEvents().size()-1){
                    eventQueue.getEvents().add(event);
                    break;
                }
            }
        }
    }
}
