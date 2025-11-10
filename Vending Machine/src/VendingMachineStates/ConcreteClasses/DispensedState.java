package VendingMachineStates.ConcreteClasses;

import VendingMachineStates.VendingMachineContext;
import VendingMachineStates.VendingMachineState;

public class DispensedState implements VendingMachineState {

    public DispensedState() {
        System.out.println("In Dispensed State");
    }

    @Override
    public String getStateName() {
        return "Dispensed State";
    }

    @Override
    public VendingMachineState next(VendingMachineContext context) {
        return new IdleState();
    }
}
