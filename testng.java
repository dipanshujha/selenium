package selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testng {
	
	static WebDriver driver;
	
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yy_hh_mm_ss");
	LocalDateTime now = LocalDateTime.now();
	String curr_date = dtf.format(now);
	
	/*public static WebDriver OpenApp(String BrowserName, String url){
		fn_LaunchBrowser(BrowserName);
		fn_OpenURL(url);
		return driver;
		}
	
		public static void fn_OpenURL(String url){
		driver.get(url);
		driver.manage().window().maximize();
		}
		 
		public static WebDriver fn_LaunchBrowser(String BrowserName){
		if(BrowserName=="CH"){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\testng\\src\\main\\java\\testng\\chromedriver.exe");
		driver = new ChromeDriver();
		}*/
		
	public static void open(String browser, String url) throws InterruptedException{
			
			try{
			if(browser.equals("CH")){
				System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\testng\\src\\main\\java\\testng\\chromedriver.exe");
				driver = new ChromeDriver();
				
				driver.manage().window().maximize();
				driver.get(url);
				
				}
			}
			catch(Exception e){
				System.out.println(e);
			}
		}
		
	static public void print(String name){
		System.out.println("name is "+name);
	}
	
	@BeforeSuite
	public void launch(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\testng\\src\\main\\java\\testng\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Before suite called\n");
		}
	
	@BeforeTest
	public void b4test(){
		System.out.println("Execution started\n ");
	}
	
	@BeforeMethod
	public void b4method(){
		System.out.println("Before Method called\n");
	}
	
	@Test
	public void a_geturl(){
		driver.get("https://www.google.com");
		System.out.println(driver.getCurrentUrl()+"URL visited\n");
	}
	
	@Test
	public void b_gmail() throws InterruptedException{
		Thread.sleep(1000);
		String exp_title = "Sign in – Google accounts";
		String url = "https://accounts.google.com/signin/v2/identifier?flowName=GlifWebSignIn&flowEntry=ServiceLogin";
		driver.get(url);
		Assert.assertEquals(driver.getTitle(), exp_title);
		System.out.println("Gmail login page invoked\n");
	}
	@Test
	public void c_login() throws IOException, InterruptedException {
		Properties pro = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\app.properties");
		pro.load(file);
		driver.findElement(By.xpath(pro.getProperty("email_box_locator"))).sendKeys("email@gmail.com");
		driver.findElement(By.xpath(pro.getProperty("next1"))).click();
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(pro.getProperty("pass_locator"))));
		WebElement pass = driver.findElement(By.xpath(pro.getProperty("pass_locator")));
		pass.sendKeys("password");
		WebElement clk2 = driver.findElement(By.xpath(pro.getProperty("next2")));
		clk2.click();
		Thread.sleep(5000);
	}

	@Test
	public void d_as(){
		String exp_title = "Google Account"; 
		String error_msg = "Can't access Google account";
		Assert.assertEquals(driver.getTitle(), exp_title, error_msg);
		System.out.println("Appropriate site visited");
	}
	
	@AfterTest
	public void e_ss() throws IOException{
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshotFile, new File("D:\\Selenium_ss\\Success\\Gmail_"+curr_date+"_.png"));
	}
	
	
	@AfterSuite
	public void f_ter(){
		driver.close();
		System.out.println("Driver terminated");
	}
}
