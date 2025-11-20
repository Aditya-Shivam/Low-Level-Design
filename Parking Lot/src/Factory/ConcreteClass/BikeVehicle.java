package Factory.ConcreteClass;

import Factory.Vehicle;
import Strategy_Fee.ParkingFeeStrategy;


public class BikeVehicle extends Vehicle {

    public BikeVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
