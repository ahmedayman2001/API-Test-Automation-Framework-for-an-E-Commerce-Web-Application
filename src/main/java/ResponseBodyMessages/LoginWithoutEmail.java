package ResponseBodyMessages;

/**
 * Represents the API response returned when a login request
 * is submitted without an email address.
 */
public class LoginWithoutEmail {

    private int responseCode;
    private String message;

    /**
     * Retrieves the API response code.
     *
     * @return the response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Retrieves the API response message.
     *
     * @return the response message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Returns a string representation of the API response.
     *
     * @return a formatted string containing the response code and message
     */
    @Override
    public String toString() {
        return "LoginWithoutEmail{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                '}';
    }
}