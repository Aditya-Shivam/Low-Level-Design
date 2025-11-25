package Strategy.ConcreteClasses;

import Strategy.PaymentStrategy;

public class CashPayment implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cash payment of $" + amount);
    }
}
