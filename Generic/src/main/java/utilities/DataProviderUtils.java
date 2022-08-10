package utilities;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

import static configuration.common.WebTestBase.driver;

public class DataProviderUtils {
    // Similar to TestNG Parameters, DataProviders are a means to pass data to test scripts in TestNG. Using DataProvider in TestNG, we can easily inject multiple values into the same test case. It comes inbuilt in TestNG and is popularly used in data-driven frameworks.
    //The syntax for a DataProvider in TestNG is as follows:
    @DataProvider(name = "name of the data provider")
    public Object[][] dataProviderFunction() {
        return new Object[][]{};
    }

    // Now, let’s try to understand the different components of this syntax.
    //The DataProvider annotation has a single attribute called name, which you can select as per your convenience.
    //DataProviders are separate methods used in test functions, which means that this annotation is not used on test functions like the testNG parameters.
    //The DataProvider method returns a 2D list of objects.
    //In case you do not define a name for the DataProvider, the DataProvider method name is considered its default name. So, the name of the DataProvider calls the DataProvider method.


    // Using DataProvider in TestNG : Inheriting DataProvider in TestNG

    @DataProvider(name = "test-data-new")
    public static Object[][] dataProviderFunc() {
        return new Object[][]{
                {"Lambda Test"},
                {"Automation"}
        };
    }

    @Test(dataProvider = "test-data-new", dataProviderClass = DataProviderUtils.class)
    public void testDataProvider(String keyWord) {
        System.out.println(keyWord);
    }

    @Test(dataProvider = "test-data-new", dataProviderClass = DataProviderUtils.class)
    public void search(String keyWord) {
        WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        txtBox.sendKeys(keyWord);
        Reporter.log("Keyword entered is : " + keyWord);
        txtBox.sendKeys(Keys.ENTER);
        Reporter.log("Search results are displayed.");
    }


    // Passing Multiple Parameter Values in TestNG DataProviders
    @DataProvider(name = "test-data")
    public Object[][] dataProvFunc() {
        return new Object[][]{
                {"Selenium", "Delhi"},
                {"QTP", "Bangalore"},
                {"LoadRunner", "Chennai"}
        };
    }

    @Test(dataProvider = "test-data")
    public void search(String keyWord1, String keyWord2) {
        WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        txtBox.sendKeys(keyWord1, " ", keyWord2);
        Reporter.log("Keyword entered is : " + keyWord1 + " " + keyWord2);
        txtBox.sendKeys(Keys.ENTER);
        Reporter.log("Search results are displayed.");
    }


    @Test(dataProvider = "test-data", dataProviderClass = DataProviderUtils.class)
    public void testDataProvider1(String keyWord1,String keyWord2) {
        System.out.println("keyWord1 :"+keyWord1 +" keyWord2 :"+keyWord2);
    }


    @DataProvider(name = "LogInInformation")
    public static Object[][] logInData() {
        return new Object[][]{
                {"OoompaLoompa", "awwNoIDidItAgain"},
                {"OhSnap", "lifeManJustLife"},
                {"HereWeGoAgain", "INeedMotivation"},
        };
    }

    // DataProvider in TestNG using Excel
    @DataProvider(name = "RegistrationDataFromExcel")
    public static Object[][] getRegistrationDataFromExcel() {
        return ReadExcelDataDrivenApproach.getRegistrationTestData("AccountInfo");
    }

    @Test(dataProvider = "RegistrationDataFromExcel", dataProviderClass = DataProviderUtils.class)
    public void testDataProviderUsingExcel(String firstName, String email,String password,String rePassword){
        System.out.println("keyWord1 :"+firstName +" keyWord2 :"+email+" keyWord3 :"+password+" keyWord4 :"+rePassword);
//        GlobalReUsableMethods.enterValueOnElement(AboutPage.about,firstName);
//        GlobalReUsableMethods.enterValueOnElement(AboutPage.about,email);
//        GlobalReUsableMethods.enterValueOnElement(AboutPage.about,password);
//        GlobalReUsableMethods.enterValueOnElement(AboutPage.about,rePassword);
    }


    @DataProvider(name = "excel-data")
    public Object[][] excelDP() throws IOException {
        //We are creating an object from the excel sheet data by calling a method that reads data from the excel stored locally in our system
        return getExcelData("Location of the excel file in your local system", "Name of the excel sheet where your data is placed");
    }

    //This method handles the excel - opens it and reads the data from the respective cells using a for-loop & returns it in the form of a string array
    public String[][] getExcelData(String fileName, String sheetName) {
        String[][] data = null;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(fis);
            XSSFSheet sh = wb.getSheet(sheetName);
            XSSFRow row = sh.getRow(0);
            int noOfRows = sh.getPhysicalNumberOfRows();
            int noOfCols = row.getLastCellNum();
            Cell cell;
            data = new String[noOfRows - 1][noOfCols];

            for (int i = 1; i < noOfRows; i++) {
                for (int j = 0; j < noOfCols; j++) {
                    row = sh.getRow(i);
                    cell = row.getCell(j);
                    data[i - 1][j] = cell.getStringCellValue();
                }
            }
        } catch (Exception e) {
            System.out.println("The exception is: " + e.getMessage());
        }
        return data;
    }

    @Test(dataProvider = "excel-data")
    public void searchNew(String keyWord1, String keyWord2) {
        WebElement txtBox = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        txtBox.sendKeys(keyWord1, " ", keyWord2);
        Reporter.log("Keyword entered is : " + keyWord1 + " " + keyWord2);
        txtBox.sendKeys(Keys.ENTER);
        Reporter.log("Search results are displayed.");
    }


}
