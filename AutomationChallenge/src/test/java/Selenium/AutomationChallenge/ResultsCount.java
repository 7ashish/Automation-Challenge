package Selenium.AutomationChallenge;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import data.ExcelReader;

public class ResultsCount {
	
	WebDriver driver;
	
	static String browserIn;
	static String chromeDriverPath = "WebDrivers\\\\chromedriver.exe";
	static String firefoxDriverPath = "WebDrivers\\\\geckodriver.exe";
	static String ieDriverPath = "WebDrivers\\\\IEDriverServer.exe";
	static String edgeDriverPath = "WebDrivers\\\\msedgedriver.exe";
	static String testDataPath;
	
	
	/**
	* Set the test data Excel file path to its variable.
	* @param  dataFile  contains the test data Excel file path.
	*/
	@BeforeTest (groups = {"parameter", "userPrompt"})
	@Parameters({"dataFile"})
	public void assignDataPath(String dataFile)
	{
		testDataPath = dataFile;
	}
	
	/**
	* Set the WebDriver from the browser set from User Input.
	*/
	@BeforeTest (groups = "userPrompt")
	public void openBrowserUser() {
		
		System.out.println("Please enter one of the following (chrome, firefox, ie, edge)");
		Scanner userInput = new Scanner(System.in);
		browserIn = userInput.nextLine();
		
		/**
		* calling a defined helper method to set the browser from User Input.
		*/
		setDriver(browserIn);
		
	}
	
	/**
	* Set the WebDriver from the browser set from parameter set in TestNG.xml
	* @param  browser  contains the browser obtained from the parameter set in TestNG.xml
	*/
	@BeforeTest (groups = "parameter")
	@Parameters({"browser"})
	public void openBrowser(String browser) {
		setDriver(browser);
	}
	
		
	/**
	* Asserts That page 2 and page 3 have equal results count, in any URL. 
	* @param  URL  an absolute URL.
	* @param  searchText Text that is used to search for results.
	* @param  searchBarLocator  Locator for the search bar.
	* @param  page2Locator Results Page 2 Locator
	* @param  page3Locator Results Page 3 Locator
	* @param  scrollingMethod  Method used to identify how many pixels should be scrolled in which direction.
	* @param  resultsLocator results Locator for counting purpose neglecting any maps, videos, or images.
	*/
	//Parameters ordering is important according to the excel sheet
	@Test(groups = {"parameter", "userPrompt"}, dataProvider = "test_Data")
	public void resultsCount(String URL, String searchText, String searchBarLocator, 
			String page2Locator, String page3Locator, String scrollingMethod,String resultsLocator) {
	    
		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
				
		WebElement searcHBar = driver.findElement(By.name(searchBarLocator));
		searcHBar.sendKeys(searchText);
		searcHBar.submit();
   
		
        js.executeScript(scrollingMethod);

        driver.findElement(By.cssSelector(page2Locator)).click();
		List<WebElement> page2Results = driver.findElements(By.xpath(resultsLocator));
		System.out.println(page2Results.size());

		js.executeScript(scrollingMethod);
        
		driver.findElement(By.cssSelector(page3Locator)).click();
		List<WebElement> page3Results = driver.findElements(By.xpath(resultsLocator));
		System.out.println(page3Results.size());
	    
		
		
		int pageTwo = page2Results.size();
		int pageThree = page3Results.size();
		Assert.assertEquals(pageTwo, pageThree);
	}

		
	
	
	/**
	* Closes the WebDriver browser.
	*/
	@AfterTest(groups = {"parameter", "userPrompt"})
	public void closeBrowser() {
		driver.close();
	}

	/**
	* Sets the WebDriver browser according to the User Input or Parameter obtained from TestNG.xml. 
	* @param  driverBrowser  an absolute URL.
	*/
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
	
	/**
	* Reading the Data Driven from the Excel Sheet using extractExcelData from the Excel Reader class,
	* to use it in the  resultsCount test function.
	*/
	@DataProvider
	public Object[][] test_Data() throws InvalidFormatException, IOException
	{
		ExcelReader Excel = new ExcelReader();
		return Excel.extractExcelData(testDataPath);
	}
}
