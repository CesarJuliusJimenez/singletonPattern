package singletonPattern;
import java.util.*;
public class QueuingSystemApp {

    private static void displayQueue(List<QueuingSystem> queuingSystems) {
        for (QueuingSystem helpDesk : queuingSystems) {
            int currentQNumber = helpDesk.getCurrentQNumber();
            if (currentQNumber > 0) {
                System.out.println(helpDesk.getStation() + " is serving queue number: " + currentQNumber);
            }
        }
    }

    private static int randomGenerator() {
        return new Random().nextInt(3) + 1; // Random number between 1 and 3
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            QueuingSystem queuingSystem = QueuingSystem.getSystem();

            // Create a list to hold QueuingSystem instances
            List<QueuingSystem> stationNum = new ArrayList<>();

            // Add station and pass it to the QueuingSystem class.
            QueuingSystem station1 = new QueuingSystem("Help Desk Station 1");
            QueuingSystem station2 = new QueuingSystem("Help Desk Station 2");
            QueuingSystem station3 = new QueuingSystem("Help Desk Station 3");
            stationNum.add(station1);
            stationNum.add(station2);
            stationNum.add(station3);

            // Printing of simulation of pag-ibig office
            System.out.println("==================== Welcome to Pag-Ibig Office ====================");
            System.out.print("How many individuals entered?: ");
            int custCount = sc.nextInt();

            System.out.println();
            System.out.println("Generating individuals random queue number...");
            System.out.println();

            // Print how many individuals did the user input
            for (int a = 0; a < custCount; a++) {
                int stationIndex = randomGenerator() - 1; // Adjust the index to start from 0
                queuingSystem = stationNum.get(stationIndex);
                int queueNumber = queuingSystem.nextCustomer(); // Increment the station's queue number

                System.out.println("Person " + (a + 1) + " needs help at " + queuingSystem.getStation() + " [Queue #" + queueNumber + "]");
            }

            // Real-Time Display Monitor
            System.out.println();
            System.out.println("==================== Display Monitor ====================");
            displayQueue(stationNum);

            // For loop to ask if station wants to change its queue number
            for (QueuingSystem currentStation : stationNum) {
                System.out.println();
                System.out.print("Does " + currentStation.getStation() + " want to reset their queue number? (Y/N): ");
                char resetChoice = sc.next().charAt(0);
                if (resetChoice == 'Y' || resetChoice == 'y') {
                    System.out.print("Reset to: ");
                    int newQueueNumber = sc.nextInt();
                    if (newQueueNumber <= currentStation.getCurrentQNumber()) {
                        currentStation.resetQueue(newQueueNumber);
                    } else {
                        System.out.println("Only choose from the given queue number to the individuals!");
                    }
                } else if (resetChoice == 'N' || resetChoice == 'n') {
                    System.out.println("The queue number for " + currentStation.getStation() + " remains the same.");
                }
            }

            // Updated Real-Time Display Monitor after reset of each station
            System.out.println();
            System.out.println("==================== Updated Display Monitor ====================");
            displayQueue(stationNum);

            System.out.println();
            System.out.print("Do you want to run the program again? (Y/N): ");
            String userInput = sc.next().toLowerCase();
            if (userInput.equals("n")) {
                System.out.println("Terminating program...");
                System.exit(0);
            } else {
                System.out.println();
            }
        }
    }
}