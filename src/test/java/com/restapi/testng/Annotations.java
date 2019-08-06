package com.restapi.testng;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Annotations {

	@Test
	public void test() {		
		System.out.println("I am test");
	}
	
	@BeforeTest
    public void beforeTest() {		
		System.out.println("I am before test");
	}
	
	@BeforeMethod
    public void beforeMethod() {		
		System.out.println("I am before method");
	}
	
	@Test
	public void test2() {		
		System.out.println("I am test2");
	}
		
	@AfterTest
    public void afterTest() {		
		System.out.println("I am after test");
	}
	
	@AfterMethod
    public void afterMethod() {
		
		System.out.println("I am after method");
	}
}
