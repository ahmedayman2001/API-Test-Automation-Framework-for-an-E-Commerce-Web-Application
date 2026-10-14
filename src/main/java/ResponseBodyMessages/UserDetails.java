package ResponseBodyMessages;

/**
 * Represents the user details returned in an API response.
 */
public class UserDetails {

    private int id;
    private String name;
    private String email;
    private String title;
    private String birth_day;
    private String birth_month;
    private String birth_year;
    private String first_name;
    private String last_name;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String state;
    private String city;
    private String zipcode;

    /**
     * Retrieves the user's unique identifier.
     *
     * @return the user ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param id the user ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the user's name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's name.
     *
     * @param name the user's name
     */
    public void setName(String name) {
        this.name = name;
    }

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
     * Retrieves the user's title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the user's title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the user's birth day.
     *
     * @return the birth day
     */
    public String getBirth_day() {
        return birth_day;
    }

    /**
     * Sets the user's birth day.
     *
     * @param birth_day the birth day
     */
    public void setBirth_day(String birth_day) {
        this.birth_day = birth_day;
    }

    /**
     * Retrieves the user's birth month.
     *
     * @return the birth month
     */
    public String getBirth_month() {
        return birth_month;
    }

    /**
     * Sets the user's birth month.
     *
     * @param birth_month the birth month
     */
    public void setBirth_month(String birth_month) {
        this.birth_month = birth_month;
    }

    /**
     * Retrieves the user's birth year.
     *
     * @return the birth year
     */
    public String getBirth_year() {
        return birth_year;
    }

    /**
     * Sets the user's birth year.
     *
     * @param birth_year the birth year
     */
    public void setBirth_year(String birth_year) {
        this.birth_year = birth_year;
    }

    /**
     * Retrieves the user's first name.
     *
     * @return the first name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * Sets the user's first name.
     *
     * @param first_name the first name
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Retrieves the user's last name.
     *
     * @return the last name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * Sets the user's last name.
     *
     * @param last_name the last name
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Retrieves the user's company.
     *
     * @return the company name
     */
    public String getCompany() {
        return company;
    }

    /**
     * Sets the user's company.
     *
     * @param company the company name
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * Retrieves the user's primary address.
     *
     * @return the first address line
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the user's primary address.
     *
     * @param address1 the first address line
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Retrieves the user's secondary address.
     *
     * @return the second address line
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the user's secondary address.
     *
     * @param address2 the second address line
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Retrieves the user's country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the user's country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Retrieves the user's state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the user's state.
     *
     * @param state the state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Retrieves the user's city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the user's city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Retrieves the user's ZIP or postal code.
     *
     * @return the ZIP or postal code
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     * Sets the user's ZIP or postal code.
     *
     * @param zipcode the ZIP or postal code
     */
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    /**
     * Returns a string representation of the user details.
     *
     * @return a formatted string containing the user information
     */
    @Override
    public String toString() {
        return "UserDetails{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", title='" + title + '\'' +
                ", birth_day='" + birth_day + '\'' +
                ", birth_month='" + birth_month + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}