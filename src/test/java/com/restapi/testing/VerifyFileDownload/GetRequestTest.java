package com.restapi.testing.VerifyFileDownload;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class GetRequestTest {
    @Test
    public void verifyFileDownload() {
        int fileSize;
        File filePath = new File("C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\src\\rest-assured-3.0.5-dist.zip");
        fileSize = (int) filePath.length();
        System.out.println("The actual value is " + fileSize);
        byte[] expectedValue =
                given()
                        .get("http://dl.bintray.com/johanhaleby/generic/rest-assured-3.0.5-dist.zip")
                        .asByteArray();
        System.out.println("The expected value is " + expectedValue.length);
        Assert.assertEquals(fileSize, expectedValue.length);
    }
}
