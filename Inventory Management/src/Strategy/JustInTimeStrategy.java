package Strategy;

import Factory.Product;

public class JustInTimeStrategy implements ReplenishmentStrategy{
    @Override
    public void replenish(Product product) {
        System.out.println("Applying Just-In-Time replenishment for " + product.getName());
    }
}
