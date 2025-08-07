package com.qa.hippo.main.utilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Random;

public class UtilClass {

    static WebDriver driver;
    static JavascriptExecutor js;

    public UtilClass(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    public static void scrollToElement(WebElement element) {

        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    /**
     * Pause program execution for specified amount of time
     *
     * @param millis
     */
    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Refreshes the current page of the browser.
     *
     * @param driver The WebDriver instance controlling the browser.
     */
    public static void refreshPage(WebDriver driver) {
        try {
            driver.navigate().refresh();
//            System.out.println("Browser page refreshed successfully.");
        } catch (Exception e) {
            System.err.println("Failed to refresh the browser: " + e.getMessage());
        }
    }

    /**
     * Helper method to click an element after waiting for it to be clickable.
     *
     * @param locator          The WebElement to click.
     * @param timeoutInSeconds Timeout in seconds to wait for the element to be clickable.
     */
    public static void waitForElementAndClick(WebElement locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        element.click();
    }

    /**
     * Waits until the specified element is visible on the page.
     *
     * @param element          The WebElement to wait for.
     * @param timeoutInSeconds Timeout duration in seconds.
     */
    public static void waitForElementPresent(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void pressEnter(WebElement element, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys(Keys.ENTER);
    }
    public static String generateMobileNumber() {
        Random random = new Random();

        // Generate the first digit as 6, 7, 8, or 9
        int firstDigit = 6 + random.nextInt(4);

        // Generate the remaining 9 digits randomly (0-9)
        StringBuilder mobileNumber = new StringBuilder();
        mobileNumber.append(firstDigit);

        for (int i = 0; i < 9; i++) {
            mobileNumber.append(random.nextInt(10));
        }

        return mobileNumber.toString();
    }

}



