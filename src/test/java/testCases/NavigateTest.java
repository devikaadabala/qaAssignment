package testCases;

import com.aventstack.extentreports.Status;
import framework.BasePage;
import objects.HomePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigateTest extends BasePage {
    WebDriver driver;
    private static Logger log = LogManager.getLogger(NavigateTest.class.getName());

    @BeforeTest
    public void initialiseBrowser() throws IOException {
        driver = initialiseDriver();
        driver.get(properties.getProperty("url"));
    }

    @Test(dataProvider = "testData")
    public void navigateToSubCategory(String color) {
        HomePage homePage = new HomePage(driver);
        homePage.cookieBannerClose().click();
        Actions action = new Actions(driver);
        action.moveToElement(homePage.clothingLink()).perform();
        homePage.shirtsLink().click();
        log.info("Navigated to the shirts sub category");
        waitForLoading(3000);
        for (int i = 0; i < homePage.colorFilter().size(); i++) {
            if (homePage.colorFilter().get(i).getText().contains(color)) {
                homePage.colorFilter().get(i).click();
                log.info("Filtered " + color + "in color section");
                break;
            }
        }
        log.info("Filters selected:" + homePage.filters().getText());
        Assert.assertTrue(homePage.filters().getText().contains(color));
        waitForLoading(1000);
        log.info("Product Count after filter: " + homePage.productCounter().getText());
        Listeners.threadLocal.get().log(Status.INFO, "Product Count after filter: " + homePage.productCounter().getText());
    }


    @DataProvider
    public Object[] testData() {
        Object[] data = new Object[1];
        data[0] = "Black";
        return data;
    }

    //method to select specific country store
    public void switchCountry(String country) {
        HomePage homePage = new HomePage(driver);
        homePage.countryToggle().click();
        homePage.enterCountryPlaceHolder().sendKeys(country);
        waitForLoading(1000);
        homePage.selectCountry(country).click();

    }

    @AfterTest
    public void closeBrowser() {
        driver.close();
        log.info("Browser closed");
    }

}
