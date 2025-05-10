package usermanagement;

import java.util.Scanner;

import persistenceLayer.UltraSimpleUserStorage;

import java.util.List;
import java.util.Map;

public class UserAccount { // 1 to 1 multplicity with user and composition with user
    private User user; 
    private Scanner scanner = new Scanner(System.in);

    public UserAccount(User user) {
        this.user = user;
    }

    // check if pass is valid
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

            if (upperCase&&aNumber&&specialChar) {
                return true;
            }
        }

        System.out.println("Invalid! Password must include at least one uppercase letter, one number, and one special character.");
        return false;
    }    

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

            // Load all users
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

    public boolean updateEmail() {
        System.out.print("\nEnter current email: ");
        String currentEmail = scanner.nextLine();

        if (!user.getEmailAddress().equals(currentEmail)) {
            System.out.println("Incorrect current Email. Email change failed.");
            return false;
        }

        System.out.print("Enter new email: ");
        String email = scanner.nextLine().trim();
        // check if email contains -> @
        while (!email.contains("@")) {
            System.out.println("Invalid email. Please include '@' in the email address.");
            System.out.print("Enter new email: ");
            email = scanner.nextLine().trim();
        }

        user.setEmailAddress(email);
        System.out.println("Email successfully updated to: " + email);
        return true;
    }

    public boolean setSecurityOptions() {
        try {
            // Load existing PINs
            Map<String, String> userPins = UltraSimpleUserStorage.loadUserPins();

            System.out.print("\nEnter your email: ");
            String email = scanner.nextLine().trim();
            // check if the user already has a pin
            if (userPins.containsKey(email)) {
                System.out.println("A PIN already exists.");
                return false;
            }

            System.out.print("\nSet Your 4-digit Security Pin: ");
            String pin = scanner.nextLine().trim();

            // check if pin is 4 digits
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
            // Add user pin
            userPins.put(email, pin);
            UltraSimpleUserStorage.saveUserPins(userPins);

            System.out.println("Your PIN has been successfully set.");
            return true;

        }catch (Exception e) {
            System.out.println("Error during creating pin: " + e.getMessage());
            return false;
        }
    }

    public void helpAndSupport(){
        System.out.println("\nFor Customer Support call: 01150446626 /  01064830839\n");
    }
}
