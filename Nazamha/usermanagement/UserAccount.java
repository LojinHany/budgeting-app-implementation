package usermanagement;

import java.util.Scanner;
import java.util.List;
import java.util.Map;

import persistenceLayer.UltraSimpleUserStorage;

/**
 * This class provides functionality for managing user account operations like changing 
 * passwords, updating email addresses, setting security options (PIN), and accessing support.
 */
public class UserAccount {
    private User user;
    private Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a new UserAccount object for the specified user.
     *
     * @param user The User object associated with the account.
     */
    public UserAccount(User user) {
        this.user = user;
    }

    /**
     * Validates the given password according to the following rules:
     * <ul>
     *   <li>Must be at least 8 characters long</li>
     *   <li>Must contain at least one uppercase letter</li>
     *   <li>Must contain at least one number</li>
     *   <li>Must contain at least one special character</li>
     * </ul>
     *
     * @param pass The password to validate.
     * @return {@code true} if the password is valid, otherwise {@code false}.
     */
    public boolean validPass(String pass) {
        if (pass.length() < 8) {
            System.out.println("Invalid! Please make sure Password is at least 8 characters long");
            return false;
        }

        boolean upperCase = false;
        boolean aNumber = false;
        boolean specialChar = false;

        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)){
                upperCase = true;
            } 
            else if (Character.isDigit(c)){
                aNumber = true;
            } 
            else if (!Character.isLetterOrDigit(c)){
                specialChar = true;
            } 

            if (upperCase && aNumber && specialChar) {
                return true;
            }
        }

        System.out.println("Invalid! Password must include at least one uppercase letter, one number, and one special character.");
        return false;
    }

    /**
     * Changes the password for the current user by validating the old password, entering a 
     * new one, and ensuring the new password matches the confirmation.
     *
     * @return {@code true} if the password was successfully changed, otherwise {@code false}.
     */
    public boolean changePassword() {
        try {
            System.out.print("\nEnter your email: ");
            String email = scanner.nextLine().trim();

            System.out.print("\nEnter current password: ");
            String currentPassword = scanner.nextLine();

            if (!user.getPassword().equals(currentPassword)) {
                System.out.println("Incorrect current password. Password change failed.");
                return false;
            }

            System.out.print("Enter new password: ");
            String pass1 = scanner.nextLine();
            while (!validPass(pass1)){
                System.out.print("Enter new password: ");
                pass1 = scanner.nextLine();
            }

            System.out.print("Confirm your password: ");
            String pass2 = scanner.nextLine();

            while (!pass1.equals(pass2)) {
                System.out.println("Passwords do not match.\nPlease Recreate your Password");
                System.out.print("Enter new password: ");
                pass1 = scanner.nextLine();
                while (!validPass(pass1)){
                    System.out.print("Enter new password: ");
                    pass1 = scanner.nextLine();
                }
                System.out.print("Confirm your password: ");
                pass2 = scanner.nextLine();
            }

            List<User> allUsers = UltraSimpleUserStorage.loadUsers();

            for (User u : allUsers) {
                if (u.getEmailAddress().equals(email)) {
                    u.setPassword(pass1);
                    break;
                }
            }
            UltraSimpleUserStorage.saveUsers(allUsers);

            System.out.println("Password successfully changed!");
            return true;
        } catch (Exception e) {
            System.out.println("Error during password change: " + e.getMessage());
            return false;
        }
    }

    /**
     * Updates the email address for the current user.
     *
     * @return {@code true} if the email was successfully updated, otherwise {@code false}.
     */
    public boolean updateEmail() {
        System.out.print("\nEnter current email: ");
        String currentEmail = scanner.nextLine();

        if (!user.getEmailAddress().equals(currentEmail)) {
            System.out.println("Incorrect current Email. Email change failed.");
            return false;
        }

        System.out.print("Enter new email: ");
        String email = scanner.nextLine().trim();
        while (!email.contains("@")) {
            System.out.println("Invalid email. Please include '@' in the email address.");
            System.out.print("Enter new email: ");
            email = scanner.nextLine().trim();
        }

        user.setEmailAddress(email);
        System.out.println("Email successfully updated to: " + email);
        return true;
    }

    /**
     * Allows the user to set up security options by configuring a PIN.
     * If a PIN already exists for the user, it prevents further setup.
     *
     * @return {@code true} if the PIN was successfully set, otherwise {@code false}.
     */
    public boolean setSecurityOptions() {
        try {
            Map<String, String> userPins = UltraSimpleUserStorage.loadUserPins();

            System.out.print("\nEnter your email: ");
            String email = scanner.nextLine().trim();
            if (userPins.containsKey(email)) {
                System.out.println("A PIN already exists.");
                return false;
            }

            System.out.print("\nSet Your 4-digit Security Pin: ");
            String pin = scanner.nextLine().trim();

            while (!pin.matches("\\d{4}")){
                System.out.println("Invalid! Please enter a 4-digit pin");
                System.out.print("\nSet Your 4-digit Security Pin: ");
                pin = scanner.nextLine().trim();
            }

            System.out.print("Confirm your pin: ");
            String pin2 = scanner.nextLine();

            while (!pin.equals(pin2)) {
                System.out.println("Invalid! Pins do not match.\nPlease Recreate your Pin");
                System.out.print("Set Your 4-digit Security Pin: ");
                pin = scanner.nextLine();

                while (!pin.matches("\\d{4}")){
                    System.out.println("Invalid! Please enter a 4-digit pin");
                    System.out.print("\nSet Your 4-digit Security Pin: ");
                    pin = scanner.nextLine().trim();
                }

                System.out.print("Confirm your pin: ");
                pin2 = scanner.nextLine();
            }
            userPins.put(email, pin);
            UltraSimpleUserStorage.saveUserPins(userPins);

            System.out.println("Your PIN has been successfully set.");
            return true;

        } catch (Exception e) {
            System.out.println("Error during creating pin: " + e.getMessage());
            return false;
        }
    }

    /**
     * Provides customer support information.
     */
    public void helpAndSupport() {
        System.out.println("\nFor Customer Support call: 01150446626 /  01064830839\n");
    }
}
