package Strategy_Payment;

public class PaymentProcessor {
    public static void processPayment(double amount, PaymentStrategy strategy){
        strategy.processPayment(amount);
    }
}
