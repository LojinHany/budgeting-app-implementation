package FinancialManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class budgetAnalysisManager {
    private List<Category> categories;
    private List<Expense> expenses;

    public budgetAnalysisManager() {
        categories = new ArrayList<>();
        expenses = new ArrayList<>();
    }
    
    public void addNewCategory(String categoryName, double budget) {
        if (categoryExists(categoryName)) {
        System.out.println("Category \"" + categoryName + "\" already exists.");
        return;
        }
        categories.add(new Category(categoryName, budget));
        System.out.println("Category added successfully");
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

    public boolean expenseExists(String categoryName, double amount, String description) {
    for (Expense exp : expenses) {
        if (exp.getCategory().equalsIgnoreCase(categoryName) &&
            exp.getAmount() == amount && 
            exp.getDescription().equalsIgnoreCase(description)) // prevent duplicates from being added
        {
            return true;
        }
    }
    return false; 
    }

    public void addNewExpense(String description, String categoryName, double amount) {
        if (!categoryExists(categoryName)) {
        System.out.println("Category \"" + categoryName + "\" does not exist.");
        return;
    }

    if (expenseExists(categoryName, amount, description)) {
        System.out.println("This expense is already entered: Category: " + categoryName + ", Amount: $" + amount + ", Description: " + description);
        return;
    }

    Category category = getCategoryByName(categoryName);
    if (category != null) {
        double categoryBudget = category.getBudget();
        // If the amount is greater than the category's budget, show a message
        if (amount > categoryBudget) {
            System.out.println("Warning: The amount entered ($" + amount + ") exceeds the budget for category \"" + categoryName + "\" ($" + categoryBudget + ").");
        }
    }

    expenses.add(new Expense(description, categoryName, amount));
    System.out.println("Expense added successfully");
    }


    public void spentManager () {
        Map<String, Double> analysisMap = new HashMap<>();
        for (Expense exp : expenses) {
            String cat = exp.getCategory();
            Double amount = exp.getAmount();
            if (analysisMap.containsKey(cat)) {
                analysisMap.put(cat, analysisMap.get(cat) + amount);
            } else {
                analysisMap.put(cat, amount);
            }
        }

        System.out.println("\n\t\t\t\t Budget Analysis \t\t\t\t");

        for (Category cat: categories) {
            Double spent = analysisMap.getOrDefault(cat.getCategory(),0.0);
            Double budget = cat.getBudget();

            System.out.printf(" Category: %s\n Budget: $%.2f\n Spent: $%.2f\n",
            cat.getCategory(), budget, spent);
            if (spent > budget) {
                System.out.println("--> Over Budget by $" + String.format("%.2f", spent - budget) + "\n");
            }
            else {
                System.out.println("--> Under Budget by $" + String.format("%.2f", budget - spent) + "\n");
            }
        }
    }
    
}
