package VendingMachineStates.ConcreteClasses;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class SelectionState implements VendingMachineState {
    public SelectionState() {
        System.out.println("Vending Machine is in Selection State");
    }

    @Override
    public String getStateName() {
        return "Selection State";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if(!context.getInventory().hasItems()){
            return new OutOfStock();
        }

        if(context.getCoinList().isEmpty()){
            return new IdleState();
        }

        if(context.getSelectedItemCode() > 0){
            return new DispensedState();
        }
        return this;
    }
}
