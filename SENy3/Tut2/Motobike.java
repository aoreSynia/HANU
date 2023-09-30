package Tut2;

import Tut1.Exercise3.Vehicle;

public class Motobike extends Vehicle {
      
    private boolean hasSideCar;

    public Motobike(String name, double width, double height, double length, double weight, int seatingCapacity,
            String registrationNumber) {
        super(name, width, height, length, weight, seatingCapacity, registrationNumber);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected String getVehicleSymbol() {
        return "M";
    }

    public boolean hasSideCar() {
        return hasSideCar;
    }

    public void setHasSideCar(boolean hasSideCar) {
        this.hasSideCar = hasSideCar;
    }

    @Override
    public String toString() {
        return super.toString() + " hasSideCar=" + hasSideCar;
    }
}
