package com.restapi.testing.postreqwitharraywithaksonDependancy;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class PostRequestWithArrays {

    @Test
    public static void testRequestArray(){
        _Info info1=new _Info();
        info1.setEmail("test email6");
        info1.setAddress("test address6");
        info1.setPhone("test phone6");

        _Info info2=new _Info();

        info1.setEmail("test email5");
        info1.setAddress("test address5");
        info1.setPhone("test phone5");

        __Posts post1=new __Posts();
        post1.setAuthor("Surbhi");
        post1.setId("checkarrayupdate");
        post1.setTitle("The API Testing");
        post1.setInfo(new _Info[]{info1,info2});


     Response response= given().
                when().contentType(ContentType.JSON).
                body(post1).
                post(" http://localhost:3000/posts");

        System.out.println(response.asString());





    }

}
