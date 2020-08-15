package objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement clothingLink() {
        return driver.findElement(By.linkText("CLOTHING"));
    }

    public WebElement shirtsLink() {
        return driver.findElement(By.linkText("Shirts"));
    }

    public List<WebElement> colorFilter() {
        return driver.findElements(By.xpath("//div[@class='niatd4-1 kCgZbU']/div"));
    }

    public WebElement cookieBannerClose() {
        return driver.findElement(By.xpath("//img[@alt='cookie banner close']"));
    }

    public WebElement productCounter() {
        return driver.findElement(By.xpath("//div[@data-testid='Product__Counter']"));
    }

    public WebElement filters() {
        return driver.findElement(By.xpath("//div[@class='filtered-by']"));
    }

    public WebElement countryToggle() {
        return driver.findElement(By.xpath("//button[@data-test='CountrySelector__CountryToggle']"));
    }

    public WebElement enterCountryPlaceHolder() {
        return driver.findElement(By.xpath("//input[@placeholder='Search for shipping country']"));
    }

    public WebElement selectCountry(String country) {
        return driver.findElement(By.xpath("//a[contains(.,'" + country + "')]"));
    }

    public WebElement searchIcon() {
        return driver.findElement(By.xpath("//button[@aria-label='Search']"));
    }

    public WebElement searchTextBox() {
        return driver.findElement(By.xpath("//input[@placeholder=' Search']"));
    }

    public WebElement productItem() {
        return driver.findElement(By.xpath("(//span[@data-test='ProductCard__ProductBrand'])[1]"));
    }

    public WebElement sizeButton(String size) {
        return driver.findElement(By.xpath("//div[@data-testid='Size__Button' and contains(.,'" + size + "')]"));
    }

    public WebElement addToCartButton() {
        return driver.findElement(By.xpath("//button[@data-test='AddToCart__Button']"));
    }

    public WebElement overlay() {
        return driver.findElement(By.xpath("//div[@data-test='Overlay']"));
    }

    public WebElement cartCount() {
        return driver.findElement(By.xpath("//span[@data-testid=\"CartQuantityCounty__CartCount__Cart__Count\"]"));
    }

}
