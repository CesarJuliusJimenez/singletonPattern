package singletonPattern;
public class QueuingSystem {
    private static QueuingSystem instance;
    private int queueNumber;
    private String station;

    // Private constructor to prevent instantiation outside of this class
    public QueuingSystem(String station) {
        this.station = station;
        this.queueNumber = 0;
    }

    public static synchronized QueuingSystem getSystem() {
        if (instance == null)
            instance = new QueuingSystem("Default");
        return instance;
    }

    public int nextCustomer() {
        return ++queueNumber;
    }

    public void resetQueue(int newQueueNumber) {
        this.queueNumber = newQueueNumber;
        System.out.println("The queue number for " + this.getStation() + " has been reset to " + newQueueNumber);
    }

    public String getStation() {
        return station;
    }

    public int getCurrentQNumber() {
        return queueNumber;
    }
}