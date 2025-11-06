package UtilityClasses;

public class Account {
    private String accountNumber;
    private double balance;

    public String getAccountNumber() {
        return accountNumber;
    }

    public boolean withdraw(double amount){
        if(this.getBalance() < amount){
            return false;
        }
        this.setBalance(this.getBalance() - amount);
        return true;
    }

    public void deposit(double amount){
        this.setBalance(this.getBalance() + amount);
    }

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
