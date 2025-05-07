package com.thetestingacademy.ex_04_RestAssured_HTTP_Methods.PATCH;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting012_PATCH_NONBddStyle {

    public String getToken() {
        String payload = "{ \"username\": \"admin\", \"password\": \"password123\" }";

        Response res = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com")
                .basePath("/auth")
                .contentType(ContentType.JSON)
                .body(payload)
                .post();

        return res.jsonPath().getString("token");
    }

    @Test
    public void test_patch_non_bdd() {

        String token = getToken();
        String bookingid = "4120";

        String payloadPatch = "{\n" +
                "    \"firstname\" : \"Lucky\",\n" +
                "    \"lastname\" : \"Brown\"\n" +
                "}";

        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/" + bookingid);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);

        requestSpecification.body(payloadPatch).log().all();

        Response response = requestSpecification.when().patch();

        ValidatableResponse validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);
    }
}
