package selenium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.commons.io.FileUtils;

import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class form_test {

	public static void main(String[] args) throws InterruptedException, IOException {

		try {
			//date definition
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String curr_date = dtf.format(now);
			
			//driver definition
			System.setProperty("webdriver.chrome.driver", "C://Users//admin//workspace//Auto/chromedriver.exe");
			WebDriver driver = new ChromeDriver();

			//wait call and define time for implicit wait
			WebDriverWait wait = new WebDriverWait(driver,20);
			
			//URL definition
			String baseUrl = "https://www.toolsqa.com/automation-practice-form/";
			driver.manage().window().maximize();
			//URL get
			driver.get(baseUrl);

			//This If block will execute when the title will matches given title in IF block.
			
			if (driver.getTitle().equals("Demo Form for practicing Selenium Automation")) {
				System.out.println("Got the intended page");

				// Cookie button will be clicked as accept to avoid further crashes.
				wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("cookie_action_close_header")));
				
				// First name
				driver.findElement(By.id("cookie_action_close_header")).click();
				driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Dipanshu");
				// Last name
				WebElement lname = driver.findElement(By.xpath("//input[@id='lastname']"));
				lname.clear();
				lname.sendKeys("Jha");
				// radio button gender
				driver.findElement(By.xpath("//input[@value='Male']")).click();
				// Radio button for experience
				driver.findElement(By.id("exp-4")).click();
				// date input as current date
				driver.findElement(By.xpath("//input[@id='datepicker']")).sendKeys(curr_date);
				// check box single select
				driver.findElement(By.id("profession-1")).click();
				// file upload by the file path
				driver.findElement(By.id("photo")).sendKeys("C://Users//dipanshu.jha//Downloads/findme.txt");
				// check box multiple selection
				String value = "Selenium Webdriver,Selenium IDE";
				List<WebElement> input2 = driver.findElements(By.xpath("//input[@type='checkbox']"));
				List<String> list = new ArrayList<String>(Arrays.asList(value.split(",")));
				for (String check : list) {
					for (WebElement chk : input2) {
						if (chk.getAttribute("value").equalsIgnoreCase(check)) {
							chk.click();
						}
					}
				}
				// drop down for continents
				Select drp = new Select(driver.findElement(By.id("continents")));
				drp.selectByVisibleText("Europe");
				// Screenshot will be stored at given location.
				File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				// Dynamic dates will be appended to filename as curr_date which has been defined at top.
				FileUtils.copyFile(screenshotFile, new File("D:\\SoftwareTestingMaterial" + curr_date + ".png"));
			} 
			// Else block will be executed when IF condition fails, means the title of web page is not intended.
			else {
				System.out.println("page not found, instead this page found: " + driver.getTitle());
				driver.quit();
			}
		} catch (NoSuchElementException e) {
			System.out.println("element exception Called " + e);
		}
	}
}
