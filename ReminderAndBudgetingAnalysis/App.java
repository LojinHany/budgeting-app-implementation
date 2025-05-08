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
        if (isExpired()) {
            System.out.println("There is a reminder! \n [Reminder]: " + msgInfo + " (Due: " + dueDate + ")");
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

    public budgetAnalysisManager() {
        categories = new ArrayList<>();
        expenses = new ArrayList<>();
    }
    
    public void addCategory(String categoryName, double budget) {
        categories.add(new Category(categoryName, budget));
    }

    public void addExpense(String categoryName, double amount) {
        expenses.add(new Expense(categoryName, amount));
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


public class App {
    public static void main(String[] args) {
        // Setup Reminders
        ReminderManager rm = new ReminderManager();
        rm.addReminder("Pay credit card bill", LocalDateTime.now().minusMinutes(1));
        rm.addReminder("Study for exam", LocalDateTime.now().plusHours(2));
        rm.manageSendingReminders(); // Only shows due reminders

        // Setup Budget & Expenses
        budgetAnalysisManager bm = new budgetAnalysisManager();
        bm.addCategory("Food", 200);
        bm.addCategory("Entertainment", 150);

        bm.addExpense("Food", 180);
        bm.addExpense("Entertainment", 170);
        bm.addExpense("Food", 30); // Now over budget

        bm.spentManager();
    }
}

