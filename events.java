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
		String action = "popup";
		System.setProperty("webdriver.gecko.driver", "./geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		// System.setProperty("webdriver.chrome.driver",
		// WebDriver driver = new ChromeDriver();
		if (action.equals("drag")) {
			driver.manage().window().maximize();
			String baseURL = "https://demoqa.com/droppable/";
			driver.get(baseURL);
			WebElement from = driver.findElement(By.id("draggable"));
			WebElement to = driver.findElement(By.id("droppable"));
			Actions act = new Actions(driver);
			act.dragAndDrop(from, to).perform();
		} else if (action.equals("popup")) {
			driver.manage().window().maximize();
			String baseURL = "http://demo.guru99.com/test/delete_customer.php";
			driver.get(baseURL);
			driver.findElement(By.name("cusid")).sendKeys("2222");
			driver.findElement(By.name("submit")).click();

			Alert alert = driver.switchTo().alert();
			System.out.println("first alert accepted: " + alert.getText());
			alert.accept();
			wait.until(ExpectedConditions.alertIsPresent());
			System.out.println("second alert accepted: " + alert.getText());
			alert.accept();
		} else {
			System.out.println("kuch nahi hua");
			driver.quit();
		}
	}
}
