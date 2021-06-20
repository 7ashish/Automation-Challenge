package Selenium.AutomationChallenge;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import data.ExcelReader;

public class ResultsCount {
	
	WebDriver driver;
	
	String browserIn;
	String chromeDriverPath = "WebDrivers\\\\chromedriver.exe";
	String firefoxDriverPath = "WebDrivers\\\\geckodriver.exe";
	String ieDriverPath = "WebDrivers\\\\IEDriverServer.exe";
	String edgeDriverPath = "WebDrivers\\\\msedgedriver.exe";
	String testDataPath;
	
	
	
	@BeforeTest (groups = {"parameter", "userPrompt"})
	@Parameters({"dataFile"})
	public void assignDataPath(String dataFile)
	{
		testDataPath = dataFile;
	}
	
		
	@BeforeTest (groups = "userPrompt")
	public void openBrowserUser() {
		
		System.out.println("Please enter one of the following (chrome, firefox, ie, edge)");
		Scanner myObj = new Scanner(System.in);
		browserIn = myObj.nextLine();
		
		setDriver(browserIn);
		
	}
	
		
	@BeforeTest (groups = "parameter")
	@Parameters({"browser"})
	
	public void openBrowser(String browser) {
		setDriver(browser);
	}
	
		
	
	//Parameters ordering is important according to the excel sheet
	@Test(groups = {"parameter", "userPrompt"}, dataProvider = "test_Data")
	public void searchGoogle(String URL, String searchText, String searchBarLocator, 
			String page2Locator, String page3Locator, String scrollingLocator,String resultsLocator) {
	    
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
		WebElement searcHBar = driver.findElement(By.name(searchBarLocator));
		searcHBar.sendKeys(searchText);
		searcHBar.submit();
   
		
        js.executeScript(scrollingLocator);

        driver.findElement(By.cssSelector(page2Locator)).click();
		List<WebElement> page2Results = driver.findElements(By.xpath(resultsLocator));
		System.out.println(page2Results.size());

		js.executeScript(scrollingLocator);
        
		driver.findElement(By.cssSelector(page3Locator)).click();
		List<WebElement> page3Results = driver.findElements(By.xpath(resultsLocator));
		System.out.println(page3Results.size());
	    
		
		
		int pageTwo = page2Results.size();
		int pageThree = page3Results.size();
		Assert.assertEquals(pageTwo, pageThree);
	}

		
	
	
	
	
	@AfterTest(groups = {"parameter", "userPrompt"})
	public void closeBrowser() {
		driver.close();
	}

	
	public void setDriver(String driverBrowser)
	{
		if (driverBrowser.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}
		else if (driverBrowser.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
			driver = new FirefoxDriver();
		}
		else if (driverBrowser.equals("ie"))
		{
			System.setProperty("webdriver.ie.driver", ieDriverPath);
			driver = new InternetExplorerDriver();
		}
		else if (driverBrowser.equals("edge"))
		{
			System.setProperty("webdriver.edge.driver", edgeDriverPath);
			driver = new EdgeDriver();
		}
		else
		{
			System.out.println("Invalid browser name, so the test will be executed on the default browser which is Google Chrome");
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			driver = new ChromeDriver();
		}
	}
	
	
	@DataProvider
	public Object[][] test_Data() throws InvalidFormatException, IOException
	{
		ExcelReader Excel = new ExcelReader();
		return Excel.extractExcelData(testDataPath);
	}
}
