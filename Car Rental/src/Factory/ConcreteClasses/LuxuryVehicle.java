package Factory.ConcreteClasses;

import CommonEnums.VehicleType;
import Factory.Vehicle;

public class LuxuryVehicle extends Vehicle {
    private static final double RATE_MULTIPLIER = 2.5;
    private static final double PREMIUM_FEE = 50.0;
    public LuxuryVehicle(String registrationNumber, String model, VehicleType type, double baseRentalPrice) {
        super(registrationNumber, model, type, baseRentalPrice);
    }

    @Override
    public double calculateRentalFee(int days) {
        return (getBaseRentalPrice() * RATE_MULTIPLIER * days) + PREMIUM_FEE ;
    }
}
