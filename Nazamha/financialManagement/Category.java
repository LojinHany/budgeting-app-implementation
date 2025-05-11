package financialManagement;

import java.io.Serializable;

/**
 * Represents a budget category with a name and a budget amount.
 * This class allows for retrieving and modifying the category's budget.
 */
@SuppressWarnings("FieldMayBeFinal")
public class Category implements Serializable {
    private String category;
    private double budget;

    /**
     * Constructs a new Category with a specified name and budget.
     *
     * @param category The name of the category.
     * @param budget   The budget allocated for this category.
     */
    public Category(String category, double budget) {
        this.category = category;
        this.budget = budget;
    }

    /**
     * Retrieves the name of the category.
     *
     * @return The name of the category.
     */
    public String getCategory() { 
        return category;
    }

    /**
     * Retrieves the budget allocated to this category.
     *
     * @return The budget of the category.
     */
    public double getBudget() { 
        return budget; 
    }

    /**
     * Sets a new budget for this category.
     *
     * @param budget The new budget for the category.
     */
    public void setBudget(double budget) {
         this.budget = budget;
    }
}
