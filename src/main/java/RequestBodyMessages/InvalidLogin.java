package RequestBodyMessages;

/**
 * Represents the request body containing invalid login credentials.
 */
public class InvalidLogin {

    private String email;
    private String password;

    /**
     * Retrieves the email address.
     *
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password.
     *
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of the login credentials.
     *
     * @return a formatted string containing the email and password
     */
    @Override
    public String toString() {
        return "InvalidLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}