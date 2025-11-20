import Factory.Vehicle;
import ParkingSpots.ParkingSpot;

import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> parkingSpots;

    public ParkingLot(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }

    public ParkingSpot findAvailableSpot(Vehicle vehicle){
        return parkingSpots.stream()
                .filter(p->p.getSpotType().equalsIgnoreCase(vehicle.getVehicleType()) && !p.isOccupied())
                .findFirst()
                .orElse(null);
    }

    public ParkingSpot parkVehicle(Vehicle vehicle){
        ParkingSpot parkingSpot = findAvailableSpot(vehicle);
        if(parkingSpot != null){
            parkingSpot.parkVehicle(vehicle);
            System.out.println(
                    "Vehicle parked successfully in spot: " + parkingSpot.getSpotNumber());
            return parkingSpot;
        }
        System.out.println(
                "No parking spots available for " + vehicle.getVehicleType() + "!");
        return null;
    }

    public void vacateSpot(ParkingSpot spot, Vehicle vehicle){
        if(spot != null && spot.isOccupied() && spot.getVehicle() == vehicle){
            spot.vacate();
            System.out.println(vehicle.getVehicleType()
                    + " vacated the spot: " + spot.getSpotNumber());
            return;
        }
        System.out.println("Invalid operation! Either the spot is already vacant "
                + "or the vehicle does not match.");
    }

    public ParkingSpot getSpotByNumber(int spotNumber){
        return parkingSpots.stream()
                .filter(p->p.getSpotNumber() == spotNumber)
                .findFirst()
                .orElse(null);
    }

    public List<ParkingSpot> getParkingSpots() {
        return parkingSpots;
    }

    public void setParkingSpots(List<ParkingSpot> parkingSpots) {
        this.parkingSpots = parkingSpots;
    }
}
