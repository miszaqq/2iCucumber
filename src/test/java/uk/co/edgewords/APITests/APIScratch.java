package uk.co.edgewords.APITests;
import io.restassured.RestAssured;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class APIScratch {


    @Test
    void api(){
        var req = RestAssured.given().baseUri("http://localhost:2002");
        var res = req.when().get("/api/products");
        var val = res.then();
        val.assertThat().statusCode(200).log().body();

    }
    @Test
    void mine(){

        //The TEST ABOVE can be done in one line
        RestAssured
                .given().baseUri("https://meedin3.pythonanywhere.com/")
                .when().get("/").then()
                .statusCode(200);

    }

    @Test
    void responsespec(){
        // Create a ResponseSpecification using ResponseSpecBuilder
        var responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectStatusLine("HTTP/1.1 200 OK")
                .expectContentType(ContentType.JSON)
                .expectResponseTime(Matchers.lessThan(5000L))
                .build();
        RestAssured.given().baseUri("http://localhost:2002")
                .when().get("/api/products")
                .then().spec(responseSpecification)
                .body("size()", Matchers.equalTo(3)) // 3 objects in array
                .log().body()
        //Rest Assured uses Groovy's GPath notation
        //https://github.com/rest-assured/rest-assured/wiki/Usage#example-1---json
                .body("[0].name", Matchers.equalTo("iPad")); //0th object in array name property
    }

    @Test
    void jsonpath(){
        RestAssured.given().baseUri("http://localhost:2002")
                .when().get("/api/products/1")
                .then().body("name", Matchers.equalTo("iPad"));
    }
}
