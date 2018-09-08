package com.test;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestBaidu {
	WebDriver driver ;
	
	@BeforeClass
	public void setUp(){
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void testOne(){
		driver.get("http://www.baidu.com");
		driver.findElement(By.id("kw")).sendKeys("自动化");
		driver.findElement(By.id("su")).click();
		Assert.assertTrue(driver.getTitle().contains("自动化"));
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
	}

}
