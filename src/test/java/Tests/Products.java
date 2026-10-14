package Tests;

import Utils.PropertiesReader;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;



/// ==============================================================================================================




/**
 * Contains API tests for product and brand endpoints of the Automation Exercise website.
 * Validates response status codes, response bodies, product data, brand data, and response times.
 */

@Listeners(MyListeners.CustomListener.class)

public class Products {

    private String domain = PropertiesReader.get("src/test/java/Properties/environment.properties", "Domain");
    private String ProductList = PropertiesReader.get("src/test/java/Properties/environment.properties", "ProductsList");
    private String BrandList = PropertiesReader.get("src/test/java/Properties/environment.properties", "BrandList");
    private String SearchProduct = PropertiesReader.get("src/test/java/Properties/environment.properties", "SearchProduct");



    /**
     * Configures Rest Assured to parse HTML content as JSON for API responses.
     */
    @BeforeClass
    public void setup() {

        RestAssured.registerParser("text/html", Parser.JSON);
    }




    ///================================================================================================================///





    /**
     * Verifies that GET /api/productsList returns a successful response
     * containing a non-empty product list with valid product details and unique IDs.
     */
    @Description("Verify GET /api/productsList returns 200 with a valid, non-empty products list")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("Get All Products List")
    @Story("Get all products list")
    @Test

    public void API_1_GetAllProductsList() {

        Response response = given()
                .baseUri(domain)

                .when()
                .get(ProductList)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().header("Content-Type", containsString("text/html"))
                .assertThat().time(lessThan(3000L))
                .body("responseCode", equalTo(200),
                        "products", not(empty()),
                        "products.id", everyItem(notNullValue()),
                        "products.name", everyItem(notNullValue()),
                        "products.name", hasItems("Blue Top", "Men Tshirt"),
                        "products.price", everyItem(matchesPattern("^Rs\\. \\d+$")),
                        "products.brand", everyItem(not(emptyOrNullString())),
                        "products.category.category", everyItem(not(emptyOrNullString())),
                        "products.category.usertype.usertype", everyItem(in(List.of("Men", "Women", "Kids"))))
                .extract().response();

        // Verify that every product has a unique ID.
        List<Integer> ids = response.path("products.id");
        assertThat("Duplicate IDs found", ids, is(equalTo(new ArrayList<>(new LinkedHashSet<>(ids)))));

        // Log the response time for performance monitoring.
        System.out.println("Response time: " + response.getTime() + " ms");
    }






    /// ==============================================================================================================






    /**
     * Verifies that POST /api/productsList returns an API error response
     * when an unsupported HTTP method is used.
     */
    @Description("Verify POST /api/productsList returns 405 with an error message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("POST To All Products List")
    @Story("Invalid Method")
    @Test

    public void API_2_POSTToAllProductsList() {

        given()
                .baseUri(domain)

                .when()
                .post(ProductList)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(emptyOrNullString()))
                .assertThat().body("responseCode", equalTo(405),
                        "message", containsString("This request method is not supported."))
                .assertThat().body(not(emptyOrNullString()))
                .assertThat().time(lessThan(3000L));
    }





    /// ==============================================================================================================





    /**
     * Verifies that GET /api/brandsList returns a successful response
     * containing a non-empty list of brands with valid details and unique IDs.
     */
    @Description("Verify GET /api/brandsList returns 200 with non-empty brand list")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("Get All Brands List")
    @Story("Get all brands list")
    @Test

    public void API_3_GetAllBrandsList() {

        Response response = given()
                .baseUri(domain)

                .when()
                .get(BrandList)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(emptyOrNullString()))
                .assertThat().body("brands", not(empty()))
                .assertThat().body("responseCode", equalTo(200))
                .assertThat().body("brands.id", everyItem(notNullValue()),
                        "brands.brand", everyItem(not(emptyOrNullString())),
                        "brands.brand", hasItems("Polo", "H&M", "Babyhug", "Kookie Kids", "Biba"))
                .extract().response();

