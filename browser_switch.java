package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

public class cross_browser {

	public static void main(String[] args) {
		String browser = "chrome";			
		
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C://Users//admin//workspace//Auto/chromedriver.exe");
			WebDriver driver1 = new ChromeDriver();
			driver1.manage().window().maximize();
			driver1.get("https://demoqa.com/droppable/");
			WebElement from = driver1.findElement(By.id("draggable"));
			WebElement to = driver1.findElement(By.id("droppable"));
			Actions act = new Actions(driver1);
			act.dragAndDrop(from, to).perform();
			driver1.quit();
		}

		else if (browser.equals("firefox")) {

			System.setProperty("webdriver.gecko.driver", "C://Users//admin//workspace//Auto/geckodriver.exe");
			WebDriver driver2 = new FirefoxDriver();
			driver2.manage().window().maximize();
			driver2.get("https://demoqa.com/droppable/");
			WebElement from1 = driver2.findElement(By.id("draggable"));
			WebElement to1 = driver2.findElement(By.id("droppable"));
			Actions act1 = new Actions(driver2);
			act1.dragAndDrop(from1, to1).perform();

			driver2.quit();

		}

		else if (browser.equals("IE")) {
			System.setProperty("webdriver.ie.driver", "C://Users//admin//workspace//Auto//IEDriverServer.exe");
			WebDriver driver3 = new InternetExplorerDriver();
			driver3.manage().window().maximize();
			driver3.get("https://demoqa.com/droppable/");
			WebElement from2 = driver3.findElement(By.id("draggable"));
			WebElement to2 = driver3.findElement(By.id("droppable"));
			Actions act2 = new Actions(driver3);
			act2.dragAndDrop(from2, to2).perform();

			driver3.quit();
		} else {
			System.out.println("kuch nahi");
		}
		}
}
