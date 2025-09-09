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
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.net.URL;
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

    /**
     * Initializes the WebDriver and browser settings.
     */
    @Parameters("Env")
    @BeforeSuite()
    public void setupBaseClass(String Env) {
        initializeLogger();
        launchBrowser(Env);
        loadBrowserConfiguration();
        generateMobileNumber();
    }

    /**
     * Launch browser (local or remote)
     */
    private void launchBrowser(String Env) {
        ConfigLoader.load(Env);
        String browserName = ConfigLoader.get("browser");
        System.out.println(browserName);
        String runOnJenkins = ConfigLoader.get("runonjenkins");
        String remote = ConfigLoader.get("remote"); // yes/no

        try {
            if (remote.equalsIgnoreCase("yes")) {
                // ✅ Selenium Grid execution
                DesiredCapabilities caps = new DesiredCapabilities();
                if (browserName.equalsIgnoreCase("chrome")) {
                    caps.setBrowserName("chrome");
                    System.out.println((browserName + " launched on Selenium Grid successfully"));

                } else if (browserName.equalsIgnoreCase("firefox")) {
                    caps.setBrowserName("firefox");
                } else if (browserName.equalsIgnoreCase("edge")) {
                    caps.setBrowserName("MicrosoftEdge");
                } else {
                    HTPLLogger.error("Invalid browser name for Grid execution");
                }
                driver = new RemoteWebDriver(new URL("http://localhost:4445/wd/hub"), caps);
                System.out.println(browserName + " launched on Selenium Grid successfully");

            } else {
                // ✅ Local execution
                if (browserName.equalsIgnoreCase("chrome")) {
                    if (runOnJenkins.equalsIgnoreCase("no")) {
                        initiateBrowser();
                    } else {
                        initiateHeadlessBrowser();
                    }
                    HTPLLogger.info("Chrome launched successfully");
                } else if (browserName.equalsIgnoreCase("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    HTPLLogger.info("Firefox launched successfully");
                } else if (browserName.equalsIgnoreCase("edge")) {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    HTPLLogger.info("Edge launched successfully");
                } else {
                    HTPLLogger.error("Browser not found, Please enter valid browser name");
                }
            }
        } catch (Exception e) {
            HTPLLogger.error("Error launching browser: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Opens the application at the given URL.
     */
    public void openApplication() {
        String url = ConfigLoader.get("appUrl");
        driver.get(url);
        driver.manage().deleteAllCookies();
    }

    /**
     * Generate a random mobile number and store it
     */
    public void generateMobileNumber() {
        mobile = UtilClass.generateMobileNumber();
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

        // ✅ Allow camera access
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.media_stream_camera", 1);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
    }

    private void initiateHeadlessBrowser() {
        WebDriverManager.chromedriver().setup(); // ✅ Auto-management
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("force-device-scale-factor=0.75");
        options.addArguments("high-dpi-support=0.75");
        options.addArguments("--disable-gpu");
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
