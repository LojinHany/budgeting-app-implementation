package financialManagement;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import persistenceLayer.UltraSimpleUserStorage;

/**
 * Represents a financial transaction, either a withdrawal or deposit, associated with an account, category, and other relevant details.
 * This class handles the execution of transactions, including withdrawal and deposit operations, and stores transaction details.
 */
public class Transaction implements Serializable {
    private final double amount;   
    private final Account account;
    private final double transactionID;
    private final String transactionType;
    private final LocalDateTime transactionDate;
    private final String recipientID;
    private final Category category;
    private final BudgetAnalysisManager budgetAnalysisManager;

    private static final List<Transaction> allTransactions = new ArrayList<>();
    private static final List<Transaction> Incomes = new ArrayList<>();

    /**
     * Constructs a new Transaction with specified details.
     *
     * @param amount                The transaction amount.
     * @param account               The account associated with this transaction.
     * @param transactionID         The unique identifier for the transaction.
     * @param transactionType       The type of transaction (e.g., withdrawal, deposit).
     * @param transactionDate       The date and time when the transaction occurred.
     * @param recipientID           The recipient account ID (for deposit transactions).
     * @param category              The category associated with this transaction.
     * @param budgetAnalysisManager The budget analysis manager for managing financial data.
     */
    public Transaction(double amount, Account account, double transactionID, String transactionType, 
                       LocalDateTime transactionDate, String recipientID, Category category, 
                       BudgetAnalysisManager budgetAnalysisManager) {
        this.category = category;
        this.amount = amount;
        this.account = account;
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.recipientID = recipientID;
        this.budgetAnalysisManager = budgetAnalysisManager;
    }

    /**
     * Retrieves the amount of this transaction.
     *
     * @return The amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Retrieves the account associated with this transaction.
     *
     * @return The account associated with the transaction.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Retrieves the unique transaction ID.
     *
     * @return The transaction ID.
     */
    public double getTransactionID() {
        return transactionID;
    }

    /**
     * Retrieves the type of the transaction (e.g., withdrawal, deposit).
     *
     * @return The transaction type.
     */
    public String getTransactionType() {
        return transactionType;
    }

    /**
     * Retrieves the date and time when the transaction occurred.
     *
     * @return The transaction date.
     */
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    /**
     * Retrieves the recipient account ID for a deposit transaction.
     *
     * @return The recipient account ID.
     */
    public String getRecipientID() {
        return recipientID;
    }

    /**
     * Retrieves the category associated with this transaction.
     *
     * @return The transaction's category.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Retrieves the budget analysis manager associated with this transaction.
     *
     * @return The budget analysis manager.
     */
    public BudgetAnalysisManager getBudgetAnalysisManager() {
        return budgetAnalysisManager;
    }

    /**
     * Executes a withdrawal transaction from the associated account, 
     * reducing the category's budget and the account balance.
     *
     * @return true if the transaction was successfully processed, false otherwise.
     */
    public boolean doTransaction() {
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || 
            transactionType == null || transactionDate == null) {
            return false;
        } else if (category.getBudget() < amount) {
            System.out.println("Insufficient budget in the category: " + category.getCategory());
            System.out.println("Current budget: " + category.getBudget());
            return false;
        }

        category.setBudget(category.getBudget() - amount);
        account.withdraw(amount);
        System.out.println("\nAmount withdrawn from account " + account.getAccountID() + ": " + amount);
        System.out.println("Current balance: " + account.getBalance());
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction date: " + transactionDate);
        System.out.println("Transaction type: " + transactionType);
        System.out.println("Category: " + category.getCategory() + " Budget after transaction: " + category.getBudget());
        allTransactions.add(this);
        try {
            UltraSimpleUserStorage.saveTransactions(allTransactions);
        } catch (IOException e) {
            System.err.println("Failed to save transactions: " + e.getMessage());
        }

        return true;
    }

    /**
     * Executes a deposit transaction, adding the specified amount to the associated account.
     *
     * @return true if the transaction was successfully processed, false otherwise.
     */
    public boolean receiveTransaction() {
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || 
            transactionType == null || transactionDate == null) {
            return false;
        }

        account.deposit(amount);
        allTransactions.add(this);
        Incomes.add(this);

        try {
            UltraSimpleUserStorage.saveTransactions(allTransactions);
            UltraSimpleUserStorage.saveIncomes(Incomes);
        } catch (IOException e) {
            System.err.println("Failed to save transactions/incomes: " + e.getMessage());
        }

        System.out.println("Amount deposited into account " + account.getAccountID() + ": " + amount + " from account " + recipientID);
        System.out.println("Current balance: " + account.getBalance());
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction date: " + transactionDate);
        System.out.println("Transaction type: " + transactionType);
        return true;
    }
}
