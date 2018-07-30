package com.test;

import org.testng.annotations.Test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

public class TestDemo {
	public WebDriver driver;
	String url="http://www.baidu.com/";
	
  @Parameters("browser")
  @BeforeClass
  public void beforeTest(String Browser) {	  
	  if(Browser.equalsIgnoreCase("chrome")){
		  System.setProperty("webdriver.chrome.driver", "D:\\资源文件\\selenium\\chromedriver.exe");
		  driver=new ChromeDriver();
		  Reporter.log("打开chrome浏览器");
	  }else{
		  driver=new FirefoxDriver();
		  Reporter.log("打开firefox浏览器");
	  }
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  driver.manage().window().maximize();
  }
  
  @Test
  public void Test() {
	  driver.get(url);
	  Reporter.log("打开浏览器");
  }
  @Test
  public void getPageSource(){
	  driver.get(url);
	  String pageSource = driver.getPageSource();
	  System.out.println(pageSource);
	  //断言页面源代码是否包含“百度”
	  Assert.assertTrue(pageSource.contains("百度"));
  }
  @Test
  public void doubleClick(WebElement element){
	  Actions actions = new Actions(driver);
	  actions.doubleClick(element).build().perform();
  }
  @Test
  public void checkSelectText(WebElement element){
	  Select dropList = new Select(element);
	  //存储期望值
	  List<String> expect_options = Arrays.asList(new String[]{"",""});
	  List<String> actual_options = new ArrayList<String>();
	  for(WebElement options:dropList.getOptions()){
		  actual_options.add(options.getText());
	  }
	  Assert.assertEquals(actual_options.toArray(), expect_options.toArray());
  }
  @Test
  public void operateRadio(WebElement element){
	  if(!element.isSelected()){
		  element.click();
	  }
  }
  
  public void executeJavaScript(String s){
	  //申明一个javascript执行器对象
	  JavascriptExecutor js = (JavascriptExecutor)driver;
	  js.executeScript(s);
  }

  @AfterClass
  public void afterTest() {
	  driver.close();
  }

}
