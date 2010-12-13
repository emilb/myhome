package se.greyzone.myhome.web.security;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AuthenticatedUser implements Serializable {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private String userId;

    public AuthenticatedUser(String firstName, String lastName, String login, String password, String email,
            String userId) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return getFirstName() + " " + getLastName();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
