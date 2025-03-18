package Carwash;
import Simulator.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Observable;

/**
 * @author Hugo Söderström, Albin Rubinson
 * Denna klass sköter alla utskrifter. Först har vi den initsiella utskriften för när simuleringen börjar. Sedan har vi en metod
 * "update" som sköter allt som händer under simuleringen för att slutligen avsluta med metoden "stopPrint" som summerar allt som
 * skett under simuleringen.
 */

public class CarWashView extends SimView{
    CarWashState CWstate;
    Car car;

    /**
     * Klass konstruktor som skapar en CarWashView och sammankopplar den till simState
     * @param simState håller koll på vart i simuleringen vi befinner oss
     */

    public CarWashView(SimState simState) {
        super(simState);
    }

    /**
     * Den initiala utskriften av biltvätten. Metoden kallas från simulatorn.
     */

    public void startPrint(){
        System.out.println("Fast machines: " + CarWashState.getTotalFastMachines());
        System.out.println("Slow machines: " + CarWashState.getTotalSlowMachines());
        System.out.println("Fast Distribution: (" + CarWashState.distributionFastLower + "," + CarWashState.distributionFastUpper + ")");
        System.out.println("Slow Distribution: (" + CarWashState.distributionSlowLower + "," + CarWashState.distributionSlowUpper + ")");
        System.out.println("Exponential distribution with lambda = " + CarWashState.lambda);
        System.out.println("seed = " + CarWashState.seed);
        System.out.println("Max Queue size: "+ CarWashState.maxSizeOfQueue);
        System.out.println("----------------------------------------------------");
        System.out.printf("%4s %9s %9s %7s %11s %13s %14s %14s %13s\n","Time","Event","Id","Fast","Slow","IdleTime","QueueTime","QueueSize","Rejected");
    }

    /**
     * Denna metod kallas varje gång vi anropar notifyObservers(). I varje Execute så kommer metoden kallas och ge utskrift av
     * händelseförloppet. Metoden är även ansvarig för formatet av utskriften.
     * @param observable     the observable object.
     * @param object   an argument passed to the {@code notifyObservers}
     *                 method.
     */

    public void update(Observable observable, Object object) {
        NumberFormat nf = new DecimalFormat("#0.00");
        Event temporary = (Event) object;
        if (temporary instanceof Start){
            System.out.printf("%-5s %8s\n",
                    nf.format(temporary.time),
                    "Start",
                    CarWashState.availableFastMachines,
                    CarWashState.availableSlowMachines,
                    "",
                    nf.format(CarWashState.idleTime),
                    nf.format(CarWashState.queueTime),
                    FIFO.getSize(),
                    CarWashState.rejectedCars());
        }

        if (temporary instanceof Stop){
            System.out.printf("%-5s %8s %9s %7s %11s %11.4s %14s %12s %12s\n",
                    nf.format(temporary.time),
                    "Stop",
                    CarWashState.availableSlowMachines,
                    CarWashState.availableFastMachines,
                    "",
                    nf.format(CarWashState.idleTime),
                    nf.format(CarWashState.queueTime),
                    FIFO.getSize(),
                    CarWashState.rejectedCars());
        }

        if (temporary instanceof CarArrivalEvent){
            CarArrivalEvent arrival = (CarArrivalEvent) temporary;
            System.out.printf("%-5s %8s %9s %7s %11s %11.4s %14s %12s %12s\n",
                    nf.format(temporary.time),
                    "Arrive",
                    arrival.car.carID(),
                    CarWashState.availableFastMachines,
                    CarWashState.availableSlowMachines,
                    nf.format(CarWashState.idleTime),
                    nf.format(CarWashState.queueTime),
                    FIFO.getSize(),
                    CarWashState.rejectedCars());
        }

        if (temporary instanceof CarDepartureEvent){
            CarDepartureEvent departure = (CarDepartureEvent) temporary;
            System.out.printf("%-5s %8s %9s %7s %11s %11.4s %14s %12s %12s\n",
                    nf.format(temporary.time),
                    "Leave",
                    departure.car.carID(),
                    CarWashState.availableFastMachines,
                    CarWashState.availableSlowMachines,
                    nf.format(CarWashState.idleTime),
                    nf.format(CarWashState.queueTime),
                    FIFO.getSize(), CarWashState.rejectedCars());
        }
    }

    /**
     * Den slutgiltiga utskriften som summerar allt som skett och skriver ut dem.
     */

    public void stopPrint(){
        NumberFormat nf = new DecimalFormat("#0.00");
        System.out.println("----------------------------------------------------");
        System.out.println("Total idle machine time: " + nf.format(CarWashState.idleTime));
        System.out.println("Total queueing time: " + nf.format(CarWashState.queueTime));
        System.out.println("Mean queueing time: " + nf.format(CarWashState.queueTime/(CarFactory.numberOfCars() - CarWashState.rejectedCars())));	//rejected ?
        System.out.println("Rejected cars: " + CarWashState.rejectedCars());
    }
}