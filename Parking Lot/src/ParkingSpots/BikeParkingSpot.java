package ParkingSpots;

import Factory.ConcreteClass.BikeVehicle;
import Factory.Vehicle;

public class BikeParkingSpot extends ParkingSpot{
    public BikeParkingSpot(int spotNumber,String spotType){
        super(spotNumber,spotType);
    }

    @Override
    public boolean canParkVehicle(Vehicle vehicle){
        return "Bike".equalsIgnoreCase(vehicle.getVehicleType());
    }
}
