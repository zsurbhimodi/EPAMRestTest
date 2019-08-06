package com.restapi.testing.SpecificationBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ResponseSpecificationBuilder {
    ResponseSpecBuilder builder;
    ResponseSpecification rspec;

    @BeforeClass
    public void requestSpec () {

        ResponseSpecBuilder builder = new ResponseSpecBuilder ();
        builder.expectContentType (ContentType.JSON);
        builder.expectStatusCode (200);

        rspec= builder.build ();

    }

    @Test
    public void Test01() {
     Response response=      given().
                when().get("http://localhost:3000/posts").
                then().spec(rspec).extract().response();

        System.out.println(response.asString());



    }

}
