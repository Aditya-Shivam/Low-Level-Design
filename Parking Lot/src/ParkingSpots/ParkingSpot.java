package ParkingSpots;

import Factory.Vehicle;

public abstract class ParkingSpot {
    private int spotNumber;
    private String spotType;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot(int spotNumber, String spotType) {
        this.spotNumber = spotNumber;
        this.spotType = spotType;
        this.isOccupied = false;
        this.vehicle = null;
    }

    public abstract boolean canParkVehicle(Vehicle vehicle);

    public void parkVehicle(Vehicle vehicle){
        if(isOccupied){
            throw new IllegalStateException("Spot is already occupied.");
        }

        if(!canParkVehicle(vehicle)){
            throw new IllegalArgumentException("This spot is not suitable for" + vehicle.getVehicleType());
        }

        this.vehicle = vehicle;
        this.isOccupied = true;
    }

    public void vacate(){
        if(!isOccupied) throw new IllegalStateException("Spot is already vacant");
        this.vehicle = null;
        this.isOccupied = false;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public String getSpotType() {
        return spotType;
    }

    public void setSpotType(String spotType) {
        this.spotType = spotType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
