package selenium;

import java.io.IOException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class events {
	public static void main(String[] args) throws IOException {
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);

			driver.manage().window().maximize();
			driver.get("http://demo.guru99.com/test/simple_context_menu.html");
			Actions act = new Actions(driver);
			WebElement link =driver.findElement(By.xpath("//button[text()='Double-Click Me To See Alert']"));
			act.doubleClick(link).perform();
			System.out.println(driver.switchTo().alert().getText());
	}
}
