package com.restapi.testng;

import org.testng.annotations.Test;

public class Timeout
{
    @Test(timeOut = 400)
    public void timeTestOne() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("Time test method one");
    }
 
    @Test(timeOut = 400)
    public void timeTestTwo() throws InterruptedException {
        Thread.sleep(300);
        System.out.println("Time test method two");
    }
}