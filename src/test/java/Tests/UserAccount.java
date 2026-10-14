package Tests;

import RequestBodyMessages.*;
import ResponseBodyMessages.LoginWithoutEmail;
import ResponseBodyMessages.UserDetails;
import ResponseBodyMessages.UserDetailsResponse;
import TestData.RandomDataGenerator;
import Utils.HelperClass;
import Utils.PropertiesReader;
import io.qameta.allure.*;
import io.qameta.allure.internal.shadowed.jackson.core.type.TypeReference;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



/**
 * Contains API tests for user account operations on the Automation Exercise website.
 *
 * <p>The test class covers account registration, login validation, account deletion,
 * account updates, and retrieving user details by email. Test data is loaded from
 * JSON files, and Allure annotations provide reporting metadata.</p>
 */

@Listeners(MyListeners.CustomListener.class)

public class UserAccount {

    private RegisterUser createdUser;
    private UpdateUserAccount NewData;

    private String Domain = PropertiesReader.get("src/test/java/Properties/environment.properties", "Domain");

    private String CreateAccount = PropertiesReader.get("src/test/java/Properties/environment.properties", "CreateAccount");
    private String Login = PropertiesReader.get("src/test/java/Properties/environment.properties", "Login");
    private String DeleteAccount = PropertiesReader.get("src/test/java/Properties/environment.properties", "DeleteAccount");
    private String UpdateAccount = PropertiesReader.get("src/test/java/Properties/environment.properties", "UpdateAccount");
    private String UserDetailsByEmail = PropertiesReader.get("src/test/java/Properties/environment.properties", "UserDetails");

    /**
     * Registers the JSON response parser before executing the test methods.
     */
    @BeforeClass
    public void setup() {

        RestAssured.registerParser("text/html", Parser.JSON);
    }





    /// =============================================================================================================///





    /**
     * Loads the registration template and generates unique user credentials
     * for tests that require a prepared account.
     */
    @BeforeMethod(onlyForGroups = "needsAccount")
    public void CreateTestAccount() {

        RegisterUser test = HelperClass.readJson
                ("src/main/java/TestData/RegisterUserData.JSON", RegisterUser.class);

        test.setName(RandomDataGenerator.generateRandomName());
        test.setEmail(RandomDataGenerator.generateRandomEmail());
        test.setPassword(RandomDataGenerator.generateRandomPassword());
        createdUser = test;

    }

    /**
     * Verifies that POST /api/createAccount creates a user account successfully.
     *
     * <p>The test submits generated registration data as form parameters and
     * validates the HTTP status, response time, response body, and success message.</p>
     */
    @Description("Verify POST /api/createAccount returns 201 with a success message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Epic("Automation Exercise Website APIs")
    @Feature("Create/Register User Account")
    @Story("Creat new Account")
    @Test(groups = "needsAccount")

    public void API_7_POST_ToCreateRegisterUserAccount() {

        // Convert the registration object into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(createdUser, new TypeReference<Map<String, Object>>() {});

        given()

                .baseUri(Domain)
                .formParams(formData)

                .when()

                .post(CreateAccount)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(empty()))
                .assertThat().body("responseCode", equalTo(201)
                        , "message", containsString("User created!"));

    }






    /// =================================================================================================================///





    /**
     * Supplies valid login credentials from a JSON test-data file.
     *
     * @return an array of valid login test data
     */
    @DataProvider(name = "ValidLoginAccount")
    public ValidLogin[] DataDrivenValidLogin() {

        ValidLogin[] Test = HelperClass.readJson
                ("src/main/java/TestData/ValidLoginData.JSON", ValidLogin[].class);

        return Test;

    }

    /**
     * Verifies that POST /api/verifyLogin accepts valid login credentials.
     *
     * <p>The test is data-driven and checks the HTTP status, response body,
     * response time, and expected success message.</p>
     */
    @Description("Verify POST /api/verifyLogin returns 200 with a success message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Verify Login with valid details")
    @Story("Login with valid account ")

    @Test(dataProvider = "ValidLoginAccount")

    public void API_8_POST_ToVerifyLoginWithValidDetails(ValidLogin user) {

        // Convert the login credentials into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(user, new TypeReference<Map<String, Object>>() {});

        given()
                .baseUri(Domain)
                .formParams(formData)

                .when()

                .post(Login)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .assertThat().body("responseCode", equalTo(200)
                        , "message", containsString("User exists!"));

    }





    /// ==============================================================================================================///





