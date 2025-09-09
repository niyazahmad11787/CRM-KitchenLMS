package com.qa.hippo.main.pages.ordercreation;

import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePage {


    WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@type=\"button\"]//span[text()='Order']")
    WebElement orderButtonHomePage;

    @FindBy(xpath = "//button[@type=\"button\"][text()='Create Request']")
    WebElement createRequestButton;


    public void clickOnOrderButtonOnHomePage(){
        try {
            UtilClass.sleep(2000);
            if (orderButtonHomePage.isEnabled()){
                UtilClass.waitForElementAndClick(orderButtonHomePage,2000);
                HTPLLogger.info("Clicked on "+orderButtonHomePage.getText()+"Button on home page");
            }
            else {
                HTPLLogger.error("Unable to click on order button on home page!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnCreateRequestButton(){
        try {
            UtilClass.sleep(2000);
            if (createRequestButton.isEnabled()){
                UtilClass.waitForElementAndClick(createRequestButton,2000);
                HTPLLogger.info("Click on "+createRequestButton.getText());
            }else {
                HTPLLogger.error("Unable to click on create request button!!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }




}
