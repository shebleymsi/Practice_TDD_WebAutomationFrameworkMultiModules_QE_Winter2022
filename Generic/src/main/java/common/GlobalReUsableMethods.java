package common;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;


public class GlobalReUsableMethods extends WebTestBase {


    public static void clickOnElementByXpath(String locator) {
        driver.findElement(By.xpath(locator)).click();
    }

    public void clickOnElementCssSelector(String locator) {
        driver.findElement(By.cssSelector(locator)).click();
    }

    public static void enterValueOnElement(String locator, String value) {
        driver.findElement(By.xpath(locator)).sendKeys(value);
    }


    public static void clickOnElement(WebElement element) {
        element.click();
    }

    public static void enterValueOnElement(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

    public static void scrollDownToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }


    public static String convertToString(String st) {
        String splitString = "";
        splitString = StringUtils.join(StringUtils.splitByCharacterTypeCamelCase(st), ' ');
        return splitString;
    }


    // Wait: https://www.guru99.com/implicit-explicit-waits-selenium.html
    // ImplicitWait: Indirect wait
    // ExplicitWait: Direct wait
    // FluentWait: conditional wait till the time-out


    public static WebElement explicitWaitForElementUsingVisibilityOf(WebElement ele) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOf(ele));
        return element;
    }

    public static WebElement explicitWaitForElementToBeClickable(WebElement ele) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(ele));
        return element;
    }

    public static boolean explicitWaitForElementToBeSelected(WebElement ele) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        boolean element = webDriverWait.until(ExpectedConditions.elementToBeSelected(ele));
        return element;
    }

    public static void waitUntilClickable(WebElement ele) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement element = webDriverWait.until(ExpectedConditions.visibilityOf(ele));
        element.click();
    }

    // https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver

    public static WebElement fluentWaitUntilConditionMeet(WebElement ele) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3)).withMessage("Time out")
                .ignoring(NoSuchElementException.class);

        WebElement element = fluentWait.until(new Function<>() {
            public WebElement apply(WebDriver driver) {
                return ele;
            }
        });
        return element;
    }

    public static WebElement fluentWaitUntilConditionMeetUsingLambda(WebElement ele) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3)).withMessage("Time out")
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(driver1 -> ele);
    }

    public static WebElement fluentWaitUntilConditionMeetUsingBeforeLambda(WebElement ele) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(60))
                .pollingEvery(Duration.ofSeconds(3)).withMessage("Time out")
                .ignoring(NoSuchElementException.class);
        return fluentWait.until(new Function<>() {
            public WebElement apply(WebDriver driver1) {
                return ele;
            }
        });
    }


    public static void selectByVisibleTextFromSelect(WebElement element, String visibleText) {
        Select select = new Select(element);
        select.selectByVisibleText(visibleText);
    }

    public static void selectByIndexFromSelect(WebElement element, int index) {
        Select select = new Select(element);
        select.selectByIndex(index);
    }

    public static void selectByValueFromSelect(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);
    }


    public void clickOnElement(String locator) {
        try {
            driver.findElement(By.xpath(locator)).click();
        } catch (Exception e1) {
            try {
                driver.findElement(By.cssSelector(locator)).click();
            } catch (Exception e2) {
                try {
                    driver.findElement(By.id(locator)).click();
                } catch (Exception e3) {
                    driver.findElement(By.name(locator)).click();
                }
            }
        }
    }


    public static void clearField(WebElement element) {
        element.click();
    }

    public static void navigateBack() {
        driver.navigate().back();
    }

    public static void navigateForward() {
        driver.navigate().forward();
    }


    public static List<WebElement> getListOfElementsByXpath(String locator) {
        List<WebElement> elementList = new ArrayList<>();
        elementList = driver.findElements(By.xpath(locator));
        return elementList;
    }

    public static List<WebElement> getListOfElementsFromWebElement(List<WebElement> elements) {
        List<WebElement> elementList = new ArrayList<>();
        elementList = elements;
        return elementList;
    }

    public static List<String> getListOfString(List<WebElement> elements) {
        List<String> elementList = new ArrayList<>();
        for (WebElement element : elements) {
            elementList.add(element.getText());
        }
        return elementList;
    }

    public static String getCurrentUrl() {
        return driver.getCurrentUrl();
    }


    public static void mouseHoverByXpath(String locator) {
        WebElement element = driver.findElement(By.xpath(locator));
        Actions action = new Actions(driver);
        try {
            action.moveToElement(element);
        } catch (Exception e) {
            getLog("First Attempt has been done, This is second try");
            action.moveToElement(element).perform();
        }
    }


    public static void mouseHoverByWebElement(WebElement element) {
        Actions action = new Actions(driver);
        try {
            action.moveToElement(element);
        } catch (Exception e) {
            getLog("First Attempt has been done, This is second try");
            action.moveToElement(element).perform();
        }
    }


    public static void okAlert() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public static void cancelAlert() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }

    public static void dynamicAlertHandle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
            System.out.println("alert was not present");
        } else {
            System.out.println("alert was present");
            okAlert();
        }

    }

    // ****************************************************************
