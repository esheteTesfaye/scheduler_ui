package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class loginLogout {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", utility.PathList.chromeDriver);
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("http://3.142.140.217/");
		driver.manage().window().maximize();
	}

	@AfterTest
	public void closeTheBrowser() {
		driver.close();
	}

	@Test(dataProvider = "userNameandPassword")
	public void testLogin(String userName, String password) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.findElement(By.xpath("/html/body/div[1]/a[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys(userName);
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div[6]/label/form/input[3]")).click();
		String actualText = driver.findElement(By.xpath("//*[@id=\"banner\"]")).getText();
		String expectedText = "success: welcome, oneshete@gmail.com";
		Assert.assertEquals(expectedText, actualText);
		driver.findElement(By.xpath("/html/body/div[1]/a[2]")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Logout")).click();

	}

	@DataProvider
	public Object[][] userNameandPassword() {
		return new Object[][] { new Object[] { "oneshete@gmail.com", "123456" }, };
	}

}
