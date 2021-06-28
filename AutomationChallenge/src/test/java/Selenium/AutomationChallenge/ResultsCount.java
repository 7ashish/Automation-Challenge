package Selenium.AutomationChallenge;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import data.ExcelReader;

public class ResultsCount extends Testbase {

	/**
	 * Variable holding the test data Excel file path.
	 */
	String testDataPath;

	/**
	 * Set the test data Excel file path to its variable.
	 * 
	 * @param dataFile contains the test data Excel file path.
	 */
	@BeforeTest(groups = { "parameter", "userPrompt" })
	@Parameters({ "dataFile" })
	public void assignDataPath(String dataFile) {
		testDataPath = dataFile;
	}

	/**
	 * Asserts That page 2 and page 3 have equal results count, in any URL.
	 * 
	 * @param URL              an absolute URL.
	 * @param searchText       Text that is used to search for results.
	 * @param searchBarLocator Locator for the search bar.
	 * @param page2Locator     Results Page 2 Locator
	 * @param page3Locator     Results Page 3 Locator
	 * @param scrollingMethod  Method used to identify how many pixels should be
	 *                         scrolled in which direction.
	 * @param resultsLocator   results Locator for counting purpose neglecting any
	 *                         maps, videos, or images.
	 */
	// Parameters ordering is important according to the excel sheet
	@Test(groups = { "parameter", "userPrompt" }, dataProvider = "test_Data")
	public void resultsCount(String URL, String searchText, String searchBarLocator, String page2Locator,
			String page3Locator, String scrollingMethod, String resultsLocator) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		driver.get(URL);

		WebElement searcHBar = driver.findElement(By.name(searchBarLocator));
		searcHBar.sendKeys(searchText);
		searcHBar.submit();

		js.executeScript(scrollingMethod);

		driver.findElement(By.cssSelector(page2Locator)).click();
		List<WebElement> page2Results = driver.findElements(By.xpath(resultsLocator));
		System.out.println("Test " + TestCounter + " [" + searchText + "]" + " Page 2 Results: " + page2Results.size());

		js.executeScript(scrollingMethod);

		driver.findElement(By.cssSelector(page3Locator)).click();
		List<WebElement> page3Results = driver.findElements(By.xpath(resultsLocator));
		System.out
				.println("Test " + TestCounter++ + " [" + searchText + "]" + " Page 3 Results: " + page3Results.size());

		int pageTwo = page2Results.size();
		int pageThree = page3Results.size();
		Assert.assertEquals(pageTwo, pageThree);
	}

	/**
	 * Reading the Data Driven from the Excel Sheet using extractExcelData from the
	 * Excel Reader class, to use it in the resultsCount test function.
	 */
	@DataProvider
	public Object[][] test_Data() throws InvalidFormatException, IOException {
		ExcelReader Excel = new ExcelReader();
		return Excel.extractExcelData(testDataPath);
	}
}
