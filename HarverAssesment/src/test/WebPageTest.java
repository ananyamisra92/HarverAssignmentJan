package test;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class WebPageTest {

	static WebDriver driver;

	@BeforeClass
	public static void setBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"./Resources/chromedriver.exe");
		driver = new ChromeDriver(); 

	}

	@Before
	public void openPage() throws InterruptedException {
		driver.get("https://the-internet.herokuapp.com/");
		Thread.sleep(2000);
	}

	@Test
	public void checkboxValidator() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a")).click();
		Thread.sleep(3000);
		
		//Validate Checkbox isSelected method and click

		WebElement checkBoxElement = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
		
		Assert.assertFalse(checkBoxElement.isSelected());
		checkBoxElement.click();
		Assert.assertTrue(checkBoxElement.isSelected());
		
	}
	
	@Test
	public void dropdownValidator() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[11]/a")).click();
		Thread.sleep(3000);
		
		Select dropdownElement = new Select(driver.findElement(By.xpath("//*[@id=\"dropdown\"]")));
            //Selecting option as 'Option1'-- ByIndex
            dropdownElement.selectByIndex(1);
            System.out.println("Test Case Passed");
		}
	@Test
	public void brokenImageValidator() throws InterruptedException { 
		
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[4]/a")).click();
		Thread.sleep(3000);
		for (WebElement image : driver.findElements(By.cssSelector("img")))
		{
		    isImageBroken(image);
		}
	}
		public void isImageBroken(WebElement image)
		{
		    if (image.getAttribute("naturalWidth").equals("0"))
		    {
		        System.out.println(image.getAttribute("outerHTML") + " is broken.");
		    }
	}	
		@Test
		public void keypressValidator() throws InterruptedException {
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[31]/a")).click();
			Thread.sleep(3000);
			
			//Enter a key and validate

			driver.findElement(By.xpath("//*[@id=\"target\"]")).sendKeys("A");
			Thread.sleep(1000);
			Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"result\"]")).getText().equals("You entered: A"));
			
		}
		@Test
		public void formAuthenticator() throws InterruptedException {
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
			Thread.sleep(3000);
			
			//Enter a key and validate

			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"flash\"]")).isDisplayed();
			Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText(), "You logged into a secure area!\n"
					+ "×");
			
		}	
		
		
		
		@Test
		public void formAuthenticatorFailureWrongPassword() throws InterruptedException {
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
			Thread.sleep(3000);
			
			//Enter a key and validate

			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("tomsmith");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("wrongPassword");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"flash\"]")).isDisplayed();
			Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText(), "Your password is invalid!\n"
					+ "×");
			
		}	
		
		
		@Test
		public void formAuthenticatorFailureWrongUsername() throws InterruptedException {
			
			driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[21]/a")).click();
			Thread.sleep(3000);
			
			//Enter a key and validate

			driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("wrongusername");
			driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("SuperSecretPassword!");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button/i")).click();
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"flash\"]")).isDisplayed();
			Assert.assertEquals(driver.findElement(By.xpath("//*[@id=\"flash\"]")).getText(), "Your username is invalid!\n"
					+ "×");
			
		}	

	@AfterClass 
	 public static void CloseBrowser() {
	 
	 driver.close(); 
	 }
	



}
