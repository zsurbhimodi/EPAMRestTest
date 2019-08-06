package com.restapi.testing.FilterInAPI;

import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.PrintStream;
import java.io.StringWriter;

import static io.restassured.RestAssured.given;

public class FilterTest {
    public static StringWriter requeststringWriter;
    public static PrintStream requestprintStream;


    public static StringWriter responsestringWriter;
    public static PrintStream responseprintStream;



    public static StringWriter errorstringWriter;
    public static PrintStream errorprintStream;

    @BeforeMethod
    public void beforeEachTest() {

        requeststringWriter = new StringWriter();
        requestprintStream = new PrintStream(new WriterOutputStream(requeststringWriter), true);

        responsestringWriter = new StringWriter();
        responseprintStream = new PrintStream(new WriterOutputStream(responsestringWriter), true);


        errorstringWriter = new StringWriter();
        errorprintStream = new PrintStream(new WriterOutputStream(errorstringWriter), true);

    }


   // @Test
    public void Test_04() {


        Response res = (Response) given().
                filter(new RequestLoggingFilter(requestprintStream)).
                filter(new ResponseLoggingFilter(requestprintStream))
                .param("appid", "c6e95d8d3aa5622408987cd2a69ea698").
                param("q", "London").
                when().
                get("https://samples.openweathermap.org/data/2.5/weather");

        System.out.println(res.asString());

        System.err.println(requeststringWriter.toString());
        System.err.println(responsestringWriter.toString());



    }

    @Test
    public void Test_044() {


        Response res = (Response) given().
                filter(new ErrorLoggingFilter(errorprintStream)).

                param("appid", "c6e95d8d3aa5622408987cd2a69ea698").
                        param("q", "London").
                        when().
                        get("https://samples.openweathermap.org/data/2.5/weather1");

       // System.out.println(res.asString());

        System.err.println(errorstringWriter.toString().toUpperCase());
       // System.err.println(responsestringWriter.toString());



    }
}

