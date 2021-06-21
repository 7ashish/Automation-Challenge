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

* Take care of the parameters order used plus my Excel Reader neglect the headers [Headers are required but neglected]
* The Parameters should be set as follows :
* @param URL              an absolute URL.
* @param searchText       Text that is used to search for results.
* @param searchBarLocator Locator for the search bar.
* @param page2Locator     Results Page 2 Locator
* @param page3Locator     Results Page 3 Locator
* @param scrollingMethod  Method used to identify how many pixels should be
*                         scrolled in which direction.
* @param resultsLocator   results Locator for counting purpose neglecting any
*                         maps, videos, or images.


### Executing program

* How to run the program 
* Run the TestNG.xml as a TestNG Suite after modifying it's parameters if required.
* You are able to either choose the browser to run in the run time by enter the name of the browser as a user input, or setting the browser parameter to the name of the browser.
* Don't Forget to decide which way you want to run that test either by browser user input or by browser parameter, you can do that by setting the group name into either ["userPrompt" or "parameter"] and it will work fine with you.
```
code blocks for commands
```
