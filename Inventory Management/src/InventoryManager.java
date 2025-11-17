import Factory.Product;
import Factory.ProductFactory;
import Strategy.ReplenishmentStrategy;
import UtlityClasses.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class InventoryManager {
    private static InventoryManager instance;

    private ReplenishmentStrategy replenishmentStrategy;
    private List<Warehouse> warehouses;
    private ProductFactory productFactory;

    private InventoryManager(ReplenishmentStrategy replenishmentStrategy){
        this.replenishmentStrategy = replenishmentStrategy;
        warehouses = new ArrayList<>();
        productFactory = new ProductFactory();
    }

    public static synchronized InventoryManager getInstance(ReplenishmentStrategy replenishmentStrategy){
        if(instance == null){
            instance = new InventoryManager(replenishmentStrategy);
        }
        return instance;
    }

    public void setReplenishmentStrategy(ReplenishmentStrategy replenishmentStrategy) {
        this.replenishmentStrategy = replenishmentStrategy;
    }

    public void addWarehouse(Warehouse warehouse){
        warehouses.add(warehouse);
    }

    public void removeWareHouse(Warehouse warehouse){
        warehouses.remove(warehouse);
    }

    public Product getProductBySku(String sku){
       for(Warehouse warehouse : warehouses){
           Product product = warehouse.getProductBySku(sku);
           if(product != null) return product;
       }
       return null;
    }

    public void checkAndReplenish(String sku){
        Product product = getProductBySku(sku);
        if(product == null) return;
        if(product.getQuantity() < product.getThreshold()) replenishmentStrategy.replenish(product);
    }

    public void performInventoryCheck(){

        for(Warehouse warehouse : warehouses){
            for(Product product : warehouse.getAllProducts()){
                if(product.getQuantity() < product.getThreshold()){
                    replenishmentStrategy.replenish(product);
                }
            }
        }
    }
}
