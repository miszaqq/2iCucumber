package uk.co.edgewords.APITests;

import org.junit.jupiter.api.Test;

public class APIScratch {


    @Test
    void api(){
        var req = RestAssured.given().baseUri("http://localhost:2002");
        var res = req.when().get("/api/products");
        var val = res.then();
        val.assertThat().statusCode(200).log().body();


    }
}
