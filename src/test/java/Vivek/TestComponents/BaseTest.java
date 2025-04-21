package Vivek.TestComponents;

import Vivek.Pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    public  WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initilizeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Vivek//Resources//GlobalData.properties");
        prop.load(fis);
        String BrowserName = prop.getProperty("Browser");

        if (BrowserName.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }
    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
        driver= initilizeDriver();
        landingPage =new LandingPage(driver);

        landingPage.Goto();
        return landingPage;
    }
    public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException {
        String jsonContent= FileUtils.readFileToString(new File(FilePath));
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String,String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
        return data;

    }

    @AfterMethod(alwaysRun = true)
    public void driverclose() {
        if (driver != null) {
            driver.quit(); // Properly closes the session
        }

    }

}
