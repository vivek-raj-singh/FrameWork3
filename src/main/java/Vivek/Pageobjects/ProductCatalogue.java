package Vivek.Pageobjects;

//import AbstractComponents.AbstractsComponents;
//import Vivek.AbstractComponents.AbstractsComponents;
import Vivek.AbstractComponents.AbstractsComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AbstractsComponents {
    WebDriver driver;
    public ProductCatalogue(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css=".mb-3")
    List<WebElement> products;
    @FindBy(css= ".ng-animating")
    WebElement spinner;


    By productsBy= By.cssSelector(".mb-3");
        By AddToCart=By.cssSelector(".card-body button:last-of-type");
    By toastMessage =By.cssSelector("#toast-container");
    public List<WebElement> getProductList(){

        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String ProductName){
        return getProductList().stream().filter(pd->pd.findElement(By.cssSelector("b")).getText().
                equals(ProductName)).findFirst().orElse(null);
    }
    public void addProductToCart(String ProductName){
        WebElement prod =getProductByName(ProductName);
        prod.findElement(AddToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);

    }
}
