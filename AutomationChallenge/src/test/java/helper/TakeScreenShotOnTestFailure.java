package helper;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class TakeScreenShotOnTestFailure {

	/**
	 * Used to Take Screenshots for the current running web driver before failing,
	 * to show more descriptive tests when fails and put it in the Screenshots folder.
	 * 
	 * @param result       Test result object of the current executed test.
	 * @param driver       The Web Driver currently running.
	 * @param TestsCounter Parameter holding the test number to be added to the
	 *                     screenshot when the test fails, for more descriptive name
	 *                     and to avoid file overwrite.
	 */
	public void takeScreenShot(ITestResult result, WebDriver driver, int TestsCounter) throws IOException {
		if (ITestResult.FAILURE == result.getStatus()) {
			// Taking Screenshots for Failures
			TakesScreenshot takescreenshot = (TakesScreenshot) driver;
			File source = takescreenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + result.getName() + " " + TestsCounter + ".png"));
		}
	}
}
