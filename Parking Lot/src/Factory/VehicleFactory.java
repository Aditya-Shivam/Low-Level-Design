package Factory;

import Factory.ConcreteClass.BikeVehicle;
import Factory.ConcreteClass.CarVehicle;
import Factory.ConcreteClass.OtherVehicle;
import Strategy_Fee.ParkingFeeStrategy;


public class VehicleFactory {
    public static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy){
        switch (vehicleType){
            case "Bike" -> {
                return new BikeVehicle(licensePlate, vehicleType, feeStrategy);
            }
            case "Car" -> {
                return new CarVehicle(licensePlate, vehicleType, feeStrategy);
            }
            default -> {
                return new OtherVehicle(licensePlate, vehicleType, feeStrategy);
            }
        }

    }
}
