package StatePattern.ConcreteATMState;

import StatePattern.ATMMachineContext;
import StatePattern.ATMState;

public class IdleState implements ATMState {
    public IdleState() {
        System.out.println("ATM Machine is in Idle State : Please Insert Card");
    }

    @Override
    public String getStateName() {
        return "Idle State";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if(context.getCurrentCard() != null){
            return new HasCardState();
        }
        return this;
    }
}
