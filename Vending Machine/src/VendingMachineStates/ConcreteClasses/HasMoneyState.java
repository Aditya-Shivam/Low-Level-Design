package VendingMachineStates.ConcreteClasses;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class HasMoneyState implements VendingMachineState {

    public HasMoneyState() {
        System.out.println("Vending Machine is in HasMoneyState");
    }

    @Override
    public String getStateName() {
        return "Has Money State";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {

        if(!context.getInventory().hasItems()){
            return new OutOfStock();
        }

        if(context.getCoinList().isEmpty()){
            return new IdleState();
        }

        if(context.getCurrentState() instanceof HasMoneyState){
            return new SelectionState();
        }

        return this;
    }
}
