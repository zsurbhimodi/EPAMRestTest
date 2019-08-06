package com.restapi.testing.SpecificationBuilder;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class RequestSpecificationBuilder {

    RequestSpecification rspec;
    RequestSpecBuilder build;

    @BeforeClass
    public void requestSpec () {

        build = new RequestSpecBuilder();
        build.setBaseUri ("http://localhost:3000/posts");
        build.setContentType(ContentType.JSON);
        rspec = build.build ();

    }

    @Test
    public void test01 () {

        given()
                .spec (rspec)
                .when ()
                .get ("")
                .then ()
                .contentType (ContentType.JSON)
                .statusCode (200);
    }

}
