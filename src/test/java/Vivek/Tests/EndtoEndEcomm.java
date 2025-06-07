package Vivek.Tests;//import org.junit.Assert;
import Vivek.Pageobjects.*;
import Vivek.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class EndtoEndEcomm extends BaseTest {
        String ProductName= "ZARA COAT 3";
    
        @Test(dataProvider="getData")
        public void submitOrder(HashMap<String,String> input ) throws IOException {

        ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("pass"));
        List<WebElement> productS =productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage= productCatalogue.GoToCart();
        Boolean match= cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match,input.get("product"));
        CheckoutPage checkoutPage= cartPage.GotoCheckout();
        checkoutPage.SelectCountry("India");
        ConfirmationPage confirmationPage= checkoutPage.submitOrder();
        String msg=confirmationPage.getConfirmationMessage();
        Assert.assertTrue(msg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }
    @Test(dependsOnMethods = {"submitOrder"})
    public void OrderHistoryTest(){
        ProductCatalogue productCatalogue=landingPage.loginApplication("rajvivek1206@gmail.com","Vivek@123");

        orderHeader OrderHeader= productCatalogue.GoToOrderpage();
       Assert.assertTrue(OrderHeader.VerifyOrderDisplay(ProductName));

    }

    @DataProvider
    public Object[][] getData() throws IOException {
       List<HashMap<String,String>> data= getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Vivek//Data//purchaseData.json");
        return new Object[][] {{data.get(1)},{data.get(0)}};
    }



//    HashMap<String,String> map =new HashMap<String,String>();
//        HashMap<String,String> map1 =new HashMap<String,String>();
//        map.put("email","rajvivek1206@gmail.com");
//        map.put("pass","Vivek@123");
//        map.put("product","ZARA COAT 3");
//
//        map1.put("email","shetty@gmail.com");
//        map1.put("pass","Iamking@000");
//        map1.put("product","ADIDAS ORIGINAL");
}
