package RequestBodyMessages;

/**
 * Represents the request body containing valid credentials for account deletion.
 */
public class ValidDeleteAccount {

    private String email;
    private String password;

    /**
     * Retrieves the user's email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the user's password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the account deletion credentials.
     *
     * @return a formatted string containing the email and password
     */
    @Override
    public String toString() {
        return "ValidDeleteAccount{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}