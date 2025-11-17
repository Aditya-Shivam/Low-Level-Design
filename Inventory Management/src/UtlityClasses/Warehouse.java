package UtlityClasses;

import Factory.Product;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private int id;
    private String location;
    private String name;
    private Map<String, Product> products; // sku->product

    public void setLocation(String location) {
        this.location = location;
    }

    public Warehouse(String name) {
        this.name = name;
        products = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        if(products.containsKey(product.getSku())){
            int newQuantity = products.get(product.getSku()).getQuantity() + quantity;
            products.get(product.getSku()).setQuantity(newQuantity);
        } else{
            product.setQuantity(quantity);
            products.put(product.getSku(),product);
        }
        System.out.println(quantity + " units of " + product.getName()
                + " (SKU: " + product.getSku() + ") added to " + name
                + ". New quantity: " + getAvailableQuantity(product.getSku()));
    }

    public boolean removeProduct(String sku, int quantity){
        Product product = products.get(sku);
        if(product != null){
            int currentQuantity = product.getQuantity();
            if(currentQuantity < quantity){
                System.out.println("Error: Insufficient inventory. Requested: "
                        + quantity + ", Available: " + currentQuantity);
                return false;
            }
            product.setQuantity(currentQuantity-quantity);
            System.out.println(quantity + " units of " + product.getName()
                    + " (SKU: " + sku + ") removed from " + name
                    + ". Remaining quantity: " + product.getQuantity());
            if(product.getQuantity() == 0) {
                products.remove(sku);
                System.out.println("Product " + product.getName()
                        + " removed from inventory as quantity is now zero.");
            }
            return true;

        } else {
            System.out.println(
                    "Error: Product with SKU " + sku + " not found in " + name);
            return false;
        }
    }

    private int getAvailableQuantity(String sku) {
        return products.get(sku).getQuantity();
    }

    public Product getProductBySku(String sku){
        return products.get(sku);
    }

    public Collection<Product> getAllProducts(){
        return products.values();
    }
}
