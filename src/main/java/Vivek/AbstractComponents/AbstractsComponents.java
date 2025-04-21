package Vivek.AbstractComponents;

import Vivek.Pageobjects.CartPage;
import Vivek.Pageobjects.orderHeader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractsComponents {
WebDriver driver;
    public AbstractsComponents(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }
    @FindBy(css="[routerlink*='cart']")
    WebElement CartHeader;
    @FindBy(css="[routerlink*='myorders']")
    WebElement orderHeader;
    By tested=By.cssSelector(".mb-3");

    public void waitForElementToAppear(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }
    public void waitForWebElementToAppear(WebElement findBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        wait.until(ExpectedConditions.visibilityOf((WebElement) findBy));
    }
    public CartPage GoToCart(){
        CartHeader.click();
        return new CartPage(driver) ;
    }
    public orderHeader GoToOrderpage(){
        orderHeader.click();
        orderHeader orderHeader=new orderHeader(driver);
        return new orderHeader(driver) ;
    }

    public void waitForElementToDisappear(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));


    }
}