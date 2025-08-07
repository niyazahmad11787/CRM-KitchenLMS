package com.qa.hippo.main.pages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MoLogin {

    WebDriver driver;

    public MoLogin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }

    @FindBy(xpath = "(//div[@data-testid='content-input']/input)[1]")
    WebElement UserID;
    @FindBy(xpath = "(//div[@data-testid='content-input']/input)[2]")
    WebElement Password;
    @FindBy(xpath = "//button[text()='Login']")
    WebElement LoginButton;

    @FindBy(xpath = "//input[@placeholder='Email']")
    WebElement EmailFieldMedusa;

    @FindBy(xpath = "//input[@placeholder='Password']")
    WebElement PasswordFieldMedusa;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement ContinueButtonMedusa;


    /**
     * Enters user id
     *
     * @param userId
     * @param waitForReload
     */
    public void enterUserId(String userId, WebDriverWait waitForReload) {
        try {
            waitForReload.until(ExpectedConditions.visibilityOf(UserID));
            UserID.sendKeys(userId);
            HTPLLogger.info("UserId is Entered!!");
        } catch (RuntimeException e) {
            HTPLLogger.error("Failure in test method EnterUserId", e);
            Assert.fail("Failure in test Method performLoginOperation!",e);
        }
    }
    /**
     * Enters user password
     *
     * @param waitForReload
     */
    public void enterPassword(String password, WebDriverWait waitForReload) {
        try {
            waitForReload.until(ExpectedConditions.visibilityOf(Password));
            Password.sendKeys(password);
            HTPLLogger.info("Password is Entered!!!");
        } catch (RuntimeException e) {
            HTPLLogger.error("Failure in test method enterPassword!!", e);
            Assert.fail("Failure in test method enterPassword!!",e);
        }
    }

    /**
     * Clicks on login button
     *
     * @param waitForReload
     */
    public void clickOnLoginButton(WebDriverWait waitForReload) {
        try {
            waitForReload.until(ExpectedConditions.elementToBeClickable(LoginButton));
            LoginButton.click();
            HTPLLogger.info("Clicked on Login Button!!");
        } catch (Exception e) {
            HTPLLogger.error("Failure in test method clickOnLoginButton!!", e);
            Assert.fail("Failure in test method clickOnLoginButton",e);
        }
    }

    public void performLoginOperation(String userName,String Password){
        WebDriverWait waitForReload = new WebDriverWait(driver, Duration.ofSeconds(2000));
        enterUserId(userName,waitForReload);
        enterPassword(Password,waitForReload);
        clickOnLoginButton(waitForReload);

    }


}
