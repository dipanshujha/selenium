package selenium;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.apache.commons.io.FileUtils;


//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class script {

	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver", "C://Users//admin//workspace//Auto/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		String baseUrl = "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin";
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(baseUrl);
		try {
			driver.getTitle();
			if (driver.getTitle().equals("Sign in – Google accounts")) {
				System.out.println("Got the intended page");
				WebElement fld1 = driver.findElement(By.xpath("//input[@type='email']"));
				fld1.clear();
				fld1.sendKeys("email_user@internet.com");
				WebElement clk1 = driver.findElement(By.xpath("//span[@class='RveJvd snByac']"));
				clk1.click();
				Thread.sleep(2000);
				WebElement pass = driver.findElement(By.xpath("//input[@type='password']"));
				pass.clear();
				pass.sendKeys("password");
				WebElement clk2 = driver.findElement(By.xpath("//span[@class='RveJvd snByac'][1]"));
				clk2.click();
				Thread.sleep(2000);
				if (driver.getTitle().equals("Google Account")) {
					driver.navigate().to("https://mail.google.com/mail/u/0/#inbox");
					WebElement compose_btn = driver.findElement(By.xpath("//div[@gh='cm']"));
					compose_btn.click();
					Thread.sleep(3000);
					WebElement to = driver.findElement(By.xpath("//textarea[@name='to']"));
					to.sendKeys("reciever@gmail.com");
					WebElement sub = driver.findElement(By.xpath("//input[@name='subjectbox']"));
					sub.clear();
					sub.sendKeys("Subject");
					WebElement body = driver.findElement(By.xpath("//div[@aria-label='Message Body']"));
					body.clear();
					body.sendKeys("Body of mail");
					WebElement send = driver.findElement(By.xpath("//div[@aria-label='Send ‪(Ctrl-Enter)‬']"));
					send.click();
				} else {
					throw new Exception();
				}
			} else {
				System.out.println("page not found, instead this page found: " + driver.getTitle());
				driver.quit();
			}
		}

		catch (Exception e) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss");
			LocalDateTime now = LocalDateTime.now();
			String curr_date = dtf.format(now);
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshotFile, new File("D:\\Selenium_ss\\Errors\\error_"+curr_date+".png"));

			System.out.println("element exception Called " + e);
		}
	}
}
