package financialManagement;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import persistenceLayer.UltraSimpleUserStorage;

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
    
     public Transaction(double amount,Account account, double transactionID, String transactionType, LocalDateTime transactionDate, String recipientID, Category category,BudgetAnalysisManager budgetAnalysisManager) {
        this.category = category;
        this.amount = amount;
        this.account = account;
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.transactionDate = transactionDate;
        this.recipientID = recipientID;
        this.budgetAnalysisManager = budgetAnalysisManager;
    }
    public double getAmount() {
        return amount;
    }
    public Account getAccount() {
        return account;
    }
    public double getTransactionID() {
        return transactionID;
    }
    public String getTransactionType() {
        return transactionType;
    }
    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
    public String getRecipientID() {
        return recipientID;
    }
    public Category getCategory() {
        return category;
    }
    public BudgetAnalysisManager getBudgetAnalysisManager() {
        return budgetAnalysisManager;
    }

    public boolean doTransaction() {
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || transactionType == null || transactionDate == null) {
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
        System.out.println();
        System.out.println("Category: " + category.getCategory() + "Budget after transaction: " + category.getBudget());
        allTransactions.add(this);
        try {
            UltraSimpleUserStorage.saveTransactions(allTransactions);
        } catch (IOException e) {
            System.err.println("Failed to save transactions: " + e.getMessage());
        }

        return true;
    }

    public boolean receiveTransaction() {
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || transactionType == null || transactionDate == null) {
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
