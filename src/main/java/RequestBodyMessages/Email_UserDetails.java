package RequestBodyMessages;

/**
 * Represents the request body containing user email details.
 */
public class Email_UserDetails {

    private String email;

    /**
     * Retrieves the user's email address.
     *
     * @return the user's email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     *
     * @param email the email address to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns a string representation of the user's email details.
     *
     * @return a formatted string containing the email address
     */
    @Override
    public String toString() {
        return "Email_UserDetails{" +
                "email='" + email + '\'' +
                '}';
    }
}