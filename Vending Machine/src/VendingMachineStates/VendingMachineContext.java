package VendingMachineStates;

import CommonEnums.Coin;
import UtilityClasses.Inventory;
import UtilityClasses.Item;
import VendingMachineStates.ConcreteClasses.DispensedState;
import VendingMachineStates.ConcreteClasses.HasMoneyState;
import VendingMachineStates.ConcreteClasses.IdleState;
import VendingMachineStates.ConcreteClasses.SelectionState;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class VendingMachineContext {

    private VendingMachineState currentState;
    private Inventory inventory;
    private List<Coin> coinList;
    private int selectedItemCode;

    public VendingMachineContext() {
        inventory = new Inventory(10);
        coinList = new ArrayList<>();
        currentState = new IdleState();
        System.out.println("Initialized: " + currentState.getStateName());
    }

    public void advanceState(){
        this.currentState =  currentState.next(this);
        System.out.println("Current State: " + currentState.getStateName());
    }

    public void clickOnInsertCoinButton(Coin coin){
        if(currentState instanceof IdleState || currentState instanceof HasMoneyState) {
            System.out.println("Inserted" + coin.name() + " worth " + coin.value);
            coinList.add(coin);
            advanceState();
        } else{
            System.out.println("Cannot insert coin in " + currentState.getStateName());
        }

    }

    public void clickOnStartProductSelectionButton(int codeNumber){
        if(currentState instanceof HasMoneyState){
            advanceState();
            selectProduct(codeNumber);
        } else {
            System.out.println("Product selection button can only be clicked in HasMoney state");
        }
    }

    public void selectProduct(int codeNumber) {
        if(currentState instanceof SelectionState){
            try {
                Item item = inventory.getItem(codeNumber);
                int balance = getBalance();
                if(balance < item.getPrice()){
                    System.out.println(
                            "Insufficient amount. Product price: " + item.getPrice() + ", paid: " + balance);
                    return;
                }
                setSelectedItemCode(codeNumber);
                advanceState();
                dispenseItem(codeNumber);
            } catch (Exception e){

            }
        }
    }

    private void dispenseItem(int codeNumber) {
        if(currentState instanceof DispensedState){
            try {
                Item item = inventory.getItem(codeNumber);
                System.out.println("Dispensing: " + item.getItemType());
                inventory.removeItem(codeNumber);
                inventory.updateSoldOutItem(codeNumber);
                resetBalance();
                resetSelection();
                advanceState();
            } catch (Exception e){
                System.out.println("Failed to Dispense the Product with code : " + codeNumber);
            }

        } else {
            System.out.println("System cannot dispense in : " + currentState);
        }
    }

    private void resetSelection() {
        this.selectedItemCode = 0;
    }

    private void resetBalance() {
        coinList.clear();
    }

    private int getBalance() {
        int balance = 0;
        for(Coin c : coinList){
            balance += c.value;
        }
        return balance;
    }

    // GETTERS AND SETTERS
    public List<Coin> getCoinList() {
        return coinList;
    }

    public void setCoinList(List<Coin> coinList) {
        this.coinList = coinList;
    }

    public VendingMachineState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VendingMachineState currentState) {
        this.currentState = currentState;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getSelectedItemCode() {
        return selectedItemCode;
    }

    public void setSelectedItemCode(int selectedItemCode) {
        this.selectedItemCode = selectedItemCode;
    }

    public void updateInventory(Item newItem, int codeNumber) {
        if(currentState instanceof IdleState){
            try{
                inventory.addItem(newItem,codeNumber);
                System.out.println("Added " + newItem.getItemType() + " to slot " + codeNumber);
            } catch (Exception e){
                System.out.println("Error updating inventory: " + e.getMessage());
            }
        } else {
            System.out.println("Inventory can only be updated in Idle state");
        }
    }
}
