package Factory.ConcreteProducts;

import CommonEnum.ProductCategory;
import Factory.Product;

public class ElectronicsProduct extends Product {
    private String brand;
    private int warrantyPeriod;

    public ElectronicsProduct(String sku,String name, double price, int quantity, int threshold) {
        super();
        setSku(sku);
        setName(name);
        setPrice(price);
        setQuantity(quantity);
        setProductCategory(ProductCategory.ELECTRONICS);
        setThreshold(threshold);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getWarrantyPeriod() {
        return warrantyPeriod;
    }

    public void setWarrantyPeriod(int warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }
}
