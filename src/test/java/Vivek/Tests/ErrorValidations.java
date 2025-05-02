package Vivek.Tests;

import Vivek.Pageobjects.CartPage;
import Vivek.Pageobjects.ProductCatalogue;
import Vivek.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v85.io.IO;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidations extends BaseTest {


    @Test
    public void errorValidate() throws IOException,InterruptedException{
        String productName="ZARA COAT 3";
        landingPage.loginApplication("rajvivek@gmail.com","Vivek12345");
            Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage());
            driver.close();
    }

    @Test
    public  void productErrorValidation() throws IOException, InterruptedException{
        String ProductName= "ZARA COAT 3";
        ProductCatalogue productCatalogue=landingPage.loginApplication("rajvivek1206@gmail.com","Vivek@123");


//        ProductCatalogue productCatalogue =new ProductCatalogue(driver);
        List<WebElement> products =productCatalogue.getProductList();
        productCatalogue.addProductToCart(ProductName);
        CartPage cartPage= productCatalogue.GoToCart();

        Boolean match= cartPage.VerifyProductDisplay("Zara coat 33");
        Assert.assertFalse(match,ProductName);
    }
}
