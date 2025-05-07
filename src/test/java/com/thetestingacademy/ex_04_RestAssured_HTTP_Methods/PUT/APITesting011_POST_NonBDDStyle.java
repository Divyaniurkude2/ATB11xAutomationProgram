package com.thetestingacademy.ex_04_RestAssured_HTTP_Methods.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting011_POST_NonBDDStyle {

    // PUT

    // token, booking id - A

//    public void get_token(){ }
//    public void get_booking_id(){}

    RequestSpecification r;
    Response response;
    ValidatableResponse vr;

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
    public void test_put_non_bdd(){

        String token = getToken();
        String bookingid = "4120";



        String payloadPUT = "{\n" +
                "    \"firstname\" : \"PK\",\n" +
                "    \"lastname\" : \"Kute\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        r = RestAssured.given();
        r.baseUri("https://restful-booker.herokuapp.com");
        r.basePath("/booking/" + bookingid);
        r.contentType(ContentType.JSON);
//        r.auth().oaut
        r.cookie("token", token);
        r.body(payloadPUT).log().all();

        response = r.when().log().all().put();


        vr = response.then().log().all();
        vr.statusCode(200);



    }
}
