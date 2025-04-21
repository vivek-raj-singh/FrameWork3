package Vivek.Pageobjects;

//import AbstractComponents.AbstractsComponents;
import Vivek.AbstractComponents.AbstractsComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractsComponents {
    WebDriver driver;

    public LandingPage(WebDriver driver){
        super(driver);
        this.driver= driver;
        PageFactory.initElements(driver,this);
    }
    //
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(id="userPassword")
    WebElement PasswordEle;
    @FindBy(id="login")
    WebElement submit;
    @FindBy(css="[class*='flyInOut']")
    WebElement errorMessage;

    public String getErrorMessage(){
        waitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }


    public ProductCatalogue loginApplication(String email, String password){
        userEmail.sendKeys(email);
        PasswordEle.sendKeys(password);
        submit.click();
        ProductCatalogue productCatalogue =new ProductCatalogue(driver);
        return productCatalogue;

    }
    public void Goto(){
        driver.get("https://rahulshettyacademy.com/client");
    }
}
