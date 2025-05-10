import usermanagement.*;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserServices userServices = new UserServices(null, null, null, 0); 

        while (true) {
            System.out.print("\n\t\t\t**Nazamha (Budgeting Application)**\n1- SignUp\n2- SignIn\n3- LogOut\n4- ManageSettings\n5- Goal Tracker\nEnter Your choice (1-5): ");
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
                    userServices.goalTracker();
                    break;

                default:
                    System.out.println("Invalid! Please enter a valid number (1-5)");
                    break;
            }
        }
    }
}
