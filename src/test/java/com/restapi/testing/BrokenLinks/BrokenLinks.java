package com.restapi.testing.BrokenLinks;

import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class BrokenLinks {

    @Test
    public void checkBrokenLinks() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Surbhi_Modi\\Desktop\\REST_Assured\\REST_Assured\\src\\test\\resources\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("http://restservicestesting.blogspot.in/");
        List<String> hrefs = new ArrayList<String>();
        List<WebElement> anchors = driver.findElements(By.tagName("a"));
        for (WebElement anchor : anchors) {
            if (anchor.getAttribute("href") != null)
                hrefs.add(anchor.getAttribute("href"));
        }
        for (String href : hrefs) {
            try {
                int responseCode = returnStatusCode(new URL(href));
                if (responseCode != 200) {
                    System.out.println("The broken Link is " + href);
                } else {
                    System.out.println("The working Link is " + href);
                }
            } catch (Exception e) {
                System.out.println("URL: " + href + " returned " + e.getMessage());
            }
        }

    }


    public int returnStatusCode(URL url) {
        Response resp = given().
                when().
                get(url);
        return resp.getStatusCode();
    }

}
