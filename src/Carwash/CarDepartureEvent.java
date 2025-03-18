package Carwash;
import Simulator.*;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Klassen som anvsvarar för vad som händer när en bil ska lämna biltvätten
 */

public class CarDepartureEvent extends Event {
    CarWashState CWstate;
    public Car car;

    /**
     * Konstruktor som knyter samman "CarDepartureEvent" med "CarArrivalEvent" då den kallas i anländnings eventets
     * execute metod och skapar en avskeds kopia av tiden
     * @param simState stadiet där "CarArrivalEvent" körs.
     * @param car bilen som anländer
     * @param time tiden när bilen anlädde
     * @param washTime tiden det tog att tvätta bilen
     */

    public CarDepartureEvent(SimState simState, Car car, double time, double washTime) {
        this.car = car;
        this.time = time + washTime;
        CWstate = (CarWashState) simState;
    }

    /**
     * Kallat på i simulatorn när händelsen är först i kön. Beroende på vilken maskin den färdiga bilen kom ifrån så öppnas den upp
     * genom att öka värdet på den variabeln. Efter "CarDepartureEvent" så kommer första händelsen i FIFO:n att köras
     * @param sortedSequence används för att komma åt "sort" metoden som lägger till händelsen i "EventQueue"
     * @param simState används för att uppdatera "CarWashState" stadiet
     */

    public void Execute(SortedSequence sortedSequence, SimState simState) {
        CWstate = (CarWashState) simState;
        CarWashState.onGoingEvent = "LEAVE";
        CWstate.updateQueueTime(this);

        if (car.lastMaschine().equals("Fast")){
            CarWashState.availableFastMachines++;
            if (FIFO.isEmpty() == false){
                CarDepartureEvent headOfLine = (CarDepartureEvent) FIFO.get();
                FIFO.remove();
                headOfLine.time = this.time + CWstate.getFastWashTime();
                headOfLine.car.lastMaschine = "Fast";
                CarWashState.availableFastMachines--;
                sortedSequence.sort(headOfLine);
                CWstate.observable(this);
            }
        }
        if (car.lastMaschine().equals("Slow")){
            CarWashState.availableSlowMachines++;
            if (FIFO.isEmpty() == false){
                CarDepartureEvent headOfLine = (CarDepartureEvent) FIFO.get();
                FIFO.remove();
                headOfLine.time = this.time + CWstate.getSlowWashTime();
                headOfLine.car.lastMaschine = "Slow";
                CarWashState.availableSlowMachines--;
                sortedSequence.sort(headOfLine);
                CWstate.observable(this);
            }
        }
    }
}