package VendingMachineStates.ConcreteClasses;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class IdleState implements VendingMachineState {

    public IdleState(){
        System.out.println("Vending Machine is in Idle State");
    }
    @Override
    public String getStateName() {
        return "Idle State";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if(!context.getInventory().hasItems()){
            return new OutOfStock();
        }

        if(!context.getCoinList().isEmpty()){
            return new HasMoneyState();
        }

        // If money is inserted then move to hasMoneyState

        return this;
    }
}
