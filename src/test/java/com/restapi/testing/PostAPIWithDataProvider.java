package com.restapi.testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.restapi.Data.PayLoadPOST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostAPIWithDataProvider {
	String placeId= "";
	Properties prop= new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\Data\\TestData.properties");
		prop.load(fis);
	}
	
	
        @Test(dataProvider="address")
        public void validatePostApiJSON(String name, String language) {
    	
    	RestAssured.baseURI=prop.getProperty("HOST");
    	Response res = given().
    	queryParam("key",prop.getProperty("KEY")).
    	body(PayLoadPOST.getPOSTAddDynamic(name, language)).
    	when().post(prop.getProperty("ADD_RESOURCE_JSON")).
    	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
    	
    	String responseData = res.asString();// because it comes in raw format which is bit difficult to read
    	//System.out.println(responseData);
    	
        JsonPath jsonPath = new JsonPath(responseData);
    	placeId = jsonPath.getString("place_id");
    	 
	}
	
        @DataProvider(name="address")
        public Object[][] getDynamicValues() {
        	
        	return	new Object[][] {{"Your House", "Hindi"},{"My House", "Kumaoni"}};
        	
        	
        	
        }
}
