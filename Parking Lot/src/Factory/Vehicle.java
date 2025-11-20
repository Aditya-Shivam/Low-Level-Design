package Factory;

import CommonEnums.DurationType;
import Strategy_Fee.ParkingFeeStrategy;


public abstract class Vehicle {
    private String licensePlate;
    private String vehicleType;
    private ParkingFeeStrategy feeStrategy;

    public Vehicle(String licensePlate, String vehicleType, ParkingFeeStrategy feeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.feeStrategy = feeStrategy;
    }

    public double calculateFee(int duration, DurationType durationType){
        return feeStrategy.calculateFee(vehicleType,duration,durationType);
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingFeeStrategy getFeeStrategy() {
        return feeStrategy;
    }

    public void setFeeStrategy(ParkingFeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }
}
