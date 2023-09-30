package Tut2;

import Tut1.Exercise3.Vehicle;
    
public class IronSuit extends Vehicle {
    private double flightDistance;
    private boolean occupied;

    public IronSuit(String name, double width, double height, double length, double weight,
                    int seatingCapacity, String registrationNumber, double flightDistance) {
        super(name, width, height, length, weight, seatingCapacity, registrationNumber);
        this.flightDistance = flightDistance;
        this.occupied = false; // Initialize as not occupied
    }

    public void fly(String startPoint, String endPoint, boolean useProgressBar) {
        if (isOccupied()) {
            System.out.println("IronSuit is occupied. Cannot take off.");
            return;
        }

        System.out.print("Taking off from " + startPoint + " ");
        int totalDots = 30;
            try {
                Thread.sleep(300); // Pause for 0.3 second
                System.out.print("===>");
            } catch (InterruptedException e) {
                // Ignore exception
            }
        
        System.out.println(" " + endPoint);

        if (useProgressBar) {
            System.out.print(startPoint + " ");
            for (int i = 0; i < totalDots; i++) {
                try {
                    Thread.sleep(300); // Pause for 0.3 second
                    System.out.print(".");
                } catch (InterruptedException e) {
                    // Ignore exception
                }
            }
            System.out.println(" " + endPoint);
            System.out.println("-- Arrived --");
        }
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}
