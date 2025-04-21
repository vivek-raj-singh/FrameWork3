package Vivek.Pageobjects;

import Vivek.AbstractComponents.AbstractsComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class orderHeader extends AbstractsComponents {
    WebDriver driver;
    public orderHeader(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(css="tr td:nth-child(3)")
    private  List<WebElement> orderProducts;


    public Boolean VerifyOrderDisplay(String ProductName) {
        Boolean match =orderProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
        return match;
    }



}




//
//public class CartPage extends AbstractsComponents {
//    WebDriver driver;
//
//    public CartPage(WebDriver driver){
//        super(driver);
//        this.driver=driver;
//        PageFactory.initElements(driver,this);
//
//
//    }
//    @FindBy(css=".cartSection h3")
//    private  List<WebElement> cartProducts;
//
//    @FindBy(css=".totalRow button")
//    WebElement CheckOutEle;
//
//    public Boolean VerifyProductDisplay(String ProductName) {
//        Boolean match =cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(ProductName));
//        return match;
//    }
//    public CheckoutPage GotoCheckout(){
//        CheckOutEle.click();
//        return  new CheckoutPage(driver);
//    }
//}
