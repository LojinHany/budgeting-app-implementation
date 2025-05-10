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
            System.out.println("\n\t\t\t**Nazamha (Budgeting Application)**\n1- 6 User Story Cases\n2- Navigate Application\n3- Exit");
            String option = scanner.nextLine();
            if (option.equals("1")){

            System.out.print("1- SignUp\n2- SignIn\n3- Tracking Income\n4- Budgeting And Analysis\n5- Reminders\n6- Expense Tracking\n7- LogOut(Exit)\nEnter Your choice (1-7): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userServices.signUp();
                    break;

                case "2":
                    userServices.signIn();
                    break;

                case "3":
                    //Track Income
                    break;

                case "4":
                    budgetManager.spentManager();
                    break;

                case "5":
                    reminderManager.manageSendingReminders();
                    break;

                case "6":
                    //Expense Tracking
                    break;
                case "7":
                    userServices.logOut();
                    System.exit(0); // Terminate the application
                    break;
                default:
                    System.out.println("Invalid! Please enter a valid number (1-7)");
                    break;
            }
        } else if (option.equals("2")) {

            while (true) {
                System.out.print("\n\t\t\t**Nazamha (Budgeting Application)**\n1- SignUp\n2- SignIn\n3- LogOut\n4- ManageSettings\n5- Add New Category\n6- Add New Expense\n7- Show Budget Analysis\n8-Check Reminders\nEnter Your choice (1-8): ");
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
                        System.out.print("Enter category name: ");
                        String category = scanner.nextLine();
                        System.out.print("Enter budget: ");
                        double budget = Double.parseDouble(scanner.nextLine());
                        budgetManager.addNewCategory(category, budget);
                        break;
                    case "6":
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
                    case "7":
                        budgetManager.spentManager();
                        break;
                    case "8":
                        reminderManager.manageSendingReminders();
                        break;
                    default:
                        System.out.println("Invalid! Please enter a valid number (1-8)");
                        break;
                }
            }        
        } else if (option.equals("3")) {
            System.out.println("Exiting");
            scanner.close();
            System.exit(0);
        }
        else {
            System.out.println("Invalid choice! Please enter (1-3)");
        }

        }
    }
}
