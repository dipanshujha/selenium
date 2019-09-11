package selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.formula.functions.Even;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.SendingContext.RunTime;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class final_p {
	static WebDriver driver;
	String adate = new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
	ExtentReports report = new ExtentReports("D://final//report_" + adate + "_.html");
	ExtentTest fin;

	@BeforeSuite
	public void startexec() {
		System.out.println("Execution of BeforeSuite started\n");
		String browser = "chrome";
		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\workspace\\Auto\\chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		} else if (browser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\admin\\workspace\\Auto\\geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		} else if (browser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\admin\\workspace\\Auto\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();
		} else {
			System.out.println("\nNo appropriate browser defined.");
		}
		System.out.println("Execution of BeforeSuite finished");
	}

	@BeforeTest
	public void geturl() throws IOException {
		String adate = new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
		report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		fin = report.startTest("BeforeTest");
		String expected_title = "Demo Form for practicing Selenium Automation";
		String url = "https://www.toolsqa.com/automation-practice-form/";
		driver.get(url);
		Assert.assertEquals(driver.getTitle(), expected_title);
		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss, new File("D://final//ss_" + adate + "_.png"));
		System.out.println("\nExpected title visited---- " + driver.getTitle() + " ----" + "\nURL visited was---- "
				+ driver.getCurrentUrl() + " ----");
		fin.log(LogStatus.PASS, "Passed for title" + fin.addScreenCapture("D://final//ss_" + adate + "_.png"));
		report.endTest(fin);

	}

	@Test
	public void a_form_submit() throws IOException, InterruptedException {
		String adate = new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
		report.loadConfig(new File(System.getProperty("user.dir") + "\\extent-config.xml"));
		fin = report.startTest("Form Test");
		String expected_title = "Demo Table for practicing Selenium Automation";
		Properties pro = new Properties();
		FileInputStream locators = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		pro.load(locators);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("cookie")))).click();
		driver.findElement(By.linkText("Link Test")).click();
		Assert.assertEquals(driver.getTitle(), expected_title);
		System.out.println("\nLink text clicked");
		Thread.sleep(2000);
		driver.navigate().back();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("fname"))))
				.sendKeys("Dipanshu");
		driver.findElement(By.xpath(pro.getProperty("submit"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pro.getProperty("lname")))).sendKeys("Jha");
		driver.findElement(By.xpath(pro.getProperty("male_radio"))).click();
		driver.findElement(By.xpath(pro.getProperty("check1"))).click();
		driver.findElement(By.xpath(pro.getProperty("photo"))).click();
		Runtime.getRuntime().exec("D:\\final\\final.exe");
		Select drp = new Select(driver.findElement(By.xpath(pro.getProperty("dropdown1"))));
		drp.selectByVisibleText("Europe");
		File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(ss, new File("D://final" + adate + "_.png"));
		fin.log(LogStatus.ERROR, driver.getTitle() + fin.addScreenCapture("D://final" + adate + "_.png"));
		report.endTest(fin);
	}
	
	@Test
	public void b_alert_handle() throws IOException, InterruptedException{
		fin = report.startTest("Alerts");
		Properties pro = new Properties();
		FileInputStream locators = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		pro.load(locators);
		String expected_title = "Selenium Easy Demo - Automate All Scenarios";
		driver.get("https://www.seleniumeasy.com/test/javascript-alert-box-demo.html");
		Assert.assertEquals(driver.getTitle(), expected_title);
		driver.findElement(By.xpath(pro.getProperty("simple_alert"))).click();
		Alert al = driver.switchTo().alert();
		System.out.println("Text for alert was "+al.getText());
		al.accept();
		Thread.sleep(1000);
		driver.findElement(By.xpath(pro.getProperty("an_alert"))).click();
		Alert an = driver.switchTo().alert();
		System.out.println("Alert text "+an.getText());
		an.dismiss();
		fin.log(LogStatus.INFO, "alert done");
		report.endTest(fin);
	}
	
	@Test
	public void c_events() throws IOException{
		Properties pro = new Properties();
		fin = report.startTest("Events");
		FileInputStream locators = new FileInputStream(System.getProperty("user.dir") + "\\app.properties");
		pro.load(locators);
		String exp = "Simple Context Menu";
		String adate = new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
		driver.get("http://demo.guru99.com/test/simple_context_menu.html");
		Actions act = new Actions(driver);
		WebElement dc = driver.findElement(By.xpath(pro.getProperty("double_cl")));
		try{
		Assert.assertEquals(driver.getTitle(), exp);
		act.doubleClick(dc).perform();
		driver.switchTo().alert().accept();
		}
		catch (Error e) {
			// TODO: handle exception
			System.out.println("Assert failed ");
			File ss = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(ss, new File("D://final" + adate + "_.png"));
			fin.log(LogStatus.FAIL, "failed for title "+fin.addScreenCapture("D://final" + adate + "_.png"));
		}
		report.endTest(fin);
	}

	@Test
	public void d_excel_read() throws IOException {
		File exfile = new File("D:\\excelr.xlsx");
		FileInputStream fis = new FileInputStream(exfile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheetAt(0);
		int rowcount = ws.getLastRowNum() - ws.getFirstRowNum();
		int colcount = ws.getRow(0).getLastCellNum() - 1;

		for (int i = 0; i < rowcount + 1; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				String celldt = row.getCell(j).getStringCellValue();
				if (celldt.equals("Y")) {
					String data1 = row.getCell(colcount - 2).getStringCellValue();
					String data2 = row.getCell(colcount - 1).getStringCellValue();
					System.out.println(data1);
					System.out.println(data2);
					// String dt = (data1+" "+data2);
					// System.out.println(dt);
					/*
					 * String[] parts = dt.split(" "); String user = parts[0];
					 * // 004 String pass = parts[1]; // 034556
					 * System.out.println(user);
					 */
					// System.out.println(data2);

				}
			}
			System.out.println();
		}

	}

	@Test
	public void e_excel_write() throws IOException {

		String adate = new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(new Date());
		File exc = new File("D://excelr.xlsx");
		FileInputStream fis = new FileInputStream(exc);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet ws = wb.getSheetAt(0);
		int rowcount = ws.getLastRowNum() - ws.getFirstRowNum();
		int colcount = ws.getRow(0).getLastCellNum() - 1;
		for (int i = 0; i < rowcount + 1; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				String cell_data = row.getCell(j).getStringCellValue();

				if (cell_data.equals("Y")) {
					// ws.createRow(2).createCell(colcount).setCellValue("");
					row.createCell(colcount + 1).setCellValue("PASSED");
					/*
					 * String dt1 =
					 * row.getCell(colcount-1).getStringCellValue(); String dt2
					 * = row.getCell(colcount-2).getStringCellValue();
					 * System.out.println(dt1+" "+dt2);
					 * 
					 * System.out.println("Changing passowrds");
					 */
					String data = ws.getRow(rowcount - 2).getCell(colcount - 1).getStringCellValue();
					System.out.println(data);
					if (data.equals("a1234")) {
						XSSFCell cell = ws.getRow(rowcount - 2).getCell(colcount - 1);
						cell.setCellValue("Changed_pass");
					} else {
						System.out.println("not printed " + data);
					}

					FileOutputStream outputStream = new FileOutputStream("D://JavaBooks" + adate + "_.xlsx");
					wb.write(outputStream);
					outputStream.close();
				}
			}
			System.out.println();
		}

	}

	@Test
	public void zz() {
		String adate = new SimpleDateFormat("dd_MM_YY_hh_mm_ss").format(new Date());
		System.out.println(adate);
	}

	@AfterTest
	public void aft() {
		
		report.flush();
		System.out.println("TEst executed successfully");
	}
}
