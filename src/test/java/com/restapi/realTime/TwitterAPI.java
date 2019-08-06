package com.restapi.realTime;

import com.restapi.logger.MyApp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class TwitterAPI {


	String tweetId="";
	String consumerKey="mp2G3hneRFQD2x8eHyKHnzfgn";
	String consumerSecret ="Tz3icaCr12kckrZlqLs3GFIyccMSGY4JmeWLToliZanUURMoTz";
	String accessToken ="2673478879-3PdFB03ILTv5sb4D2krXcMO68lJiEtO00POlv3z";
	String secretToken="OJWFRYLIOxpHQgLahECiYtvs8SgsUByv14b61im5xMjbT";
    private static final Logger logger = LogManager.getLogger(TwitterAPI.class);
	
	//@Test
	public void getLatestTweet() {
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
        Response response =given().auth().
        oauth(consumerKey, consumerSecret, accessToken, secretToken).
        		when().get("/home_timeline.json").
        then().extract().response();

        logger.info("The response details is"+response);
        
     /*  List<String> status = response.jsonPath().getList("text");       
      
       for(String s: status) {
    	   
    	   System.out.println(s);
       }
       */
       String status = response.jsonPath().getString("text");
       
       System.out.println(status);
        tweetId= response.jsonPath().getString("id[0]");
        logger.info("Tweet Id is"+tweetId);

	}
	//@Test
	public void postTweet() {
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";

        Response res = given().auth().
         oauth(consumerKey, consumerSecret, accessToken, secretToken).
         queryParam("status","Posting a tweet from REST Assured Automation").
         when().post("/update.json").
         then().assertThat().statusCode(200).extract().response();
     
        String s= res.asString();
        System.out.println(s);
        JsonPath j= new JsonPath(s);
        System.out.println(j.get("id"));
	}

	
	@Test
	public void deleteTweet() {
        RestAssured.baseURI="https://api.twitter.com/1.1/statuses";
        Response res = given().auth().
        oauth(consumerKey, consumerSecret, accessToken, secretToken).
        pathParams("tweetId","1149599485313441792").
        when().post("/destroy/{tweetId}.json").
        then().assertThat().statusCode(200).extract().response();
     
        String s= res.asString();
        System.out.println(s);
        getField(s);
    }

    private void getField(String s) {
        JsonPath j = new JsonPath(s);
        System.out.println(j.get("id"));
    }

}
