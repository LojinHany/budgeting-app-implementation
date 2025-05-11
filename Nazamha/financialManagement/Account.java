package financialManagement;

import java.io.Serializable;

/**
 * Represents a financial account with an ID and balance.
 * This class provides functionality to deposit and withdraw funds from the account.
 */
public class Account implements Serializable {
    private final String accountID;
    private double balance;

    /**
     * Constructs a new Account with a specified account ID and initial balance.
     *
     * @param accountID      The unique identifier for the account.
     * @param initialBalance The initial balance of the account.
     */
    public Account(String accountID, double initialBalance) { 
        this.accountID = accountID;
        this.balance = initialBalance;
    }

    /**
     * Retrieves the account ID of this account.
     *
     * @return The unique identifier of the account.
     */
    public String getAccountID() {
        return accountID;
    }

    /**
     * Retrieves the current balance of this account.
     *
     * @return The current balance of the account.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Deposits the specified amount into this account, increasing the balance.
     *
     * @param amount The amount to deposit.
     */
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * Withdraws the specified amount from this account if sufficient funds are available.
     *
     * @param amount The amount to withdraw.
     * @return true if the withdrawal is successful, false if there are insufficient funds.
     */
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        } else {
            return false; // insufficient funds
        }
    }
}
