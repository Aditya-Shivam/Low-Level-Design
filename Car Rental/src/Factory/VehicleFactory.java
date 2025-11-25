package Factory;

import CommonEnums.VehicleType;
import Factory.ConcreteClasses.EconomyVehicle;
import Factory.ConcreteClasses.LuxuryVehicle;
import Factory.ConcreteClasses.SUVVehicle;

public class VehicleFactory {
    public static Vehicle createVehicle(VehicleType vehicleType, String registrationNumber, String model,  double baseRentalPrice){
        switch (vehicleType){
            case SUV -> {
                return new SUVVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            }
            case LUXURY -> {
                return new LuxuryVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            }
            case ECONOMY -> {
                return new EconomyVehicle(registrationNumber, model, vehicleType, baseRentalPrice);
            }
            default -> throw new IllegalArgumentException("Unsupported Vehicle Type: " + vehicleType);
        }
    }
}
