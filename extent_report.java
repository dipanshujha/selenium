package selenium;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class report {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "C://Users//admin//workspace//Auto/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String baseUrl = "https://www.wufoo.com/html5/maxlength-attribute/";
		driver.manage().window().maximize();
		driver.get(baseUrl);
		WebElement text = driver.findElement(By.xpath("//input[@maxlength='10']"));
		String max = text.getAttribute("maxlength");
		System.out.println(max);
		ExtentReports report = new ExtentReports(System.getProperty("user.dir")+"\\result.html");
		ExtentTest test ;
		 report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
		 test = report.startTest("demo");
		 
		 if(max.equals("10"))
		{
			test.log(LogStatus.PASS, "passed");
			System.out.println("test passed");
		}
		else 
		{
			test.log(LogStatus.FAIL, "fail");
			System.out.println("test failed");
		}
		report.endTest(test);
		report.flush();
	}

}
