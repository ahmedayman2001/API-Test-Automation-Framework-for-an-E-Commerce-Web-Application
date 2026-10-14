package ResponseBodyMessages;

/**
 * Represents the API response containing a response code and user details.
 */
public class UserDetailsResponse {

    private int responseCode;
    private UserDetails user;

    /**
     * Retrieves the API response code.
     *
     * @return the response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Retrieves the user details included in the response.
     *
     * @return the user details
     */
    public UserDetails getUser() {
        return user;
    }

    /**
     * Returns a string representation of the user details response.
     *
     * @return a formatted string containing the response code and user details
     */
    @Override
    public String toString() {
        return "UserDetailsResponse{" +
                "responseCode=" + responseCode +
                ", user=" + user +
                '}';
    }
}