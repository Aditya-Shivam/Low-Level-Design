package UtilityClasses;

import CommonEnums.ReservationStatus;
import CommonEnums.VehicleStatus;
import CommonEnums.VehicleType;
import Factory.Vehicle;

import java.util.Date;

public class Reservation {
    private int id;
    private User user;
    private Vehicle vehicle;
    private RentalStore pickupStore;
    private RentalStore returnStore;
    private Date startDate;
    private Date endDate;
    private ReservationStatus status;
    private double amount;

    public Reservation(int id, User user, Vehicle vehicle, RentalStore pickupStore, RentalStore returnStore, Date startDate, Date endDate) {
        this.id = id;
        this.user = user;
        this.vehicle = vehicle;
        this.pickupStore = pickupStore;
        this.returnStore = returnStore;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = ReservationStatus.PENDING;
        long millis = endDate.getTime() - startDate.getTime();
        int days = (int) (millis / (1000 * 60 * 60 * 60)) + 1;
        this.amount = vehicle.calculateRentalFee(days);
    }

    public void confirmReservation(){
        if(status == ReservationStatus.PENDING){
            status = ReservationStatus.CONFIRMED;
            vehicle.setStatus(VehicleStatus.RESERVED);
        }
    }
    public void startRental(){
        if(status == ReservationStatus.CONFIRMED){
            status = ReservationStatus.IN_PROGRESS;
            vehicle.setStatus(VehicleStatus.RENTED);
        }
    }

    public void completeRental(){
        if(status == ReservationStatus.IN_PROGRESS){
            status =  ReservationStatus.COMPLETED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public void cancelReservation(){
        if(status == ReservationStatus.CONFIRMED || status == ReservationStatus.PENDING){
            status = ReservationStatus.CANCELLED;
            vehicle.setStatus(VehicleStatus.AVAILABLE);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", user=" + user +
                ", vehicle=" + vehicle +
                ", pickupStore=" + pickupStore +
                ", returnStore=" + returnStore +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
