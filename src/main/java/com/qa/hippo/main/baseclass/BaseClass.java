package com.qa.hippo.main.baseclass;

import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public static Properties userProperties;
    public static String downloadFilepath = "C:\\Downloads";
    protected static ExtentTest extentTest;

    private static String mobile;

//    public ReadTestData testData;

    /**
     * Initializes the WebDriver and browser settings.
     */
    @Parameters("Env")
    @BeforeSuite()
    public void setupBaseClass(String Env){
        initializeLogger();
        launchBrowser(Env);
        loadBrowserConfiguration();
        generateMobileNumber();
    }

    /**
     * Launch browser
     *
     * @throws AWTException
     */
    private void launchBrowser(String Env) {
        ConfigLoader.load(Env);
        String browserName = ConfigLoader.get("browser");
        if (browserName.equalsIgnoreCase("chrome")) {
            if (ConfigLoader.get("runonjenkins").equalsIgnoreCase("no")) {
                initiateBrowser();
            } else if (ConfigLoader.get("runonjenkins").equalsIgnoreCase("yes")) {
                initiateHeadlessBrowser();
            }
            HTPLLogger.info("Chrome launched successfully");
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            HTPLLogger.info("firefox launch");
        } else if (browserName.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            HTPLLogger.error("Browser not found, Please enter valid browser name");
        }
    }
//    /**
//     * Initializes required resources
//     */
//    private void initializeResource() {
//        testData = new ReadTestData();
//    }

    /**
     * Opens the application at the given URL.
     */
    public void openApplication() {
        String url=ConfigLoader.get("appUrl");
        driver.get(url);
        driver.manage().deleteAllCookies();
    }

    /**
     * Generate a random mobile number and store it
     */
    public void generateMobileNumber(){
        mobile= UtilClass.generateMobileNumber();
        System.out.println(mobile);
    }

    /**
     * Getter method to access generated mobile number
     */
    public static String getMobile() {
        return mobile;
    }
    /**
     * Initializes logger
     */
    private void initializeLogger() {
        HTPLLogger.setClass(this);
    }
    private void initiateBrowser() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("high-dpi-support=0.75");

        // ✅ Add preferences to allow camera access
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1); // 1 = Allow
        options.setExperimentalOption("prefs", prefs);
        driver = new ChromeDriver(options);
    }
    private void initiateHeadlessBrowser() {
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("high-dpi-support=0.75");
        driver = new ChromeDriver(options);
    }
    /**
     * Configures browser
     */
    private void loadBrowserConfiguration() {
        driver.manage().window().maximize();
        HTPLLogger.info("Maximized browser");
        driver.manage().deleteAllCookies();
        HTPLLogger.info("Deleted all cookies");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
}
