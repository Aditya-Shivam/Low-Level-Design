package UtilityClasses;

import CommonEnums.VehicleStatus;
import CommonEnums.VehicleType;
import Factory.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RentalStore {
    private int id;
    private String name;
    private Location location;
    private Map<String, Vehicle> vehicles; // Registration number -> Vehicles

    public RentalStore(int id, String name, Location location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.vehicles = new HashMap<>();
    }

    public List<Vehicle> getAvailableVehicles(){
        return vehicles.values().stream()
                .filter(v->v.getStatus() == VehicleStatus.AVAILABLE)
                .toList();
    }

    public void addVehicle(Vehicle vehicle){
        vehicles.put(vehicle.getRegistrationNumber(), vehicle);
    }

    public void removeVehicle(String registrationNumber){
        vehicles.remove(registrationNumber);
    }

    public boolean isVehicleAvailable(String registrationNumber){
        Vehicle vehicle = vehicles.get(registrationNumber);
        return vehicle != null && vehicle.getStatus() == VehicleStatus.AVAILABLE;
    }

    public Vehicle getVehicle(String registrationNumber){
        return vehicles.get(registrationNumber);
    }
    public Map<String,Vehicle> getAllVehicles(){
        return vehicles;
    }

    public int getId() {
        return id;
    }
}
