package uk.co.edgewords.APITests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class BDDStaticExample {

    @Test
    void bddStatic() {
        given().baseUri("http://localhost:2002")
                .when().get("/api/products")
                .then().statusCode(200);
    }
}
