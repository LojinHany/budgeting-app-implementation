package financialManagement;

import java.io.Serializable;

public class Account implements Serializable {
    private final String accountID;
    private double balance;

    public Account(String accountID, double initialBalance) { 
        this.accountID = accountID;
        this.balance = initialBalance;
    }

    public String getAccountID() {
        return accountID;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false; // insufficient funds
        }
    }
}
