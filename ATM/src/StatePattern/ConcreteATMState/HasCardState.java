package StatePattern.ConcreteATMState;

import StatePattern.ATMMachineContext;
import StatePattern.ATMState;

public class HasCardState implements ATMState {
    public HasCardState() {
        System.out.println("ATM is in Has Card State - Please enter your PIN");
    }

    @Override
    public String getStateName() {
        return "HasCardState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if(context.getCurrentCard() == null){
            return new IdleState();
        }
        if(context.getCurrentAccount() != null){
            return new SelectOperationState();
        }
        return this;
    }
}
