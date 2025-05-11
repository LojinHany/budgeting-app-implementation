package usermanagement;
import java.util.List;
import java.util.Scanner;

import persistenceLayer.UltraSimpleUserStorage;

public class UserServices extends User{ // inherits from user and uses useraccount
    private UserAccount userAccount; 
    private Scanner scanner = new Scanner(System.in);

    public UserServices(String name, String emailAddress, String password, int phoneNumber) {
        super(name, emailAddress, password, phoneNumber);
        this.userAccount = new UserAccount(this); // Initialize UserAccount with the current user
    }

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

            // check if email contains -> @
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

            

            // add and save user
            User newUser = new User(name, email, pass1, phone);
            allUsers.add(newUser);
            UltraSimpleUserStorage.saveUsers(allUsers);

            setName(name);
            setEmailAddress(email);
            setPassword(pass1);
            setPhoneNumber(phone);

            System.out.println("SignUp Successful!\n");
            return true;

        }catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    public boolean signIn(){
        try{
            System.out.print("Enter your Email: ");
            String email = scanner.nextLine().trim();
            // check if email contains -> @
            while (!email.contains("@")) {
                System.out.println("Invalid email. Please include '@' in the email address.");
                System.out.print("Enter your email: ");
                email = scanner.nextLine().trim();
            }

            System.out.print("Enter your Password: ");
            String pass = scanner.nextLine();

            // check if email exists and pass is correct
            List<User> allUsers = UltraSimpleUserStorage.loadUsers();

            for (User u : allUsers) {
                if (u.getEmailAddress().equalsIgnoreCase(email)&& u.getPassword().equals(pass)) {
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

        }catch (Exception e) {
            System.out.println("Error during signup: " + e.getMessage());
            return false;
        }
    }

    public void logOut(){
        System.out.println("LogOut Successful!\n");
    }

    public void ManageSettings() {
        while(true){
            System.out.print("\n1- Profile\n2- Change Password\n3- Change Email\n4- Set Security \n5- Help And Support\n6- Exit \nEnter Your choice (1-4): ");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("\nYour Profile Details:");
                    System.out.println("Name: " +getName());
                    System.out.println("Email: " +getEmailAddress());
                    System.out.println("Phone Number: " +getPhoneNumber());
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
                case"6":
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option (1-4).");
                    break;
            }
        }
    }
    
}
