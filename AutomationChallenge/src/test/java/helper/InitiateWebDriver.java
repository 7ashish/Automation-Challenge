package helper;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class InitiateWebDriver {

	String _chromeDriverPath = "WebDrivers\\\\chromedriver.exe";
	String _firefoxDriverPath = "WebDrivers\\\\geckodriver.exe";
	String _ieDriverPath = "WebDrivers\\\\IEDriverServer.exe";
	String _edgeDriverPath = "WebDrivers\\\\msedgedriver.exe";

	/**
	 * Takes the browser name from the User Input and set the WebDriver according to it.
	 */
	public WebDriver openBrowserByUserInput() {

		System.out.println("Please enter one of the following (chrome, firefox, ie, edge)");
		Scanner userInput = new Scanner(System.in);
		String browserIn = userInput.nextLine();
		userInput.close();
		/**
		 * calling a defined helper method to set the browser from User Input.
		 */
		return setWebDriver(browserIn);
	}

	/**
	 * Sets the WebDriver browser.
	 * 
	 * @param driverBrowser String containing the browser name to initiate it's
	 *                      WebDriver.
	 */
	public WebDriver setWebDriver(String driverBrowser) {
		if (driverBrowser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", _chromeDriverPath);
			return new ChromeDriver();
		} else if (driverBrowser.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", _firefoxDriverPath);
			return new FirefoxDriver();
		} else if (driverBrowser.equals("ie")) {
			System.setProperty("webdriver.ie.driver", _ieDriverPath);
			return new InternetExplorerDriver();
		} else if (driverBrowser.equals("edge")) {
			System.setProperty("webdriver.edge.driver", _edgeDriverPath);
			return new EdgeDriver();
		} else {
			System.out.println(
					"Invalid browser name, so the test will be executed on the default browser which is Google Chrome");
			System.setProperty("webdriver.chrome.driver", _chromeDriverPath);
			return new ChromeDriver();
		}
	}
}
