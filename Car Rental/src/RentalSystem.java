import Factory.Vehicle;
import Strategy.PaymentProcessor;
import Strategy.PaymentStrategy;
import UtilityClasses.RentalStore;
import UtilityClasses.Reservation;
import UtilityClasses.ReservationManager;
import UtilityClasses.User;

import java.util.*;

public class RentalSystem {
    private static RentalSystem instance;
    private Map<Integer, User> users;
    private ReservationManager reservationManager;
    private PaymentProcessor paymentProcessor;
    private List<RentalStore> stores;
    private int nextUserId;

    private RentalSystem() {
        users = new HashMap<>();
        reservationManager = new ReservationManager();
        paymentProcessor = new PaymentProcessor();
        stores = new ArrayList<>();
        nextUserId = 1;
    }

    public static synchronized RentalSystem getInstance(){
        if(instance == null) instance = new RentalSystem();
        return instance;
    }

    public void addStore(RentalStore rentalStore){
        stores.add(rentalStore);
    }

    public RentalStore getStore(int id){
        return stores.stream()
                .filter(r->r.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<RentalStore> getStores() {
        return stores;
    }

    public User getUser(int id){
        return users.get(id);
    }

    public Reservation createReservation(int userId, String vehicleNumber, int pickupStoreId, int returnStoreId,
                                         Date startDate, Date endDate){
        User user = users.get(userId);
        RentalStore pickupStore = getStore(pickupStoreId);
        RentalStore returnStore = getStore(returnStoreId);
        Vehicle vehicle = pickupStore == null ? null : pickupStore.getVehicle(vehicleNumber);
        if(user != null && vehicle != null && returnStore != null){
            return reservationManager.createReservation(user,vehicle,pickupStore,returnStore,startDate,endDate);
        }
        return null;
    }

    public boolean processPayment(int id, PaymentStrategy paymentStrategy){
        Reservation reservation = reservationManager.getReservation(id);
        if(reservation != null){
            double amount = reservation.getAmount();
            if(paymentProcessor.processPayment(amount,paymentStrategy)){
                reservationManager.confirmReservation(reservation.getId());
                return true;
            }
        }
        return false;
    }

    public void startRental(int id){
        reservationManager.startRental(id);
    }

    public void completeRental(int id){
        reservationManager.completeRental(id);
    }

    public void cancelRental(int id){
        reservationManager.cancelReservation(id);
    }

    public void registerUser(User user){
        int userId = user.getId();
        if(users.containsKey(userId)){
            System.out.println("User with id : " + userId + "Already exists in the system");
            return;
        }
        users.put(userId,user);
    }
}
