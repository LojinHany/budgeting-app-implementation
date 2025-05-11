package usermanagement;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public User(String name, String emailAddress, String password, int phoneNumber){
        this.name=name;
        this.emailAddress= emailAddress;
        this.password= password;
        this.phoneNumber= phoneNumber;
    }

    private String name, emailAddress, password;
    private int phoneNumber;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmailAddress(){
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress){
        this.emailAddress= emailAddress;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}