        // Verify that every brand has a unique ID.
        List<Integer> ids = response.path("brands.id");
        assertThat("Duplicate IDs found", ids, is(equalTo(new ArrayList<>(new LinkedHashSet<>(ids)))));

        // Log the response time for performance monitoring.
        System.out.println("ResponseTime = " + response.getTime() + " ms");
    }






    /// ==============================================================================================================





    /**
     * Verifies that PUT /api/brandsList returns an API error response
     * when an unsupported HTTP method is used.
     */
    @Description("Verify PUT /api/brandsList returns 405 with an error message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("PUT To All Brands List")
    @Story("Invalid Method")
    @Test

    public void API_4_PUTToAllBrandsList() {

        given()
                .baseUri(domain)

                .when()
                .put(BrandList)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().body(not(emptyOrNullString()))
                .assertThat().body("responseCode", equalTo(405),
                        "message", containsString("This request method is not supported."));
    }






    /// ==============================================================================================================





    /**
     * Verifies that POST /api/searchProduct returns matching products
     * when a valid search_product parameter is provided.
     * Also validates product details, expected search results, and unique product IDs.
     */
    @Description("Verify POST /api/searchProduct returns 200 with non-empty product list")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("POST To Search Product")
    @Story("Search for specific Product")
    @Test

    public void API_5_POSTToSearchProduct() {

        Response response = given()
                .baseUri(domain)
                .formParam("search_product", "tshirt")

                .when()
                .post(SearchProduct)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(emptyOrNullString()))
                .assertThat().body("responseCode", equalTo(200),
                        "products", not(empty()))
                .assertThat().body("products.id", everyItem(notNullValue()),
                        "products.name", everyItem(not(emptyOrNullString())),
                        "products.price", everyItem(not(emptyOrNullString())),
                        "products.price", everyItem(matchesPattern("^Rs\\. \\d+$")),
                        "products.brand", everyItem(not(emptyOrNullString())),
                        "products.category.usertype.usertype", everyItem(not(emptyOrNullString())),
                        "products.category", everyItem(not(emptyOrNullString())),
                        "products.name", hasItems("Men Tshirt",
                                "Pure Cotton V-Neck T-Shirt",
                                "Premium Polo T-Shirts"),
                        "products.brand", hasItems("H&M", "Polo"))
                .extract().response();

        // Verify that every returned product has a unique ID.
        List<Integer> ids = response.path("products.id");
        assertThat("Duplicate product IDs found",
                new HashSet<>(ids).size(),
                equalTo(ids.size()));

        // Log the response time for performance monitoring.
        System.out.println("Response Time = " + response.getTime() + " ms");
    }






    /// ==============================================================================================================






    /**
     * Verifies that POST /api/searchProduct returns an API error response
     * when the required search_product parameter is omitted.
     */
    @Description("Verify POST /api/searchProduct returns 400 with an error message")
    @Owner("Ahmed_Ayman")
    @Severity(SeverityLevel.NORMAL)
    @Link(name = "APIs documentation", url = "https://www.automationexercise.com/api_list")
    @Link(name = "Manual APIs Collections from Postman", url = "https://drive.google.com/file/d/1YAdmIwQM1gEgRiPfIZ0EEt6VtH0G3n-X/view?usp=sharing")
    @Epic("Automation Exercise Website APIs")
    @Feature("POST To Search Product")
    @Story("Search Product Without search_product Parameter")
    @Test

    public void API_6_POSTToSearchProductWithoutSearchProductParameter() {

        given()
                .baseUri(domain)

                .when()
                .post(SearchProduct)

                .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200)
                .assertThat().time(lessThan(3000L))
                .assertThat().body(not(empty()))
                .assertThat().body("responseCode", equalTo(400),
                        "message", containsString(
                                "Bad request, search_product parameter is missing in POST request."));
    }






    /// ==============================================================================================================///




}