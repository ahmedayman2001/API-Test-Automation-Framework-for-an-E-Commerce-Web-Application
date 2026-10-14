package RequestBodyMessages;

/**
 * Represents the request body containing valid login credentials.
 */
public class ValidLogin {

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
     * Returns a string representation of the login credentials.
     *
     * @return a formatted string containing the email and password
     */
    @Override
    public String toString() {
        return "ValidLogin{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}