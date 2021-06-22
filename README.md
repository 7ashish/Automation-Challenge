# Automation-Challenge
Automation Challenge using Selenium WebDriver.

## Description

This Automation Framework is a Maven Project, Supporting 4 browsers [###Chrome, ###FireFox, ###Internet Explorer, ###Edge], and currently containing 1 test used to test browser Results on different pages, in my case I am testing the second and third page results count if they are the same, ignoring any maps, videos, or images. 

## Getting Started

---

### Dependencies

* The Maven Project contains all the requird dependancies and it could be run on any device with an IDE (preferably Eclipse or IntelliJ).
* You might need to check your 4 browsers version if it supports the web drivers used in this project.
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

---

### Executing program

* Import the project and go to TestNG.xml file in src/test/java folder, right click inside the file, choose [Run As ---> TestNG Suite].
* You have 2 options for running this Test eiher by User Input [Run Time] or by parameter.
* So Don't Forget to decide which way you want to run that test, you can do that by setting the group name into either ["userPrompt" or "parameter"] in the TestNG.xml file.
```
		<groups>
			<run>
				<!-- You can include (["userPrompt" for browser User Input] and ["parameter" for browser parameter in TestNG.xml]) -->
				<include name="userPrompt">
				</include>
			</run>
		</groups>
```

* [1st Option] You are able to either choose the browser in the run time by entering the name of the browser as a user input in the console if the group name is set to userPrompt.
* [2nd Option] Setting the browser parameter name to your favourite browser [chrome, firefox, ie or edge]

### This is for the 2nd Option: 
* You can find this code inside the TestNG.xml
```diff
- <!-- Parameter value can be ("chrome","firefox","ie" or "edge") to run on any browser in case of parameter group -->
+	<parameter name="browser" value="chrome" />
```
---

## Test Cases
### [Test Case 1]

### Excel test data file:

![image](https://user-images.githubusercontent.com/33814335/122881791-d54c2300-d33b-11eb-98a7-37b1931d4315.png)

### Console After running the test:
* Notice here:  I misstyped the browser name so it had a Failure Recovery which automatically runs the test on the default browser [Google Chrome]. 

![image](https://user-images.githubusercontent.com/33814335/122885674-94560d80-d33f-11eb-9092-3e34083f5bc2.png)

### TestNG Results of Running Suite

![image](https://user-images.githubusercontent.com/33814335/122885740-a46ded00-d33f-11eb-9866-467a3ac2a642.png)

### You can check the Auto generated report:
* Go to test-output folder [Automatically generated folder after running the test]
* Right click on the index.html then [OpenWith ---> WebBrowser]

![image](https://user-images.githubusercontent.com/33814335/122882250-48ee3000-d33c-11eb-8042-ad474023c909.png)

---

### [Test Case 2]

#### Excel test data file 2:

![image](https://user-images.githubusercontent.com/33814335/122884484-805ddc00-d33e-11eb-8f6c-fffe2786de26.png)

### Console After Running test 2:

![image](https://user-images.githubusercontent.com/33814335/122886215-0dedfb80-d340-11eb-9bdf-d7123f1da57e.png)

### TestNG Results of Running Suite

![image](https://user-images.githubusercontent.com/33814335/122886329-2827d980-d340-11eb-8c17-5baccd8ee36a.png)

### You can check the Auto generated report:
* Go to test-output folder [Automatically generated folder after running the test]
* Right click on the index.html then [OpenWith ---> WebBrowser]

![image](https://user-images.githubusercontent.com/33814335/122886621-6a511b00-d340-11eb-93da-5e636f08b4af.png)

#### Screenshot is taken on the Failed test using the Helper Class [TakeScreenShotOnTestFailure]
* it specifies the Name of the Test and the Test counter.

![image](https://user-images.githubusercontent.com/33814335/122887024-c3b94a00-d340-11eb-8bc0-19fa04b47f57.png)


