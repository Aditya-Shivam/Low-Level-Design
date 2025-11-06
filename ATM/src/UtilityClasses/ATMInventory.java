package UtilityClasses;

import CommonEnum.CashType;

import java.util.HashMap;
import java.util.Map;

public class ATMInventory {
    private Map<CashType,Integer> cashInventory;

    public ATMInventory() {
        this.cashInventory = new HashMap<>();
        initialiseInventory();
    }

    private void initialiseInventory() {
        cashInventory.put(CashType.BILL_100,10);
        cashInventory.put(CashType.BILL_50,10);
        cashInventory.put(CashType.BILL_20,20);
        cashInventory.put(CashType.BILL_10,30);
        cashInventory.put(CashType.BILL_5,20);
        cashInventory.put(CashType.BILL_1,50);
    }

    // Get total cash available
    public int getTotalCash(){
        int total = 0;
        for(Map.Entry<CashType,Integer> entry : cashInventory.entrySet()){
            total += entry.getKey().value * entry.getValue();
        }
        return total;
    }

    // check if ATM has sufficient cash
    public boolean hasSufficientCash(int amount){
        return getTotalCash() >= amount;
    }

    // Dispense cash for withdrawal
    Map<CashType,Integer> dispenseCash(int amount){
        if(!hasSufficientCash(amount)) return null;
        Map<CashType,Integer> dispensedCash = new HashMap<>();
        for(CashType cashType : CashType.values()){
            if(amount == 0) return dispensedCash;
            int count = Math.min(cashInventory.get(cashType),amount/cashType.value);
            amount -= count * cashType.value;
            cashInventory.put(cashType,cashInventory.get(cashType) - count);
            dispensedCash.put(cashType,count);
        }

        // if we count make the transaction rollback
        if(amount != 0){
            for(CashType cashType : CashType.values()){
                if(dispensedCash.containsKey(cashType))
                {
                    cashInventory.put(cashType,cashInventory.get(cashType) + dispensedCash.get(cashType));
                }
            }
            return null;
        }
        return dispensedCash;
    }

    public void addCash(CashType cashType, int count){
        cashInventory.put(cashType,cashInventory.get(cashType) + count);
    }
}