    /**
     * Verifies that login validation returns an error when the email parameter is missing.
     *
     * <p>The response is deserialized into {@link LoginWithoutEmail} so that
     * its application-level response code and error message can be validated.</p>
     */
    @Description("Verify POST /api/verifyLogin returns 400 with an ERROR message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Verify Login without email parameter")
    @Story("invalid Login account")

    @Test
    public void API_9_POST_ToVerifyLoginWithoutEmailParameter() {

        // Submit the login request without the required email parameter.
        LoginWithoutEmail res = given()

                .baseUri(Domain)
                .formParams("password", "Pass@29735")

                .when()

                .post(Login)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .extract().as(LoginWithoutEmail.class);

        // Validate the application-level error response.
        int ExpectedCode = 400;
        String ExpectedMessage = "Bad request, email or password parameter is missing in POST request.";

        Assert.assertEquals(res.getResponseCode(), ExpectedCode);
        Assert.assertEquals(res.getMessage(), ExpectedMessage);

    }





    /// ================================================================================================================///





    /**
     * Verifies that DELETE /api/verifyLogin is rejected because the endpoint
     * does not support the DELETE HTTP method.
     */
    @Description("Verify DELETE /api/verifyLogin returns responseCode 405 with an unsupported method error message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Verify Login")
    @Story("Verify Unsupported HTTP Method")

    @Test

