package Selenium.AutomationChallenge;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class Testbase {
	
	protected static WebDriver driver;

	/**
	 * Variable holding the test number to the console and to be added to the
	 * screenshot when the test fails, for more descriptive name and to avoid file
	 * overwrite.
	 */
	int TestCounter = 1;

	String _chromeDriverPath = "WebDrivers\\\\chromedriver.exe";
	String _firefoxDriverPath = "WebDrivers\\\\geckodriver.exe";
	String _ieDriverPath = "WebDrivers\\\\IEDriverServer.exe";
	String _edgeDriverPath = "WebDrivers\\\\msedgedriver.exe";

	/**
	 * Takes the browser name from the User Input and set the WebDriver according to
	 * it.
	 */
	@BeforeSuite(groups = { "userPrompt" })
	public void openBrowserByUserInput() {

		System.out.println("Please enter one of the following (chrome, firefox, ie, edge)");
		Scanner userInput = new Scanner(System.in);
		String browserIn = userInput.nextLine();
		userInput.close();
		setWebDriver(browserIn);
	}

	/**
	 * Sets the WebDriver browser.
	 * 
	 * @param browserName String containing the browser name to initiate it's
	 *                    WebDriver.
	 */
	@BeforeSuite(groups = { "parameter" })
	@Parameters({ "browser" })
	public void setWebDriver(@Optional("chrome") String browserName) {
		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", _chromeDriverPath);
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", _firefoxDriverPath);
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", _ieDriverPath);
			driver = new InternetExplorerDriver();
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", _edgeDriverPath);
			driver = new EdgeDriver();
		} else {
			System.out.println(
					"Invalid browser name, so the test will be executed on the default browser which is Google Chrome");
			System.setProperty("webdriver.chrome.driver", _chromeDriverPath);
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
	}

	/**
	 * Closes the WebDriver after all Tests are done.
	 */
	@AfterSuite(groups = { "parameter", "userPrompt" })
	public void stopDriver() {
		driver.quit();
	}

	/**
	 * Used to Take Screenshots for the current running web driver before failing,
	 * to show more descriptive tests when fails and put it in the Screenshots
	 * folder.
	 * 
	 * @param result Test result object of the current executed test.
	 * 
	 * @throws IOException
	 */
	@AfterMethod(groups = { "parameter", "userPrompt" })
	public void takeScreenShot(ITestResult result) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			// Taking Screenshots for Failures
			TakesScreenshot takescreenshot = (TakesScreenshot) driver;
			File source = takescreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source,
					new File("./Screenshots/" + result.getName() + " " + (TestCounter - 1) + ".png"));
		}
	}
}
