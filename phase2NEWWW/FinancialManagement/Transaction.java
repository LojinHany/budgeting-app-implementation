
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;       

public class Transaction {
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
    public boolean dotransaction() {
        // Validate the transaction details
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || transactionType == null || transactionDate == null) {
            return false; // Invalid transaction details
        }
        else if (category.getBudget() < amount) {
            System.out.println("Insufficient budget in the category: " + category.getCategory());
            System.out.println("Current budget: " + category.getBudget());
            return false; // Insufficient budget in the category
        }
        category.setBudget(category.getBudget() - amount); // Deduct the amount from the category budget
        System.out.println("Amount deducted: " + amount);
        System.out.println("New budget for category " + category.getCategory() + ": " + category.getBudget());
        account.withdraw(amount); // Withdraw the amount from the account
        System.out.println("Amount withdrawn from account " + account.getAccountID() + ": " + amount + "\nRemained balance: " + account.getBalance());
        budgetAnalysisManager.addNewExpense(transactionType, category.getCategory(), amount); // Add the expense to the budget analysis manager
        allTransactions.add(this);
        return true; // Return true if the transaction is successful
    }
    public boolean ReceiveTransaction() {
        // Validate the transaction details
        if (amount < 0 || account.getAccountID() == null || transactionID <= 0 || transactionType == null || transactionDate == null) {
            return false; // Invalid transaction details
        }
        account.deposit(amount); // Deposit the amount into the account
        allTransactions.add(this);
        Incomes.add(this);
        System.out.println("Amount deposited into account " + account.getAccountID() + ": " + amount + " from account " + recipientID);
        return true; // Return true if the transaction is successful
           }
    public static List<Transaction> getAllTransactions() {
        return allTransactions;
    }
    public static List<Transaction> getIncomes() {
        return Incomes;
    }
}
