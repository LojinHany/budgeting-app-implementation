import java.time.LocalDateTime;
import java.util.*;

class Reminder {
    private String msgInfo;
    private LocalDateTime dueDate;
    private boolean isDone;

    public Reminder (String msg, LocalDateTime due) {
        this.msgInfo = msg;
        this.dueDate = due;
        this.isDone = false;
    }

    public boolean isExpired () {
        return !isDone && LocalDateTime.now().isAfter(dueDate);
    }

    public void sendReminders () {
        if (!isExpired()) {
            System.out.println("[Reminder]: " + msgInfo);
            isDone = true;
        }
    }


}

class ReminderManager {
    private List<Reminder> allRemiders = new ArrayList<>(); 

    public void addReminder (String msg, LocalDateTime due) {
        allRemiders.add(new Reminder(msg, due));
    }

    public void manageSendingReminders () {
        for (Reminder rem : allRemiders ) {
            rem.sendReminders();
        }
    }
}

// Category class snippet for budgeting
class Category {
    private String category;
    private double budget;

    public Category(String category, double budget) {
        this.category = category;
        this.budget = budget;
    }

    public String getCategory() { return category; }
    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }
}

// Expense class snippet for analysis
class Expense {
    private String category;
    private double amount;

    public Expense(String category, double amount) {
        this.category = category;
        this.amount = amount;
    }

    public String getCategory() { return category; }
    public double getAmount() { return amount; }
}


class budgetAnalysisManager {
    private List<Category> categories;
    private List<Expense> expenses;
    
    public budgetAnalysisManager (List<Category>cat, List<Expense> exp) {
        categories= cat ;
        expenses = exp;
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

        for (Category cat: categories) {
            Double spent = analysisMap.getOrDefault(cat.getCategory(),0.0);
            Double budget = cat.getBudget();

            System.out.printf("Category: %s\n  Budget: $%.2f\n  Spent: $%.2f\n  %s\n",
            cat.getCategory(), budget, spent);
            if (budget > spent) {
                System.out.printf("Over Budget by $" + String.format("%.2f", spent - budget));
            }
            else {
                System.out.printf("Under Budget by $" + String.format("%.2f", budget - spent));
            }
        }
    }
}


