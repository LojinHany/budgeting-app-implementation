package financialManagement;

import java.io.Serializable;

@SuppressWarnings("FieldMayBeFinal")
public class Category implements Serializable {
    private String category;
    private double budget;

    public Category(String category, double budget) {
        this.category = category;
        this.budget = budget;
    }
    public String getCategory() { 
        return category;
    }
    public double getBudget() { 
        return budget; 
    }
    public void setBudget(double budget) {
         this.budget = budget;
    }
}
