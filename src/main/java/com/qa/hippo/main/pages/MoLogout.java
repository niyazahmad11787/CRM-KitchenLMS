package com.qa.hippo.main.pages;

import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;

public class MoLogout {


    WebDriver driver;

    public MoLogout(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }
    @FindBy(xpath = "//button[@aria-controls='menu-appbar']")
    WebElement AccountButton;

    @FindBy(xpath = "//li[@role=\"menuitem\"][text()='Logout']")
    WebElement logoutOption;

    public void logoutFunction(){
        try {
            UtilClass.sleep(3000);
            waitForElementAndClick(AccountButton,2000);
            waitForElementAndClick(logoutOption,2000);
            HTPLLogger.info("Logout Successfully!!!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDriver(){
        try {
            driver.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
