package financialManagement;

import java.io.Serializable;

public class Expense implements Serializable { 
    private String description;
    private String category;
    private double amount;

    public Expense(String description, String category, double amount) {
        this.category = category;
        this.amount = amount;
        this.description = description;
    }

    public String getCategory() {
         return category; 
    }
    public double getAmount() { 
        return amount; 
    }
    public String getDescription() {
        return description;
    }
}
