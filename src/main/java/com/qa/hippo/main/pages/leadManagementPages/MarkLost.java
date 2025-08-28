package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class MarkLost {

    WebDriver driver;

    private String markLostExpectedMessage="Opportunity marked as lost successfully!";
    public MarkLost(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//button[text()='Mark as Lost']")
    WebElement markLostButton;

    @FindBy(xpath = "//input[@name='remarks']")
    WebElement remarksInputField;

    @FindBy(xpath = "//button[text()='Mark as Lost' and @type='submit']")
    WebElement markLostSubmitButton;

    @FindBy(xpath = "//div[text()='Opportunity marked as lost successfully!']")
    WebElement markLostSuccessMessage;

    public void clickOnMarkLostButton(){
        try {
            UtilClass.sleep(2000);

            if (markLostButton.isEnabled()){
                UtilClass.waitForElementAndClick(markLostButton,2000);
                HTPLLogger.info("Clicked on "+markLostButton.getText());
            }else {
                HTPLLogger.error("Unable to click on "+markLostButton.getText());
            }
        } catch (RuntimeException e) {
            Assert.fail("Issue in clickOnMarkLostButton method "+e);
        }
    }

    public void enterLostReason(){
        try {
            UtilClass.waitForElementPresent(remarksInputField,2000);
            if (remarksInputField.isDisplayed()){
                remarksInputField.sendKeys("Customer changed his mind!!!");
            }else {
                HTPLLogger.error("Lost remark field is not displayed!!");
            }

        } catch (RuntimeException e) {
            Assert.fail("Issue in enterLostReason"+e);
        }
    }

    public void clickOnMarkLostSubmitButton(){

        try {
            if (markLostSubmitButton.isEnabled()){
                markLostSubmitButton.click();
                HTPLLogger.info("Clicked on "+markLostSubmitButton.getText());
            }else {
                HTPLLogger.error("Unable to click on mark lost button!!");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkMarkLostIsDone(){
        try {

            UtilClass.waitForElementPresent(markLostSuccessMessage,2000);
            if (markLostSuccessMessage.getText().equalsIgnoreCase(markLostExpectedMessage)){
                HTPLLogger.info(markLostSuccessMessage.getText());
            }else {
                HTPLLogger.error("Lead is not mark as lost!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
