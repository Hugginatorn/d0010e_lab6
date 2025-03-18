package Carwash;

import Simulator.*;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Klassen som ansvarar för vad som ska ske när en bil anländer i simulatorn
 */

public class CarArrivalEvent extends Event {
    public Car car;
    public static int numberOfCars;
    CarWashState CWstate;
    public double washTime;

    /**
     * Identifierar och håller koll på själva bilen genom att binda den till sig själv
     * @param sortedSequence
     * @param simState
     */

    public CarArrivalEvent(SortedSequence sortedSequence, SimState simState) {
        CWstate = (CarWashState) simState;
        this.car = CarFactory.createCar();
        time = CWstate.newEventTime();

    }

    /**
     * Kallas via Simulatorn när ett CarArrivalEvent står först i kön och åker in i maskinen.
     * @param sortedSequence används för att komma åt "sort" metoden som lägger till händelsen i "EventQueue"
     * @param simState används för att uppdatera "CarWashState" stadiet
     */

    public void Execute(SortedSequence sortedSequence, SimState simState) {
        CarWashState.onGoingEvent = "ARRIVE";
        sortedSequence.sort(new CarArrivalEvent(sortedSequence, simState));
        CWstate.updateQueueTime(this);

        if (CarWashState.fastAvailable()) {
            car.lastMaschine = "Fast";
            washTime = CWstate.getFastWashTime();
            sortedSequence.sort(new CarDepartureEvent(CWstate, car, time, washTime));
            CWstate.updateidleTime(this);
            CWstate.observable(this);
            CarWashState.availableFastMachines--;
        }

        else if (CarWashState.slowAvailable()) {
            car.lastMaschine = "Slow";
            washTime = CWstate.getSlowWashTime();
            sortedSequence.sort(new CarDepartureEvent(CWstate, car, time, washTime));
            CWstate.updateidleTime(this);
            CWstate.observable(this);
            CarWashState.availableSlowMachines--;
        }

        else if ((CarWashState.fastAvailable() == false && CarWashState.slowAvailable() == false) && FIFO.carQueue.size() < FIFO.maximumSize()) {
            car.lastMaschine = "FIFO";
            CWstate.observable(this);
            FIFO.add(new CarDepartureEvent(CWstate, car, time, washTime));
        }

        else {
            CWstate.observable(this);
            CarWashState.rejectedCars++;
        }
    }
}