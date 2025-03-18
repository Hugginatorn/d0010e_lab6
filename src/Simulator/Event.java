package Simulator;

/**
 * @author Hugo Söderström, Albin Rubinson
 * En abstrakt klass som extendas av varje event
 */

public abstract class Event {
    public double time;

    public abstract void Execute(SortedSequence sortedSequence, SimState simState);
}