package selenium;

import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class obj_repo {

	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.chrome.driver", "C://Users//admin//workspace//Auto/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String baseUrl = "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin";
		driver.manage().window().maximize();
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\app.properties");
		pro.load(file);
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		WebElement fld1 = driver.findElement(By.xpath(pro.getProperty("email_box_locator")));
		fld1.clear();
		fld1.sendKeys("user@email.com");
		WebElement clk1 = driver.findElement(By.xpath(pro.getProperty("next1")));
		clk1.click();
		WebDriverWait wait = new WebDriverWait(driver,20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(pro.getProperty("pass_locator"))));
		WebElement pass = driver.findElement(By.xpath(pro.getProperty("pass_locator")));
		pass.clear();
		pass.sendKeys("password");
		WebElement clk2 = driver.findElement(By.xpath(pro.getProperty("next2")));
		clk2.click();

	}

}
