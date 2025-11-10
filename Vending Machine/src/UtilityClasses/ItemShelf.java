package UtilityClasses;

import java.util.ArrayList;
import java.util.List;

public class ItemShelf {

    private int code;

    // NOTE -> we can also have count instead of list, assuming each shelf will have only one product
    private List<Item> items;
    private boolean isSoldOut;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isSoldOut() {
        return isSoldOut;
    }

    public void setSoldOut(boolean soldOut) {
        isSoldOut = soldOut;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        if(isSoldOut) setSoldOut(false);
    }

    public ItemShelf(int code) {
        this.code = code;
        this.items = new ArrayList<>();
        isSoldOut = false;
    }

    public void addItems(Item item){
        items.add(item);
        if(isSoldOut) setSoldOut(false);
    }

    public void removeItems(Item item){
        items.remove(item);
        if(items.isEmpty()) setSoldOut(true);
    }

    @Override
    public String toString() {
        return "ItemShelf{" +
                "code=" + code +
                ", items=" + items +
                ", isSoldOut=" + isSoldOut +
                '}';
    }
}
