import usermanagement.*;
import FinancialManagement.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserServices userServices = new UserServices(null, null, null, 0); // Create a UserServices instance
        ReminderManager reminderManager = new ReminderManager();
        budgetAnalysisManager budgetManager = new budgetAnalysisManager();

        // Sample Reminders
        reminderManager.addReminder("Pay credit card bill", LocalDateTime.now().minusMinutes(1));
        reminderManager.addReminder("Study for exam", LocalDateTime.now().plusHours(2));

        while (true) {
            System.out.println("\n\nBudgeting App");
            System.out.println("\n1. User Management\n2. Financial Management\n3. Exit");
            String option = scanner.nextLine();
            if (option.equals("1")){

            System.out.print("1- SignUp\n2- SignIn\n3- LogOut\n4- ManageSettings\n5- Help & Support\n6- Goal Tracker\nEnter Your choice (1-6): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userServices.signUp();
                    break;

                case "2":
                    userServices.signIn();
                    break;

                case "3":
                    userServices.logOut();
                    System.exit(0); // Terminate the application
                    break;

                case "4":
                    userServices.ManageSettings();
                    break;

                case "5":
                    userServices.helpAndSupport();
                    break;

                case "6":
                    userServices.goalTracker();
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                    break;
            }
        } else if (option.equals("2")) {

            boolean flag = true;

            while (flag){
                System.out.println("\nChoose an option:");
                System.out.println("1. Add New Category");
                System.out.println("2. Add New Expense");
                System.out.println("3. Show Budget Analysis");
                System.out.println("4. Check Reminders");
                System.out.println("5. Back to main menu");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        System.out.print("Enter category name: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter budget: ");
                        double budget = Double.parseDouble(scanner.nextLine());
                        budgetManager.addNewCategory(category, budget);
                        break;

                    case "2":
                        System.out.print("Enter expense description: ");
                        String description = scanner.nextLine();
                        System.out.print("Enter category name: ");
                        String catName = scanner.nextLine();
                        if (!budgetManager.categoryExists(catName)) {
                            System.out.println("Category doesn't exist. Please add it first.");
                            break;
                        }
                        System.out.print("Enter expense amount spent: ");
                        double amount = Double.parseDouble(scanner.nextLine());
                        budgetManager.addNewExpense(description, catName, amount);
                        break;

                    case "3":
                        budgetManager.spentManager();
                        break;

                    case "4":
                        reminderManager.manageSendingReminders();
                        break;

                    case "5":
                        flag = false;
                        break;

                    default:
                        System.out.println("Invalid choice.");
                }
            }
        
        } else if (option.equals("3")) {
            System.out.println("Exiting");
            scanner.close();
            System.exit(0);
        }
        else {
            System.out.println("Invalid choice! Please enter (1/2)");
        }

        }
    }
}
