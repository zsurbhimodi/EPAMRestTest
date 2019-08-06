package com.restapi.Data;

public class PayLoadPOST {

	public static String getPOSTAdd() {
		
		String data = "{\r\n" + 
    			"    \"location\":{\r\n" + 
    			"        \"lat\" : -38.383494,\r\n" + 
    			"        \"lng\" : 33.427362\r\n" + 
    			"    },\r\n" + 
    			"    \"accuracy\":50,\r\n" + 
    			"    \"name\":\"Frontline house\",\r\n" + 
    			"    \"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
    			"    \"address\" : \"29, side layout, cohen 09\",\r\n" + 
    			"    \"types\": [\"shoe park\",\"shop\"],\r\n" + 
    			"    \"website\" : \"http://google.com\",\r\n" + 
    			"    \"language\" : \"French-IN\"\r\n" + 
    			"}\r\n" + 
    			"";
		
		return data;
	}
	
	public static String getPOSTAddDynamic(String name, String language) {
		
		String data = "{\r\n" + 
    			"    \"location\":{\r\n" + 
    			"        \"lat\" : -38.383494,\r\n" + 
    			"        \"lng\" : 33.427362\r\n" + 
    			"    },\r\n" + 
    			"    \"accuracy\":50,\r\n" + 
    			"    \"name\":\""+name+"\",\r\n" + 
    			"    \"phone_number\":\"(+91) 983 893 3937\",\r\n" + 
    			"    \"address\" : \"29, side layout, cohen 09\",\r\n" + 
    			"    \"types\": [\"shoe park\",\"shop\"],\r\n" + 
    			"    \"website\" : \"http://google.com\",\r\n" + 
    			"    \"language\" : \""+language+"\"\r\n" + 
    			"}\r\n" + 
    			"";
		
		return data;
	}
	
	public static String getPOSTDelete(String placeId) {
		
		String data = "{" +"\"place_id\":\""+ placeId + "\""+ 
    			"}";
		
		return data;
	}
}
