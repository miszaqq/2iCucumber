package uk.co.edgewords.APITests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;


public class DefaultRequestSpec {

    @BeforeAll
    public static void setupDefaultRequest(){
        RequestSpecification spec = given();
        spec.baseUri("http://localhost");
        spec.port(2002);
        spec.contentType(ContentType.JSON);
        requestSpecification = spec; //requestSpecification is defined static in io.restassured.RestAssured
    }

    @Test
    void testGetWithSpec(){
        RequestSpecification httpRequest = given().contentType("application/json");

        Response response = httpRequest.get("/api/products/2");

        String body = response.getBody().asString();
        MatcherAssert.assertThat(body, containsString("iPhone X"));

        int statusCode = response.statusCode();
        MatcherAssert.assertThat(statusCode, is(200));

        System.out.println("Response body => " + body);
    }

    @Test
    void bddGetStaticWithSpec(){
        when().get("/api/products/2")
                .then().statusCode(200)
                .statusLine("HTTP/1.1 200 OK")
                .body("name", equalTo("iPhone X"))
                .log().headers();
    }
}
