import financialManagement.*;
import java.time.LocalDateTime;
import java.util.Scanner;
import usermanagement.*;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserServices userServices = new UserServices(null, null, null, 0);
        ReminderManager reminderManager = new ReminderManager();
        BudgetAnalysisManager budgetManager = new BudgetAnalysisManager();
        boolean authenticated = false;
        
        // Sample Reminders
        reminderManager.addReminder("Pay credit card bill", LocalDateTime.now().minusMinutes(1));
        reminderManager.addReminder("Study for exam", LocalDateTime.now().plusHours(2));

        // Authentication loop
        while (!authenticated) {
            System.out.println("\n\t\t\t**Nazamha (Budgeting Application)**");
            System.out.println("1- SignUp");
            System.out.println("2- SignIn");
            System.out.print("Enter choice (1-2): ");
            String authChoice = scanner.nextLine().trim();
            switch (authChoice) {
                case "1":
                    authenticated = userServices.signUp();
                    break;
                case "2":
                    authenticated = userServices.signIn();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1 or 2.");
            }
        }

        // Main menu after successful auth
        while (true) {
            System.out.println("\n\t\t** Main Menu **");
            System.out.println("1- Add New Category");
            System.out.println("2- Add New Expense");
            System.out.println("3- Do Transaction");
            System.out.println("4- Check Reminders");
            System.out.println("5- Track Incomes");
            System.out.println("6- Track Expenses");
            System.out.println("7- Budget Analysis");
            System.out.println("8- Manage Settings");
            System.out.println("9- LogOut (Exit)");
            System.out.print("Enter choice (1-9): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    // Add New Category
                    System.out.print("Enter category name: ");
                    String cat = scanner.nextLine();
                    System.out.print("Enter budget: ");
                    double bud = Double.parseDouble(scanner.nextLine());
                    budgetManager.addNewCategory(cat, bud);
                    break;
                case "2":
                    // Add New Expense
                    System.out.print("Enter expense description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Enter category name: ");
                    String catName = scanner.nextLine();
                    if (!budgetManager.categoryExists(catName)) {
                        System.out.println("Category doesn't exist. Please add it first.");
                        break;
                    }
                    System.out.print("Enter expense amount spent: ");
                    double amt = Double.parseDouble(scanner.nextLine());
                    budgetManager.addNewExpense(desc, catName, amt);
                    break;
                case "3":
                    // Do Transaction
                    System.out.print("Enter transaction type (income/expense): ");
                    String transactionType = scanner.nextLine();
                    System.out.print("Enter amount: ");
                    double transactionAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter transaction ID: ");
                    double transactionID = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter recipient ID: ");
                    String recipientID = scanner.nextLine();
                    System.out.print("Enter category name: ");
                    String transactionCat = scanner.nextLine();
                    if (!budgetManager.categoryExists(transactionCat)) {
                        System.out.println("Category does not exist.");
                        break;
                    }
                    Category categoryOftrans = budgetManager.getCategoryByName(transactionCat);
                    Account account = new Account("12345", 1000); // Replace with actual
                    Transaction transaction = new Transaction(
                        transactionAmount,
                        account,
                        transactionID,
                        transactionType,
                        LocalDateTime.now(),
                        recipientID,
                        categoryOftrans,
                        budgetManager
                    );
                    boolean success = transactionType.equalsIgnoreCase("income")
                        ? transaction.receiveTransaction()
                        : transaction.doTransaction();
                    System.out.println(success ? "Transaction completed." : "Transaction failed.");
                    break;
                case "4":
                    // Check Reminders
                    reminderManager.manageSendingReminders();
                    break;
                case "5":
                    // Track Incomes
                    budgetManager.trackIncomes();
                    break;
                case "6":
                    // Track Expenses
                    budgetManager.trackExpenses();
                    break;
                case "7":
                    // Budget Analysis
                    budgetManager.spentManager();
                    break;
                case "8":
                    // Manage Settings
                    userServices.ManageSettings();
                    break;
                case "9":
                    userServices.logOut();
                    System.out.println("Exiting application. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid! Please enter a valid number (1-9)");
            }
        }
    }
}
