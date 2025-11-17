package Strategy;

import Factory.Product;

public class BulkOrderStrategy implements ReplenishmentStrategy{
    @Override
    public void replenish(Product product) {
        System.out.println("Applying Bulk Order replenishment for " + product.getName());
    }
}
