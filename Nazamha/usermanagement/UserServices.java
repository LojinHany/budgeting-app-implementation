package usermanagement;

import java.util.List;
import java.util.Scanner;
import persistenceLayer.UltraSimpleUserStorage;

/**
 * This class provides services related to user sign-up, sign-in, and managing user settings.
 * It handles user account creation, authentication, and allows users to manage their profile and security settings.
 */
public class UserServices extends User {
    private UserAccount userAccount;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new UserServices object for the specified user.
     * It initializes the associated UserAccount for managing user-related operations.
     *
     * @param name The name of the user.
     * @param emailAddress The email address of the user.
     * @param password The password of the user.
     * @param phoneNumber The phone number of the user.
     */
    public UserServices(String name, String emailAddress, String password, int phoneNumber) {
        super(name, emailAddress, password, phoneNumber);
        this.userAccount = new UserAccount(this); // Initialize UserAccount with the current user
    }

    /**
     * Handles user sign-up process, including checking for existing users, 
     * validating email, password, and confirming the details.
     *
     * @return {@code true} if sign-up is successful, otherwise {@code false}.
     */
    public boolean signUp() {
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter your email: ");
            String email = scanner.nextLine().trim();
            
            // Load existing users
            List<User> allUsers = UltraSimpleUserStorage.loadUsers();

            // Check for duplicate email
            for (User u : allUsers) {
                if (u.getEmailAddress().equalsIgnoreCase(email)) {
                    System.out.println("Invalid! User with this email already exists\n");
                    return false;
                }
            }

            // Validate email format
            while (!email.contains("@")) {
                System.out.println("Invalid email. Please include '@' in the email address.");
                System.out.print("Enter your email: ");
                email = scanner.nextLine().trim();
            }

            System.out.print("Enter your phone number: ");
            int phone = scanner.nextInt();
            scanner.nextLine(); // Consume leftover newline

            System.out.print("Create your password: ");
            String pass1 = scanner.nextLine();
            while (!userAccount.validPass(pass1)){
                System.out.print("Create your password: ");
                pass1 = scanner.nextLine();
            }

            System.out.print("Confirm your password: ");
            String pass2 = scanner.nextLine();

            while (!pass1.equals(pass2)) {
                System.out.println("Passwords do not match. Signup failed.\nPlease Recreate your Password");
                System.out.print("Create your password: ");
                pass1 = scanner.nextLine();
                while (!userAccount.validPass(pass1)){
                    System.out.print("Create your password: ");
                    pass1 = scanner.nextLine();
                }
                System.out.print("Confirm your password: ");
                pass2 = scanner.nextLine();
            }

            // Add and save user
            User newUser = new User(name, email, pass1, phone);
            allUsers.add(newUser);
            UltraSimpleUserStorage.saveUsers(allUsers);

            setName(name);
            setEmailAddress(email);
            setPassword(pass1);
            setPhoneNumber(phone);

            System.out.println("SignUp Successful!\n");
            return true;

        } catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    /**
     * Handles user sign-in process, including email and password verification.
     *
     * @return {@code true} if sign-in is successful, otherwise {@code false}.
     */
    public boolean signIn() {
        try {
            System.out.print("Enter your Email: ");
            String email = scanner.nextLine().trim();
            
            // Validate email format
            while (!email.contains("@")) {
                System.out.println("Invalid email. Please include '@' in the email address.");
                System.out.print("Enter your email: ");
                email = scanner.nextLine().trim();
            }

            System.out.print("Enter your Password: ");
            String pass = scanner.nextLine();

            // Check if email exists and password is correct
            List<User> allUsers = UltraSimpleUserStorage.loadUsers();

            for (User u : allUsers) {
                if (u.getEmailAddress().equalsIgnoreCase(email) && u.getPassword().equals(pass)) {
                    setName(u.getName());
                    setEmailAddress(u.getEmailAddress());
                    setPassword(u.getPassword());
                    setPhoneNumber(u.getPhoneNumber());
                    System.out.println("SignIn Successful!\n");
                    return true;
                }
            }

            System.out.println("Invalid! Wrong Email/ Password\n");
            return false;

        } catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    /**
     * Logs out the user and prints a success message.
     */
    public void logOut() {
        System.out.println("LogOut Successful!\n");
    }

    /**
     * Provides a menu for managing user account settings including profile, 
     * changing password, updating email, setting security options, and customer support.
     */
    public void ManageSettings() {
        while (true) {
            System.out.print("\n1- Profile\n2- Change Password\n3- Change Email\n4- Set Security \n5- Help And Support\n6- Exit \nEnter Your choice (1-6): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\nYour Profile Details:");
                    System.out.println("Name: " + getName());
                    System.out.println("Email: " + getEmailAddress());
                    System.out.println("Phone Number: " + getPhoneNumber());
                    break;

                case "2":
                    userAccount.changePassword();
                    break;

                case "3":
                    userAccount.updateEmail();
                    break;

                case "4":
                    userAccount.setSecurityOptions();
                    break;

                case "5":
                    userAccount.helpAndSupport();
                    break;

                case "6":
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option (1-6).");
                    break;
            }
        }
    }
}
