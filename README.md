# Automation-Challenge
Automation Challenge using Selenium WebDriver.

## Description

This Automation Framework is a Maven Project, support 4 browsers [* Chrome, * FireFox, * Internet Explorer, * Edge], and currently containing 1 test used to test browser Results on different pages, in my case I am testing the second and third page results count if they are the same, ignoring any maps, videos, or images. 

It also containing 

## Getting Started

### Dependencies

* The Maven Project contains all the requird dependancies and it could be run on any device.
* You just need to check your 4 browsers version if it supports the web drivers used in this project.
* In order to get the different drivers version use this link for
* Chrome : 
*                 https://chromedriver.chromium.org/downloads
* FireFox : 
*                 https://github.com/mozilla/geckodriver/releases
* Edge : 
*                 https://developer.microsoft.com/en-us/microsoft-edge/tools/webdriver/
* Internet Explorer : 
*                 https://selenium-release.storage.googleapis.com/index.html

* After downloading your WebDriver don't forget to overwrite the old webdriver in a folder called WebDrivers.


* If you want to test your test data Excel File you just need to add the file location in the Configuration file called TestNG.xml in the parameter called : dataFile and set it's value to the file location you want to use for testing.
```
	<!-- dataFile parameter holds the file pass of the Excel sheet that contains the test data -->
	<parameter name="dataFile" value=".\\\\TestDataFiles\\\\testdata.xlsx" />
```

* Take care of the parameters order used plus my Excel Reader neglect the headers [Headers are required but neglected]
* The Parameters should be set as follows :
* @param URL              an absolute URL.
* @param searchText       Text that is used to search for results.
* @param searchBarLocator Locator for the search bar.
* @param page2Locator     Results Page 2 Locator
* @param page3Locator     Results Page 3 Locator
* @param scrollingMethod  Method used to identify how many pixels should be scrolled in which direction.
* @param resultsLocator   results Locator for counting purpose neglecting any maps, videos, or images.

![image](https://user-images.githubusercontent.com/33814335/122742129-f0f4f200-d285-11eb-82fa-33509ef603e1.png)



### Executing program

* Run the TestNG.xml as a TestNG Suite after modifying it's parameters if required.
* You are able to either choose the browser to run in the run time by enter the name of the browser as a user input, or setting the browser parameter to the name of the browser.
```
<!-- Parameter value can be ("chrome","firefox","ie" or "edge") to run on any browser in case of parameter group -->
	<parameter name="browser" value="chrome" />
```

* Don't Forget to decide which way you want to run that test either by browser user input or by browser parameter, you can do that by setting the group name into either ["userPrompt" or "parameter"] and it will work fine with you.
```
		<groups>
			<run>
				<!-- You can include (["userPrompt" for browser User Input] and ["parameter" for browser parameter in TestNG.xml]) -->
				<include name="userPrompt">
				</include>
			</run>
		</groups>
```
### Test Case

* After running the testdata in the Excel Sheet using Edge Browser
![image](https://user-images.githubusercontent.com/33814335/122742129-f0f4f200-d285-11eb-82fa-33509ef603e1.png)
![image](https://user-images.githubusercontent.com/33814335/122742042-dde22200-d285-11eb-9442-1864b8fe6468.png)
![image](https://user-images.githubusercontent.com/33814335/122742345-226dbd80-d286-11eb-8432-6c71a94a6625.png)
* Screenshot is taken on the Failed test using the Helper Class [TakeScreenShotOnTestFailure]
![image](https://user-images.githubusercontent.com/33814335/122742469-43361300-d286-11eb-9302-bb51b20e285b.png)
![image](https://user-images.githubusercontent.com/33814335/122742535-55b04c80-d286-11eb-8115-0f48270f33d7.png)

* The Generated TestNG report of this Test Case is in the test-output folder which is automatically generated when testing the TestNG.xml
![image](https://user-images.githubusercontent.com/33814335/122743200-15050300-d287-11eb-977a-899664d523ca.png)


