package testCases;

import com.aventstack.extentreports.Status;
import framework.BasePage;
import objects.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends BasePage {
    WebDriver driver;
    private static Logger log = LogManager.getLogger(SearchTest.class.getName());

    JavascriptExecutor executor = (JavascriptExecutor) driver;

    @BeforeTest
    public void initialiseBrowser() throws IOException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("url"));
    }

    @Test(dataProvider = "testData")
    public void searchForProductAndDifferentSize(String product){
        HomePage homePage = new HomePage(driver);
        homePage.cookieBannerClose().click();
        homePage.searchIcon().click();
        homePage.searchTextBox().sendKeys(product);
        Listeners.threadLocal.get().log(Status.INFO, "search for product  " + product);
        log.info("search for product:: " +product);
        homePage.searchTextBox().sendKeys(Keys.ENTER);
        waitForLoading(1000);
        homePage.productItem().click();
        homePage.sizeButton("X-Small").click();
        homePage.addToCartButton().click();
        log.info("product added to cart successfully ");
        homePage.overlay().click();
        homePage.sizeButton("Medium").click();
        homePage.addToCartButton().click();
        log.info("product added to cart successfully ");
        homePage.overlay().click();
        waitForLoading(3000);
        log.info("Count of items added in cart = " + homePage.cartCount().getText());
        Listeners.threadLocal.get().log(Status.INFO, "Count of items added in cart =  " + homePage.cartCount().getText());

    }
    @DataProvider
    public Object[] testData() {
        Object[] data = new Object[1];
        data[0] = "shirts";
        return data;
    }

    @AfterTest
    public void closeBrowser() {
        driver.close();

    }

}
