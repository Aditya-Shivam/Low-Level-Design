package Factory.ConcreteClasses;

import CommonEnums.VehicleType;
import Factory.Vehicle;

public class SUVVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 1.5;
    public SUVVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return getBaseRentalPrice() * RATE_MULTIPLIER * days;
    }
}
