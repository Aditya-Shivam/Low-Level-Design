package Strategy.ConcreteClasses;

import Strategy.PaymentStrategy;

public class PaypalPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}
