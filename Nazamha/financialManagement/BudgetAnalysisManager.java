package financialManagement;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistenceLayer.UltraSimpleUserStorage;

/**
 * Manages the analysis and tracking of a user's budget, categories, expenses, and transactions.
 * Provides functionality for adding new categories and expenses, as well as tracking and analyzing expenses and transactions.
 */
@SuppressWarnings("FieldMayBeFinal")
public class BudgetAnalysisManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Category> categories;
    private List<Transaction> transactions;
    private List<Transaction> incomes;
    private List<Expense> expenses;

    /**
     * Constructs a new BudgetAnalysisManager and initializes the lists of categories, expenses, transactions, and incomes.
     * Loads data from storage if available.
     */
    public BudgetAnalysisManager() {
        try {
            this.categories   = UltraSimpleUserStorage.loadCategories();
            this.expenses     = UltraSimpleUserStorage.loadExpenses();
            this.transactions = UltraSimpleUserStorage.loadTransactions();
            this.incomes      = UltraSimpleUserStorage.loadIncomes();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            this.categories   = new ArrayList<>();
            this.expenses     = new ArrayList<>();
            this.transactions = new ArrayList<>();
            this.incomes      = new ArrayList<>();
        }
    }

    /**
     * Adds a new category with a specified name and budget if the category doesn't already exist.
     * The updated categories list is saved to storage after the addition.
     *
     * @param categoryName The name of the category to add.
     * @param budget       The budget for the new category.
     */
    public void addNewCategory(String categoryName, double budget) {
        try { categories = UltraSimpleUserStorage.loadCategories(); } catch (Exception ignore) {}

        if (categoryExists(categoryName)) {
            System.out.println("Category \"" + categoryName + "\" already exists.");
            return;
        }
        categories.add(new Category(categoryName, budget));
        System.out.println("Category added successfully");
        try {
            UltraSimpleUserStorage.saveCategories(categories);
        } catch (IOException e) {
            System.err.println("Failed to save categories: " + e.getMessage());
        }
    }

    /**
     * Checks if a category exists in the current list of categories.
     *
     * @param name The name of the category to check.
     * @return true if the category exists, false otherwise.
     */
    public boolean categoryExists(String name) {
        for (Category cat : categories) {
            if (cat.getCategory().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves a category by its name.
     *
     * @param categoryName The name of the category to retrieve.
     * @return The category with the specified name, or null if no such category exists.
     */
    public Category getCategoryByName(String categoryName) {
        for (Category cat : categories) {
            if (cat.getCategory().equalsIgnoreCase(categoryName)) {
                return cat;
            }
        }
        return null;
    }

    /**
     * Adds a new expense with a description, category, and amount if the category exists and the expense is not already entered.
     * The updated expenses list is saved to storage after the addition.
     *
     * @param description The description of the expense.
     * @param categoryName The category of the expense.
     * @param amount      The amount of the expense.
     */
    public void addNewExpense(String description, String categoryName, double amount) {
        try { expenses = UltraSimpleUserStorage.loadExpenses(); } catch (Exception ignore) {}
        try { categories = UltraSimpleUserStorage.loadCategories(); } catch (Exception ignore) {}

        if (!categoryExists(categoryName)) {
            System.out.println("Category \"" + categoryName + "\" does not exist.");
            return;
        }
        for (Expense exp : expenses) {
            if (exp.getCategory().equalsIgnoreCase(categoryName) &&
                exp.getAmount() == amount &&
                exp.getDescription().equalsIgnoreCase(description)) {
                System.out.println("This expense is already entered: Category: " + categoryName + ", Amount: $" + amount + ", Description: " + description);
                return;
            }
        }
        expenses.add(new Expense(description, categoryName, amount));
        System.out.println("Expense added successfully");
        try {
            UltraSimpleUserStorage.saveExpenses(expenses);
        } catch (IOException e) {
            System.err.println("Failed to save expenses: " + e.getMessage());
        }
    }

    /**
     * Analyzes and prints the budget status by comparing the amount spent in each category with the budget.
     * Displays whether the category is over or under budget.
     */
    public void spentManager() {
        try { expenses = UltraSimpleUserStorage.loadExpenses(); } catch (Exception ignore) {}
        try { categories = UltraSimpleUserStorage.loadCategories(); } catch (Exception ignore) {}

        Map<String, Double> analysisMap = new HashMap<>();
        for (Expense exp : expenses) {
            analysisMap.merge(exp.getCategory(), exp.getAmount(), Double::sum);
        }
        System.out.println("\n\t\t\t\t Budget Analysis \t\t\t\t");
        for (Category cat : categories) {
            double spent = analysisMap.getOrDefault(cat.getCategory(), 0.0);
            double budget = cat.getBudget();
            System.out.printf(" Category: %s\n Budget: $%.2f\n Spent: $%.2f\n",
                cat.getCategory(), budget, spent);
            if (spent > budget) {
                System.out.println("--> Over Budget by $" + String.format("%.2f", spent - budget) + "\n");
            } else {
                System.out.println("--> Under Budget by $" + String.format("%.2f", budget - spent) + "\n");
            }
        }
    }

    /**
     * Displays a list of all expenses.
     */
    public void trackExpenses() {
        try { expenses = UltraSimpleUserStorage.loadExpenses(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Expenses \t\t\t\t");
        for (Expense exp : expenses) {
            System.out.printf("\nCategory: %s\nDescription: %s\nAmount: $%.2f\n", exp.getCategory(), exp.getDescription(), exp.getAmount());
        }
    }

    /**
     * Displays a list of all transactions.
     */
    public void trackTransactions() {
        try { transactions = UltraSimpleUserStorage.loadTransactions(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Transactions \t\t\t\t");
        for (Transaction tran : transactions) {
            System.out.printf("Transaction ID: %.0f\nAccount ID: %s\nTransaction Type: %s\nTransaction Date: %s\nRecipient ID: %s\nCategory: %s\nAmount: $%.2f\n", 
                tran.getTransactionID(), tran.getAccount().getAccountID(), tran.getTransactionType(), tran.getTransactionDate(), tran.getRecipientID(), tran.getCategory().getCategory(), tran.getAmount());
        }
    }

    /**
     * Displays a list of all incomes.
     */
    public void trackIncomes() {
        try { incomes = UltraSimpleUserStorage.loadIncomes(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Incomes \t\t\t\t");
        for (Transaction tran : incomes) {
            System.out.printf("Transaction ID: %.0f\nAccount ID: %s\nTransaction Type: %s\nTransaction Date: %s\nRecipient ID: %s\nCategory: %s\nAmount: $%.2f\n", 
                tran.getTransactionID(), tran.getAccount().getAccountID(), tran.getTransactionType(), tran.getTransactionDate(), tran.getRecipientID(), tran.getCategory().getCategory(), tran.getAmount());
        }
    }
}
