package com.restapi.testing.Assertions;

import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SoftAndHardAsserts {

    @Test
    public void Test_04() {


        Response res = (Response) given().
                        when().
                        get("http://localhost:3000/posts");

        System.out.println(res.asString());


        /*//Hard Assert
        res.then().body("id[0]",equalTo("2")).
                body("title[0]",equalTo("dummy Title")).
                body("author[0]",equalTo("Surbhi"));*/


        //Soft Assert
        res.then().body("id[0]",equalTo("xx"),"title[0]",equalTo("dummy Title"),"author[0]",equalTo("Surbhi1"));







    }

}