//iFrame Handle
    public void iframeHandle(WebElement element) {
        driver.switchTo().frame(element);
    }

    public void goBackToHomeWindow() {
        driver.switchTo().defaultContent();
    }

    //get Links
    public void getLinks(String locator) {
        driver.findElement(By.linkText(locator)).findElement(By.tagName("a")).getText();
    }

    //Taking Screen shots
    public void takeScreenShot() throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("screenShots.png"));
    }


    public void upLoadFile(String locator, String path) {
        driver.findElement(By.cssSelector(locator)).sendKeys(path);
        /* path example to upload a file/image
           path= "C:\\Users\\rrt\\Pictures\\ds1.png";
         */
    }


    //Handling New Tabs
    public static WebDriver handleNewTab(WebDriver driver1, int windowIndex) {
        String oldTab = driver1.getWindowHandle();
        List<String> newTabs = new ArrayList<String>(driver1.getWindowHandles());
        newTabs.remove(oldTab);
        driver1.switchTo().window(newTabs.get(windowIndex));
        return driver1;
    }

    public static boolean isPopUpWindowDisplayed(WebDriver driver1, String locator) {
        boolean value = driver1.findElement(By.cssSelector(locator)).isDisplayed();
        return value;
    }

    public void typeOnInputBox(String locator, String value) {
        try {
            driver.findElement(By.id(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex1) {
            System.out.println("ID locator didn't work");
        }
        try {
            driver.findElement(By.name(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex2) {
            System.out.println("Name locator didn't work");
        }
        try {
            driver.findElement(By.cssSelector(locator)).sendKeys(value, Keys.ENTER);
        } catch (Exception ex3) {
            System.out.println("CSS locator didn't work");
        }
    }


    // Customer Made Helper Methods for Amex.com
    public void brokenLink() throws IOException {
        //Step:1-->Get the list of all the links and images
        List<WebElement> linksList = driver.findElements(By.tagName("a"));
        linksList.addAll(driver.findElements(By.tagName("img")));

        System.out.println("Total number of links and images--------->>> " + linksList.size());

        //Step:2-->Iterate linksList: exclude all links/images which does not have any href attribute
        List<WebElement> activeLinks = new ArrayList<WebElement>();
        for (int i = 0; i < linksList.size(); i++) {
            System.out.println(linksList.get(i).getAttribute("href"));
            if (linksList.get(i).getAttribute("href") != null && (!linksList.get(i).getAttribute("href").contains("javascript") && (!linksList.get(i).getAttribute("href").contains("mailto")))) {
                activeLinks.add(linksList.get(i));
            }
        }
        System.out.println("Total number of active links and images-------->>> " + activeLinks.size());

        //Step:3--> Check the href url, with http connection api
        for (int j = 0; j < activeLinks.size(); j++) {
            HttpURLConnection connection = (HttpURLConnection) new URL(activeLinks.get(j).getAttribute("href")).openConnection();
            connection.connect();
            String response = connection.getResponseMessage();
            connection.disconnect();
            System.out.println(activeLinks.get(j).getAttribute("href") + "--------->>> " + response);
        }
    }

    public void inputValueInTextBoxByWebElement(WebElement webElement, String value) {
        webElement.sendKeys(value + Keys.ENTER);
    }



    public String getTextByWebElement(WebElement webElement) {
        String text = webElement.getText();
        return text;
    }

    public void getCurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        //String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        Date date = new Date();
        System.out.println(formatter.format(date));
        System.out.println(" my test suite started at this time --> " + formatter.format(date));
    }

    // --------------------- EDIT BOX / TYPE -----------------------
    public void typeByXpath1(String loc, String val) {
        driver.findElement(By.xpath(loc)).clear();
        driver.findElement(By.xpath(loc)).sendKeys(val);
    }

    public void typeByCss1(String loc, String val) {
        driver.findElement(By.cssSelector(loc)).clear();
        driver.findElement(By.cssSelector(loc)).sendKeys(val);
    }


    // ------------------- CLICK -----------------
    public void clickByXpath1(String loc) {
        driver.findElement(By.xpath(loc)).click();
    }

    public void clickByCss(String loc) {
        driver.findElement(By.cssSelector(loc)).click();
    }

    public void clickById(String loc) {
        driver.findElement(By.id(loc)).click();
    }

    public void clickByName(String loc) {
        driver.findElement(By.name(loc)).click();
    }

    public void clickByLinkText(String loc) {
        driver.findElement(By.linkText(loc)).click();
    }

    // ---------------- RADIO BUTTON
    public void assertEqualByXpath(String loc, String expValue) {
        String act = driver.findElement(By.xpath(loc)).getText();
        // act is coming from Domain -- the one developer build and release
        String exp = expValue; // exp is coming from Requirement or Mock-up
        Assert.assertEquals(act, exp);
    }

    public static void verifyText(WebElement element, String expValue, String message) {
        String actual = element.getText();
        Assert.assertEquals(actual, expValue,message);
    }


    // Slider Handlaing
    // https://stackoverflow.com/questions/15171745/how-to-slidemove-slider-in-selenium-webdriver




    public void clickOnLink(String locator) {
        try {
            driver.findElement(By.linkText(locator)).click();
        } catch (Exception ex) {
            driver.findElement(By.partialLinkText(locator)).click();
        }
    }


    public void clearField1(String locator) {
        try {
            driver.findElement(By.cssSelector(locator)).clear();
        } catch (Exception ex) {
            try {
                driver.findElement(By.xpath(locator)).clear();
            } catch (Exception ex1) {
                try {
                    driver.findElement(By.id(locator)).clear();
                } catch (Exception ex2) {
                    try {
                        driver.findElement(By.name(locator)).clear();
                    } catch (Exception ex3) {
                        try {
                            driver.findElement(By.className(locator)).clear();
                        } catch (Exception ex4) {
                            driver.findElement(By.tagName(locator)).clear();
                        }
                    }
                }
            }
        }
    }


    public void getTitle() {
        getLog("Get Title ");
        driver.getTitle();
    }


    public static String getText(WebElement element, String webElementName) {
        getLog("Getting Text from " + webElementName);
        String actualText = element.getText();
        getLog("Actual text: " + actualText);
        return actualText;
    }




}
