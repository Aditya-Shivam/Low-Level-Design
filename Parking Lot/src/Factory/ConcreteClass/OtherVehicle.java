package Factory.ConcreteClass;

import Factory.Vehicle;
import Strategy_Fee.ParkingFeeStrategy;

public class OtherVehicle extends Vehicle {
    public OtherVehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        super(licensePlate, vehicleType, feeStrategy);
    }
}
