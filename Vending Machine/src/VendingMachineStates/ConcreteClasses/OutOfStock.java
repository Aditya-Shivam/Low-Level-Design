package VendingMachineStates.ConcreteClasses;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class OutOfStock implements VendingMachineState {
    @Override
    public String getStateName() {
        return "Out of Stock state";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        if(context.getInventory().hasItems()){
            return new IdleState();
        }
        return this;
    }
}
