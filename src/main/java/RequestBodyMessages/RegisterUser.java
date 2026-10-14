package RequestBodyMessages;

/**
 * Represents the request body containing user registration and account details.
 */
public class RegisterUser {

    private String name;
    private String email;
    private String password;
    private String title;
    private String birth_date;
    private String birth_month;
    private String birth_year;
    private String firstname;
    private String lastname;
    private String company;
    private String address1;
    private String address2;
    private String country;
    private String zipcode;
    private String state;
    private String city;
    private String mobile_number;

    // Getters and Setters

    /**
     * Retrieves the user's display name.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the user's display name.
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
     * Retrieves the user's birth date.
     *
     * @return the birth date
     */
    public String getBirth_date() {
        return birth_date;
    }

    /**
     * Sets the user's birth date.
     *
     * @param birth_date the birth date
     */
    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
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
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets the user's first name.
     *
     * @param firstname the first name
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retrieves the user's last name.
     *
     * @return the last name
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets the user's last name.
     *
     * @param lastname the last name
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
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
     * Retrieves the user's mobile number.
     *
     * @return the mobile number
     */
    public String getMobile_number() {
        return mobile_number;
    }

    /**
     * Sets the user's mobile number.
     *
     * @param mobile_number the mobile number
     */
    public void setMobile_number(String mobile_number) {
        this.mobile_number = mobile_number;
    }

    /**
     * Returns a string representation of the user's registration details.
     *
     * @return a formatted string containing the registration fields
     */
    @Override
    public String toString() {
        return "RegisterUser{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", birth_month='" + birth_month + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }
}