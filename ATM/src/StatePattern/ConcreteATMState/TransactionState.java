package StatePattern.ConcreteATMState;

import StatePattern.ATMMachineContext;
import StatePattern.ATMState;

public class TransactionState implements ATMState {

    public TransactionState() {
        System.out.println("ATM is in Transaction State");
    }

    @Override
    public String getStateName() {
        return "TransactionState";
    }

    @Override
    public ATMState next(ATMMachineContext context) {
        if(context.getCurrentCard() == null){
            return new IdleState();
        }
        return new SelectOperationState();
    }

}
