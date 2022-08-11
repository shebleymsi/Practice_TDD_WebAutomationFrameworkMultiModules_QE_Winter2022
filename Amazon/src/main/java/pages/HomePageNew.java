package pages;


import common.WebTestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static common.GlobalReUsableMethods.*;
import static common.WebTestBase.driver;
import static pageelement.HomePageNewElements.*;

public class HomePageNew extends WebTestBase {
    public HomePageNew(){
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = searchBoxWebElement)
    public WebElement searchBox;
    @FindBy(xpath = searchButtonWebElement)
    public WebElement searchButton;
    @FindBy(xpath = verifySearchedProductWebElement)
    public WebElement verifySearchedProduct;

    @FindBy(xpath = purellAdvancedHandSanitizerWebElement)
    public WebElement purellAdvancedHandSanitizer;
    @FindBy(xpath = oneTimePurchaseWebElement)
    public WebElement oneTimePurchase;
    @FindBy(xpath = selectQtyWebElement)
    public WebElement selectQty;
    @FindBy(xpath = setQtyWebElement)
    public WebElement setQty;
    @FindBy(xpath = addToShoppingCartWebElement)
    public WebElement addToShoppingCart;
    @FindBy(xpath = proceedToRetailCheckoutWebElement)
    public WebElement proceedToRetailCheckout;




    // Search Product
    public void searchValidProduct(String productName){
      //  searchBox.sendKeys("");
        enterValueOnElement(searchBox,productName);
        clickOnElement(searchButton);
    }
    public void selectPurellAdvancedHandSanitizer() throws InterruptedException {
        scrollDownToElement(purellAdvancedHandSanitizer);
        clickOnElement(purellAdvancedHandSanitizer);
        waitFor(5);
        clickOnElement(oneTimePurchase);
        clickOnElement(selectQty);
        clickOnElement(setQty);
        clickOnElement(addToShoppingCart);
        clickOnElement(proceedToRetailCheckout);
        waitFor(10);

    }







}
