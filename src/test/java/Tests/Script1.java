//package Tests;
//
//import ResponseBodyMessages.AccessTokenResponse;
//import RequestBodyMessages.Client;
//import Utils.PropertiesReader;
//import io.qameta.allure.*;
//import io.restassured.http.ContentType;
//import org.testng.Assert;
//import org.testng.annotations.Listeners;
//import org.testng.annotations.Test;
//
//import static io.restassured.RestAssured.* ;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.* ;
//
//
//@Listeners(MyListeners.CustomListener.class)
//public class Script1 {
//
//
//
//
//
//
//    @Description("This Test To verify that increasing the product quantity on the product detail page reflects accuratdy\n" +
//            "with the exact quantity in the shopping cart.")
//    @Owner("Ahmed_Ayman")
//    @Severity(SeverityLevel.CRITICAL)
//    @Link(name = "Test cases documentation" , url ="https://www.automationexercise.com/test_cases")
//    @TmsLink("https://www.automationexercise.com/test_cases")
//    @Epic("CartAndProductPlacement_Tests")
//    @Feature("Verify Product quantity in Cart")
//    @Story("As a user, I want to increase the product quantity and verify it in the cart")
//
//    @Test
//    public void getbooks(){
//
////        String data = "{\n" +
////                "   \"clientName\": \"testUser\",\n" +
////                "   \"clientEmail\": \"vl23g91@example.com\"\n" +
////                "}" ;
//
////        HashMap<String,String> body = new HashMap<>();
////        body.put("clientName", "testUser");
////        body.put("clientEmail","vl23g091@example.com");
//
//        Client body = new Client("TestUser" , "vHH9ftdtwddekkfeddfe01@example.com");
//
//        String url = PropertiesReader.get("src/test/java/Properties/environment.properties", "baseUrl");
//
//        ResponseBodyMessages.AccessTokenResponse token = (AccessTokenResponse) given()
//                .baseUri(url)
//                //.header("Authorization","Bearer b9773506b7f719b8fc63061ef72fafb3593b70317a25457ec202608003267f04")
//                //.auth().oauth2("b9773506b7f719b8fc63061ef72fafb3593b70317a25457ec202608003267f04")
//                //.param()
//
//               .contentType(ContentType.JSON)
//                .body(body)
//
//                .when()
//                .post("/api-clients/")
//               .then()
//               //.log().all()
//               .assertThat()
//               .statusCode(201)
//               .header("Content-Type" , "application/json")
//               .time(lessThan(5000L))
//               .extract().as(AccessTokenResponse.class);
//        Assert.assertNotNull(token);
//               //.body("error" , equalTo("API client already registered. Try a different email.")).extract().as(AccessTokenResponse.class);
//                System.out.println(token.getAccessToken());
//               // String token  = res.path("accessToken");
//               // System.out.println(res);
//                //.body("[0].name",equalTo("The Russian"));
//                //headers("Connection" , "keep-alive" , "Content-Length" , "600") ;
//                //.statusCode(201);
//
//    }
////Content-Type: application/json
//}
