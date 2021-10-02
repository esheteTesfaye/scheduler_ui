package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class loginLogout {

	WebDriver driver;

	@BeforeTest
	public void beforeTest() {

		System.setProperty("webdriver.chrome.driver", utility.PathList.chromeDriver);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("disable-extensions");
		options.addArguments("--headless");
		driver = new ChromeDriver(options);
		// driver = new HtmlUnitDriver(BrowserVersion.CHROME);
		// driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get("http://3.142.140.217/");
		// driver.manage().window().maximize();
		System.out.println("This is my test case");
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
		// driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		WebDriverWait w = new WebDriverWait(driver, 3);
		// presenceOfElementLocated condition
		w.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Logout")));
		driver.findElement(By.linkText("Logout")).click();

	}

	@DataProvider
	public Object[][] userNameandPassword() {
		return new Object[][] { new Object[] { "oneshete@gmail.com", "123456" }, };
	}

}
