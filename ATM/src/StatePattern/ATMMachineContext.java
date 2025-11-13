package StatePattern;

import CommonEnum.CashType;
import CommonEnum.TransactionType;
import StatePattern.ConcreteATMState.HasCardState;
import StatePattern.ConcreteATMState.IdleState;
import StatePattern.ConcreteATMState.SelectOperationState;
import StatePattern.ConcreteATMState.TransactionState;
import UtilityClasses.ATMInventory;
import UtilityClasses.Account;
import UtilityClasses.Card;

import java.util.HashMap;
import java.util.Map;

public class ATMMachineContext {

    private ATMState currentState;
    private Card currentCard;
    private Account currentAccount;
    private ATMInventory atmInventory;
    private Map<String,Account> accounts;
    private TransactionType selectedOperation;

    public ATMMachineContext() {
        this.currentState = new IdleState();
        this.atmInventory = new ATMInventory();
        this.accounts = new HashMap<>();
        System.out.println("ATM initialized in: " + currentState.getStateName());
    }

    public void advanceState(){
        currentState = currentState.next(this);
        System.out.println("Current state: " + currentState.getStateName());
    }

    public void insertCard(Card card){
        if(currentState instanceof IdleState){
            System.out.println("Card inserted");
            this.currentCard = card;
            advanceState();
        } else {
            System.out.println(
                    "Cannot insert card in " + currentState.getStateName());
        }
    }

    public void enterPin(int pin){
        if(currentState instanceof HasCardState){
            if(currentCard.validatePin(pin)){
                System.out.println("PIN authenticated successfully");
                this.currentAccount = accounts.get(currentCard.getAccountNumber());
                advanceState();
            } else {
                System.out.println("Invalid PIN. Please try again");
            }
        } else {
            System.out.println("Cannot enter PIN in " + currentState.getStateName());
        }
    }

    public void selectOperation(TransactionType transactionType){
        if(currentState instanceof SelectOperationState){
            System.out.println("Selected operation: " + transactionType);
            this.selectedOperation = transactionType;
            advanceState();
        } else {
            System.out.println(
                    "Cannot select operation in " + currentState.getStateName());
        }
    }

    public void performTransaction(double amount){
        if(currentState instanceof TransactionState){
            try{
                if(selectedOperation == TransactionType.WITHDRAW_CASH){
                    performWithdrawal(amount);
                } else {
                    checkBalance();
                }
                advanceState();
            } catch (Exception e){
                System.out.println("Transaction failed: " + e.getMessage());
                // Go back to select operation state
                currentState = new SelectOperationState();
            }

        } else {
            System.out.println(
                    "Cannot perform transaction in " + currentState.getStateName());
        }
    }

    private void performWithdrawal(double amount) throws Exception {
        if(!currentAccount.withdraw(amount)){
            throw new Exception("Insufficient funds in account");
        }
        if(!atmInventory.hasSufficientCash((int) amount)){
            currentAccount.deposit(amount);
            throw new Exception("Insufficient cash in ATM");
        };

        Map<CashType,Integer> dispensedCash = atmInventory.dispenseCash((int)amount);
        if (dispensedCash == null) {
            // Rollback the account withdrawal
            currentAccount.deposit(amount);
            throw new Exception("Unable to dispense exact amount");
        }
        System.out.println("Transaction successful. Please collect your cash:");
        for (Map.Entry<CashType, Integer> entry : dispensedCash.entrySet()) {
            System.out.println(entry.getValue() + " x $" + entry.getKey().value);
        }
    }

    // GETTERS AND SETTERS
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public ATMInventory getAtmInventory() {
        return atmInventory;
    }

    public void setAtmInventory(ATMInventory atmInventory) {
        this.atmInventory = atmInventory;
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(Account currentAccount) {
        this.currentAccount = currentAccount;
    }

    public Card getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public TransactionType getSelectedOperation() {
        return selectedOperation;
    }

    public void setSelectedOperation(TransactionType selectedOperation) {
        this.selectedOperation = selectedOperation;
    }
    private void checkBalance() {
        System.out.println(
                "Your current balance is: $" + currentAccount.getBalance());
    }
    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    // Get account by number
    public Account getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    public void returnCard() {
        if (currentState instanceof HasCardState
                || currentState instanceof SelectOperationState
                || currentState instanceof TransactionState) {
            System.out.println("Card returned to customer");
            resetATM();
        } else {
            System.out.println("No card to return in " + currentState.getStateName());
        }
    }

    private void resetATM() {
        this.currentCard = null;
        this.currentAccount = null;
        this.selectedOperation = null;
        this.currentState = new IdleState();
    }
}
