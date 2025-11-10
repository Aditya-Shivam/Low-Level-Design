package UtilityClasses;

public class Inventory {
    private ItemShelf[] inventory;

    public Inventory(int itemCount){
        inventory = new ItemShelf[itemCount];
        initialiseEmptyInventory();
    }

    private void initialiseEmptyInventory() {
        int statusCode = 101;
        for(int i = 0; i < inventory.length ; i++){
            ItemShelf itemShelf = new ItemShelf(statusCode);
            inventory[i] = itemShelf;
            statusCode++;
        }
    }

    public ItemShelf[] getInventory() {
        return inventory;
    }

    public void setInventory(ItemShelf[] inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item, int codeNumber) throws Exception{
        for(ItemShelf itemShelf : inventory){
            if(itemShelf.getCode() == codeNumber){
                itemShelf.addItems(item);
                return;
            }
        }
        System.out.println(codeNumber + "_________!@@@@@@");
        throw new Exception("Invalid Code");
    }

    public Item getItem(int codeNumber) throws Exception{
        for(ItemShelf itemShelf : inventory){
            if(itemShelf.getCode() == codeNumber){
                if(itemShelf.isSoldOut()) throw new Exception("Item already sold out");
                return itemShelf.getItems().get(0);
            }
        }
        throw new Exception("Invalid Code");
    }

    public boolean hasItems() {
        for(ItemShelf itemShelf : inventory){
            if(!itemShelf.isSoldOut()) return true;
        }
        return false;
    }

    public void removeItem(int codeNumber) throws Exception{
        for(ItemShelf itemShelf : inventory){
            if(itemShelf.getCode() == codeNumber){
                itemShelf.removeItems(itemShelf.getItems().get(0));
                return;
            }
        }
        throw new Exception("Invalid Code");
    }

    public void updateSoldOutItem(int codeNumber) {
        for(ItemShelf itemShelf : inventory){
            if(itemShelf.getCode() == codeNumber){
                if(itemShelf.getItems().isEmpty()){
                    itemShelf.setSoldOut(true);
                }
            }
        }
    }
}
