package usermanagement;

import java.io.Serializable;

/**
 * This class represents a user with personal information such as name, email address, 
 * password, and phone number. It implements {@link Serializable} to allow users to be 
 * serialized for storage or transmission.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private String emailAddress;
    private String password;
    private int phoneNumber;

    /**
     * Constructs a new User object with the specified information.
     *
     * @param name        The name of the user.
     * @param emailAddress The email address of the user.
     * @param password    The password of the user.
     * @param phoneNumber The phone number of the user.
     */
    public User(String name, String emailAddress, String password, int phoneNumber) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name to set for the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the email address of the user.
     *
     * @return The email address of the user.
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the email address of the user.
     *
     * @param emailAddress The email address to set for the user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Returns the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password The password to set for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phoneNumber The phone number to set for the user.
     */
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
