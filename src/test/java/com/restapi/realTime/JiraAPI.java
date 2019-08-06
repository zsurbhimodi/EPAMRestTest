package com.restapi.realTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class JiraAPI {
	String sessionId,bugId,commentId,cookie="";

	@BeforeTest
	public void getCookie() {
        RestAssured.baseURI="http://localhost:8080";
    	
        Response res = given().contentType("application/json").      
        body("{ \"username\": \"Surbhimodi\", \"password\": \"5April@2018\" }").
    	when().post("/rest/auth/1/session").
    	then().assertThat().statusCode(200).and().
    	contentType(ContentType.JSON).and().
    	body("session.name",equalTo("JSESSIONID")).extract().response();
        
        String responseData = res.asString();  // because it comes in raw format which is bit difficult to read
    	System.out.println(responseData);
    	
        JsonPath jsonPath = new JsonPath(responseData);
        sessionId = jsonPath.getString("session.value");
        cookie="JSESSIONID="+sessionId;
	}
	
	@Test(priority=0)
	public void createBug() {
        RestAssured.baseURI="http://localhost:8080";


        Response res = given().
        contentType("application/json").
        header("Cookie", cookie).
        body("{\r\n" + 
        		"    \"fields\": {\r\n" + 
        		"       \"project\":\r\n" + 
        		"       {\r\n" + 
        		"          \"key\": \"RES\"\r\n" + 
        		"       },\r\n" + 
        		"       \"summary\": \"Creating bug through rest assured\",\r\n" + 
        		"       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n" + 
        		"       \"issuetype\": {\r\n" + 
        		"          \"name\": \"Bug\"\r\n" + 
        		"       }\r\n" + 
        		"   }\r\n" + 
        		"}").
    	when().post("/rest/api/2/issue").
    	then().assertThat().statusCode(201).and().
    	contentType(ContentType.JSON).and().extract().response();
        
        String responseData = res.asString();  // because it comes in raw format which is bit difficult to read
    	System.out.println(responseData);
    	
        JsonPath jsonPath = new JsonPath(responseData);
        bugId = jsonPath.getString("id");
	}
	
	@Test(priority=1)
	public void addComment() {
        RestAssured.baseURI="http://localhost:8080";


        Response res = given().
        contentType("application/json").
        header("Cookie", cookie).
        body("{\r\n" + 
        		"    \"body\": \"This is a comment from Rest Assured Automation\",\r\n" + 
        		"    \"visibility\": {\r\n" + 
        		"        \"type\": \"role\",\r\n" + 
        		"        \"value\": \"Administrators\"\r\n" + 
        		"    }\r\n" + 
        		"}").
    	when().post("/rest/api/2/issue/"+bugId+"/comment").
    	then().assertThat().statusCode(201).and().
    	contentType(ContentType.JSON).and().extract().response();
        
        String responseData = res.asString();  // because it comes in raw format which is bit difficult to read
    	System.out.println(responseData);
    	
        JsonPath jsonPath = new JsonPath(responseData);
        commentId = jsonPath.getString("id");
	}
	
	@Test(priority=2)
	public void updateComment() {
        RestAssured.baseURI="http://localhost:8080";


        given().
        contentType("application/json").
        header("Cookie", cookie).
        body("{\r\n" + 
        		"    \"body\": \"This is a comment updated from Rest Assured Automation\",\r\n" + 
        		"    \"visibility\": {\r\n" + 
        		"        \"type\": \"role\",\r\n" + 
        		"        \"value\": \"Administrators\"\r\n" + 
        		"    }\r\n" + 
        		"}").
    	when().put("/rest/api/2/issue/"+bugId+"/comment/"+commentId).
    	then().assertThat().statusCode(200).and().
    	contentType(ContentType.JSON);
        
	}
	
	
	@Test(priority=3)
	public void deleteComment() {
        RestAssured.baseURI="http://localhost:8080";


        given().
        contentType("application/json").
        header("Cookie", cookie).
    	when().delete("/rest/api/2/issue/"+"10012").
    	then().assertThat().statusCode(204);
	}
}