    public void API_10_DELETE_ToVerifyLogin() {

        given()
                .baseUri(Domain)

                .when()

                .delete(Login)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(empty()))
                .assertThat().body("responseCode", equalTo(405)
                        , "message", equalTo("This request method is not supported."));

    }





    /// ================================================================================================================///





    /**
     * Creates a new account before running tests in the {@code NewAccount} group.
     *
     * <p>Registration details are loaded from JSON, randomized, and submitted
     * to the account creation endpoint.</p>
     */
    @BeforeMethod(onlyForGroups = "NewAccount")
    public void CreateNewAccount() {

        RegisterUser test = HelperClass.readJson
                ("src/main/java/TestData/RegisterUserData.JSON", RegisterUser.class);

        test.setName(RandomDataGenerator.generateRandomName());
        test.setEmail(RandomDataGenerator.generateRandomEmail());
        test.setPassword(RandomDataGenerator.generateRandomPassword());
        createdUser = test;

        // Convert the generated account details into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(createdUser, new TypeReference<Map<String, Object>>() {});

        given()

                .baseUri(Domain)
                .formParams(formData)

                .when()

                .post(CreateAccount)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(empty()))
                .assertThat().body("responseCode", equalTo(201)
                        , "message", containsString("User created!"));

    }

    /**
     * Verifies that DELETE /api/deleteAccount deletes the newly created account.
     *
     * <p>The credentials used for deletion are taken from the account created
     * by the setup method.</p>
     */
    @Description("Verify DELETE /api/deleteAccount returns responseCode 200 with a success message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Delete User Account")
    @Story("Delete a Newly Created User Account")
    @Test(groups = "NewAccount")

    public void API_11_DELETE_METHODToDeleteUserAccount() {

        // Load the deletion request template and use the newly created account credentials.
        ValidDeleteAccount test_user = HelperClass.readJson
                ("src/main/java/TestData/DeleteAccountData.JSON", ValidDeleteAccount.class);
        test_user.setEmail(createdUser.getEmail());
        test_user.setPassword(createdUser.getPassword());

        // Convert the account credentials into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(test_user, new TypeReference<Map<String, Object>>() {});

        given()

                .baseUri(Domain)
                .formParams(formData)

                .when()
                .delete(DeleteAccount)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .assertThat().body("responseCode", equalTo(200)
                        , "message", equalTo("Account deleted!"));

    }






    /// ====================================================================================================================///





    /**
     * Prepares updated account data for tests in the {@code UpdateAccount} group.
     *
     * <p>The name and city are randomized to provide updated values for the request.</p>
     */
    @BeforeMethod(onlyForGroups = "UpdateAccount")
    public void UpdateAccountData() {

        UpdateUserAccount test = HelperClass.readJson
                ("src/main/java/TestData/UpdateUserAccountData.JSON", UpdateUserAccount.class);

        test.setName(RandomDataGenerator.generateRandomName());
        test.setCity(RandomDataGenerator.generateRandomCity());

        NewData = test;
    }

    /**
     * Verifies that PUT /api/updateAccount updates the user's account successfully.
     *
     * <p>The test submits the updated account data and validates the HTTP status,
     * response body, response time, application-level response code, and message.</p>
     */
    @Description("Verify PUT /api/updateAccount returns responseCode 200 with a success message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Update User Account")
    @Story("Update user account data like : name , city , age,...")
    @Test(groups = "UpdateAccount")

    public void API_12_PUT_METHOD_ToUpdateUserAccount() {

        // Convert the updated account object into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(NewData, new TypeReference<Map<String, Object>>() {});

        given()
                .baseUri(Domain)
                .formParams(formData)

                .when()

                .put(UpdateAccount)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .assertThat().body("responseCode", equalTo(200)
                        , "message", equalTo("User updated!"));

    }





    /// ================================================================================================================///





    /**
     * Verifies that GET /api/getUserDetailByEmail retrieves the expected user details.
     *
     * <p>The test checks that the returned email matches the requested email,
     * that the user object and ID are present, and that the password is not exposed.
     * Additional profile fields are validated using soft assertions.</p>
     */
    @Description("Verify GET /api/getUserDetailByEmail returns responseCode 200 with the user's details matching the requested email")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Get User Account Details")
    @Story("Retrieve user account details by email")
    @Test
    public void API_13_GET_UserAccountDetailByEmail() {

        // Load the email used to retrieve the account details.
        Email_UserDetails tester = HelperClass.readJson
                ("src/main/java/TestData/Email_Contain_User_Details_Data.JSON", Email_UserDetails.class);

        // Send the email as a query parameter and deserialize the response.
        UserDetailsResponse response = given()

                .baseUri(Domain)
                .queryParam("email", tester.getEmail())

                .when()

                .get(UserDetailsByEmail)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .assertThat().body("user", notNullValue())
                .assertThat().body("user.id", notNullValue())
                .assertThat().body("user.email", equalTo(tester.getEmail()))
                .assertThat().body(not(hasKey("password")))
                .assertThat().body(not(containsStringIgnoringCase("password")))
                .extract().as(UserDetailsResponse.class);

        // Verify the application-level response code.
        int ExpectedResponseCode = 200;
        Assert.assertEquals(response.getResponseCode(), ExpectedResponseCode);

        // Validate the returned profile fields without stopping at the first failure.
        SoftAssert soft = new SoftAssert();
        UserDetails User = response.getUser();

        soft.assertEquals(response.getResponseCode(), ExpectedResponseCode, "responseCode");
        soft.assertEquals(User.getName(), "ahmedayman", "name");
        soft.assertEquals(User.getCity(), "New Delhi", "city");
        soft.assertEquals(User.getAddress1(), "Street 456", "address1");
        soft.assertEquals(User.getAddress2(), "Building 8", "address2");
        soft.assertEquals(User.getCountry(), "India", "country");
        soft.assertEquals(User.getTitle(), "Mr", "title");

        // Report all soft assertion failures together.
        soft.assertAll();

    }





    /// ==================================================================================================================///




    /**
     * Supplies invalid login credentials from a JSON test-data file.
     *
     * @return an array of invalid login test data
     */
    @DataProvider(name = "InValidLoginAccount")
    public InvalidLogin[] DataDrivenInValidLogin() {

        InvalidLogin[] tester = HelperClass.readJson
                ("src/main/java/TestData/InvalidLoginData.JSON", InvalidLogin[].class);

        return tester;

    }

    /**
     * Verifies that POST /api/verifyLogin rejects invalid login credentials.
     *
     * <p>The test is data-driven and checks the HTTP status, response body,
     * response time, application-level response code, and expected error message.</p>
     */
    @Description("Verify POST /api/verifyLogin returns responseCode 404 with the message 'User not found!' when invalid login details are provided")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")    @Epic("Automation Exercise Website APIs")
    @Feature("Verify User Login")
    @Story("Verify login fails with invalid email and password")
    @Test(dataProvider = "InValidLoginAccount")

    public void API_14_POST_ToVerifyLoginWithInvalidDetails(InvalidLogin tester) {

        // Convert the invalid credentials into form parameters.
        Map<String, Object> formData = new ObjectMapper()
                .convertValue(tester, new TypeReference<Map<String, Object>>() {});

        given()

                .baseUri(Domain)
                .formParams(formData)

                .when()

                .post(Login)

                .then()

                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(empty()))
                .assertThat().time(lessThan(3000L))
                .assertThat().body("responseCode", equalTo(404)
                        , "message", equalTo("User not found!"));

    }






    /// ==================================================================================================================///






}