package Strategy_Fee;

import CommonEnums.DurationType;

public class PremiumRateStrategy implements ParkingFeeStrategy {
    @Override
    public double calculateFee(String vehicleType, int duration, DurationType durationType) {
        return switch (vehicleType.toLowerCase()) {
            case "car" -> durationType == DurationType.DAYS ? duration * 15.0 * 24 :
                    duration * 15.0;
            case "bike" -> durationType == DurationType.DAYS ? duration * 8.0 * 24:
                    duration * 8.0;
            default -> durationType == DurationType.DAYS ? duration * 20.0 * 24:
                    duration * 20.0;
        };
    }
}
