package Vivek.Pageobjects;


import Vivek.AbstractComponents.AbstractsComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstractsComponents {
WebDriver driver;
    public CheckoutPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy (css="[placeholder='Select Country']")
    WebElement country;
    @FindBy(css = ".action__submit")
    WebElement Submit;
    @FindBy(xpath = "(//button[contains(@class,'ta-item')][2])")
    WebElement SelectCountry;
    By results = By.cssSelector(".ta-results");

    public void SelectCountry(String countryName){
        Actions a =new Actions(driver);
        a.sendKeys(country ,countryName).build().perform();
        waitForElementToAppear(results);
        SelectCountry.click();


    }
    public ConfirmationPage submitOrder(){
        Submit.click();
        return  new ConfirmationPage(driver);
    }




}
