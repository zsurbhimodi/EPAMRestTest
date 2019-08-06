package com.restapi.testing.payloadWithMap;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class payloadWithMap {

    //@Test
    public void TestwithMapAsPayLoad() {
        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap.put("id", "10");
        jsonAsMap.put("title", "The API Testing");
        jsonAsMap.put("author", "Surbhi Modi");


     Response response=   given().
                when().contentType(ContentType.JSON).
                body(jsonAsMap).
                post(" http://localhost:3000/posts").
                then().assertThat().statusCode(201).extract().response();
               System.out.println(response.asString());
    }

    @Test
    public void TestwithMapAsPayLoadWithComplexPayload() {
        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap.put("id", "101");
        jsonAsMap.put("title", "The API Testing");
        jsonAsMap.put("author", "Surbhi Modi");

        Map<String, Object> jsonAsMap2 = new HashMap<String, Object>();
        jsonAsMap2.put("email","TestAutomation@gmail.com");
        jsonAsMap2.put("phone","8888888888");
        jsonAsMap2.put("address","Pune");

        jsonAsMap.put("info",jsonAsMap2);

        Response response=   given().
                when().contentType(ContentType.JSON).
                body(jsonAsMap).
                post(" http://localhost:3000/posts").
                then().assertThat().statusCode(201).extract().response();
        System.out.println(response.asString());
    }

}
