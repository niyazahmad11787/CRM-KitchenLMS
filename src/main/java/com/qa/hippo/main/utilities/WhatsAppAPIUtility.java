package com.qa.hippo.main.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class WhatsAppAPIUtility {
    private static final String BASE_URL = "https://crm-be.hippobetabox.in/api/v1/whatsapp/whatsapp_customer_reply";
    private static final String API_KEY = "LQ55siBGQIyarCYvVliv5L45Dd3cT0o_HlXoztk7iMU";
    private static final String BEARER_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjkxNjQwYzY3LTlkNGYtNDIzMC1hMTk3LWI2NWY2MWJkNWM5ZiIsInVzZXJuYW1lIjoiUG1Db21tb24iLCJlbWFpbCI6ImFobWFkQGhpcHBvc3RvcmVzLmNvbSIsImlhdCI6MTczNzUyMjc5MywiZXhwIjoxNzM3NjA5MTkzfQ.p5peAV2Ol_8TyKBvs3tHqzTIiFqr0eh4reih6jFHsFw";

    public static Response sendWhatsAppReply(String messageId,String number,String customerResponse) {

        Map<String, String> payload = new HashMap<>();
        payload.put("messageid", messageId);
        payload.put("usernumber",number);
        payload.put("customer_response",customerResponse);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .header("api-key", API_KEY)
                .header("X-API-Key", "Bearer " + BEARER_TOKEN)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post()
                .then()
                .extract().response();

        return response;
    }

    public static void main(String[] args) {
        String messageId = "wamid.HBgMOTE5MTI5ODk2Nzg2FQIAERgSQzQ4QUQxRUIyMDQ4REI4QjQ3AA==";
        String mobile="9129896786";
        String cusResponse="Yes I Approve";
        Response response = sendWhatsAppReply(messageId,mobile,cusResponse);
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
    }
}

