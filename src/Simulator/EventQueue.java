package Simulator;

import java.util.ArrayList;

/**
 * @author Hugo Söderström, Albin Rubinson
 * En kö som skapas av en Array med typen events som håller koll på vad som sker i simulatorn.
 */

public class EventQueue {
    ArrayList<Event> events = new ArrayList<Event>();

    /**
     * @return hela Array.
     */

    public ArrayList<Event> getEvents(){
        return events;
    }

    /**
     * Denna kallas från simulatorn för att tala om vilken händelse som står på tur i kö.
     * @return första elementet i kön
     */

    public Event getInitialEvent() {
        Event temporaryEvent = events.get(0);
        events.remove(0);
        return temporaryEvent;
    }

    /**
     * En metod för att lägga till event i händelsekön.
     * @param event
     */

    public void addEvent(Event event) {
        events.add(event);
    }
}
