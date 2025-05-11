package financialManagement;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import persistenceLayer.UltraSimpleUserStorage;

@SuppressWarnings("FieldMayBeFinal")
public class BudgetAnalysisManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Category> categories;
    private List<Transaction> transactions;
    private List<Transaction> incomes;
    private List<Expense> expenses;

    public BudgetAnalysisManager() {
        // Initialize lists from storage
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

    public void addNewCategory(String categoryName, double budget) {
        // reload categories to get latest
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

    public boolean categoryExists(String name) {
        for (Category cat : categories) {
            if (cat.getCategory().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Category getCategoryByName(String categoryName) {
        for (Category cat : categories) {
            if (cat.getCategory().equalsIgnoreCase(categoryName)) {
                return cat;
            }
        }
        return null;
    }

    public void addNewExpense(String description, String categoryName, double amount) {
        // reload expenses and categories
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

    public void spentManager() {
        // reload expenses and categories
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

    public void trackExpenses() {
        // reload expenses
        try { expenses = UltraSimpleUserStorage.loadExpenses(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Expenses \t\t\t\t");
        for (Expense exp : expenses) {
            System.out.printf("Category: %s\nDescription: %s\nAmount: $%.2f\n", exp.getCategory(), exp.getDescription(), exp.getAmount());
        }
    }

    public void trackTransactions() {
        // reload transactions
        try { transactions = UltraSimpleUserStorage.loadTransactions(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Transactions \t\t\t\t");
        for (Transaction tran : transactions) {
            System.out.printf("Transaction ID: %.0f\nAccount ID: %s\nTransaction Type: %s\nTransaction Date: %s\nRecipient ID: %s\nCategory: %s\nAmount: $%.2f\n", 
                tran.getTransactionID(), tran.getAccount().getAccountID(), tran.getTransactionType(), tran.getTransactionDate(), tran.getRecipientID(), tran.getCategory().getCategory(), tran.getAmount());
        }
    }

    public void trackIncomes() {
        // reload incomes
        try { incomes = UltraSimpleUserStorage.loadIncomes(); } catch (Exception ignore) {}

        System.out.println("\n\t\t\t\t Track Incomes \t\t\t\t");
        for (Transaction tran : incomes) {
            System.out.printf("Transaction ID: %.0f\nAccount ID: %s\nTransaction Type: %s\nTransaction Date: %s\nRecipient ID: %s\nCategory: %s\nAmount: $%.2f\n", 
                tran.getTransactionID(), tran.getAccount().getAccountID(), tran.getTransactionType(), tran.getTransactionDate(), tran.getRecipientID(), tran.getCategory().getCategory(), tran.getAmount());
        }
    }
}
