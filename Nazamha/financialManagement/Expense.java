package financialManagement;

import java.io.Serializable;

/**
 * Represents an expense with a description, category, and amount.
 * This class is used to track individual expenses in the financial management system.
 */
public class Expense implements Serializable { 
    private String description;
    private String category;
    private double amount;

    /**
     * Constructs a new Expense with a specified description, category, and amount.
     *
     * @param description The description of the expense.
     * @param category    The category to which the expense belongs.
     * @param amount      The amount of the expense.
     */
    public Expense(String description, String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    /**
     * Retrieves the category of this expense.
     *
     * @return The category to which this expense belongs.
     */
    public String getCategory() {
         return category; 
    }

    /**
     * Retrieves the amount of this expense.
     *
     * @return The amount of the expense.
     */
    public double getAmount() { 
        return amount; 
    }

    /**
     * Retrieves the description of this expense.
     *
     * @return The description of the expense.
     */
    public String getDescription() {
        return description;
    }
}
