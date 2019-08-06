package com.restapi.testing.JsonSchemaValidator;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JSONValidator {

    @BeforeClass
    public void setBaseUri () {

        RestAssured.baseURI = "https://maps.googleapis.com";
    }


    @Test
    public void validateJSON () {

        given().param ("query", "Churchgate Station")
                .param ("key", "XYZ")
                .when ()
                .get ("/maps/api/place/textsearch/json")
                .then()
                .body (matchesJsonSchemaInClasspath("C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\src\\test\\java\\com\\restapi\\testing\\JsonSchemaValidator\\jsonValidator.json"));
    }
}
