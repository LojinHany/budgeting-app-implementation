package usermanagement;

import java.util.Scanner;

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
            
        System.out.print("Confirm new password: ");
        String pass2 = scanner.nextLine();

        while (!pass1.equals(pass2)) {
            System.out.println("Passwords do not match. Signup failed.\nPlease Recreate your Password");
            System.out.print("Create your password: ");
            pass1 = scanner.nextLine();
            System.out.print("Confirm your password: ");
            pass2 = scanner.nextLine();
        }

        user.setPassword(pass1);
        System.out.println("Password successfully changed!");
        return true;
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
        return true;
    }

    public boolean updateSecurityOptions() {
        return true;
    }
}
