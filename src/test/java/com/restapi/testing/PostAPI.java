package com.restapi.testing;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.restapi.Data.LoggerClass;
import com.restapi.Data.PayLoadPOST;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.*;

import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class PostAPI {
	String placeId= "";
	Properties prop= new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		FileInputStream fis= new FileInputStream("C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\Data\\TestData.properties");
		prop.load(fis);
	}
	
	@Test(priority=0)
    public void validatePostApiJSON() {
    	
    	RestAssured.baseURI=prop.getProperty("HOST");
    	//Response res = given().log().all().
    	Response res = given().log().params().
    	queryParam("key",prop.getProperty("KEY")).
    	body(PayLoadPOST.getPOSTAdd()).
    	when().
    	post(prop.getProperty("ADD_RESOURCE_JSON")).
    	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		extract().response();
		String responseData = res.asString();

        System.out.println(res.getBody());
		System.out.println(responseData);
    	
    	/*String responseData = res.asString();// because it comes in raw format which is bit difficult to read
    	System.out.println(responseData);
    	
        JsonPath jsonPath = new JsonPath(responseData);
    	placeId = jsonPath.getString("place_id");*/
    	
    	placeId = res.path("place_id");
		System.out.println(placeId);
    	
		LoggerClass.log.error("just checking error log");
		LoggerClass.log.fatal("just checking fatal log");
		LoggerClass.log.info("just checking info log");
		LoggerClass.log.debug("just checking debug log");

    	 
	}
	
	
	
///	@Test(priority=0)
  /*  public void validatePostApiXML() throws IOException {
		
		String  xmlData= GenerateStringFromResource("C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\Data\\Payload.xml");
    	RestAssured.baseURI=prop.getProperty("HOST");
    	Response res = given().
				queryParam("key",prop.getProperty("KEY")).
    	body(xmlData).
    	when().post(prop.getProperty("ADD_RESOURCE_XML")).
    	then().assertThat().statusCode(200).and().contentType(ContentType.XML).and().
		body("response.status",equalTo("OK")).
		extract().response();
    	
    	String responseData = res.asString();// because it comes in raw format which is bit difficult to read
    	//System.out.println(" this is the response data recieved"+responseData);
    	
    	XmlPath xmlPath = new XmlPath(responseData);
        placeId = xmlPath.getString("reponse.place_id");
    	 
	}
	*/
	//@Test(priority=1)
    public void validateDeleteApi() {
   	    System.out.println(placeId);

		RestAssured.baseURI=prop.getProperty("HOST");
    	given().
    	queryParam("key",prop.getProperty("KEY")).
    	body(PayLoadPOST.getPOSTDelete(placeId)).
    	when().post(prop.getProperty("DELETE_RESOURCE")).
    	then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK"));    	
	}
	
	/*public static String GenerateStringFromResource(String Path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(Path)));
	}*/
}
