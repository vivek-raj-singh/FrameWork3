package Vivek.TestComponents;

import Vivek.Pageobjects.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("safebrowsing.enabled", true);
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            prefs.put("profile.password_manager_leak_detection", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--force-device-scale-factor=0.8");

            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;

    }

    @BeforeMethod(alwaysRun = true)
    public LandingPage launchApplication() throws IOException {
//        System.out.println("this is testff");
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
    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts= (TakesScreenshot) driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        File file=new File(System.getProperty("user.dir")+ "//reports//"+testCaseName+ ".png");
        FileUtils.copyFile(source,file);
        return System.getProperty("user.dir")+ "//reports//"+testCaseName+ ".png" ;
    }

    @AfterMethod(alwaysRun = true)
    public void driverclose() {
        if (driver != null) {
            driver.quit(); // Properly closes the session
        }

    }

}
