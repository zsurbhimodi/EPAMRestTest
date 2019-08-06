package com.restapi.testing;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static org.hamcrest.core.IsEqual.*;

import static io.restassured.RestAssured.given;
public class GetAPI 
{
    @Test
    public void validateGetApi() {
    	
    	RestAssured.baseURI="https://api.openweathermap.org";


    	given().log().params().
    	param("zip","95050").
    	param("units","imperial").
    	param("appid","fd4698c940c6d1da602a70ac34f0b147").
    	when().
    	get("data/2.5/weather").
    	then().assertThat().statusCode(200).and().
    	contentType(ContentType.JSON).and().
    	body("weather[0].main",equalTo("Clouds")).and().
    	body("main.humidity",equalTo(60)).and().
    	header("Server", "openresty");



    	
    	
    }




}
