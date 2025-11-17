package Factory;

import CommonEnum.ProductCategory;
import Factory.ConcreteProducts.ClothingProduct;
import Factory.ConcreteProducts.ElectronicsProduct;
import Factory.ConcreteProducts.GroceryProduct;

public class ProductFactory {
    public Product createProduct(ProductCategory category, String sku, String name, double price, int quantity, int threshold){
        switch (category){
            case ELECTRONICS -> {
                return new ElectronicsProduct(sku,name,price,quantity,threshold);
            }
            case CLOTHING ->{
                return new ClothingProduct(sku, name, price, quantity, threshold);
            }
            case GROCERY -> {
                return new GroceryProduct(sku, name, price, quantity,threshold);
            }
            default -> {
                throw new IllegalArgumentException("Unsupported Product Category");
            }
        }
    }
}
