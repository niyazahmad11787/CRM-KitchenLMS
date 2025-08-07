package com.qa.hippo.main.pages.customeCreationPages;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProfileApproval {

    WebDriver driver;

    public ProfileApproval(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@type='button' and @aria-label='close' ]")
    WebElement closButton;

    @FindBy(xpath = "//button[@type='button'][text()='Accept']")
    WebElement acceptButton;

    @FindBy(xpath = "//button[@type='button'][text()='Reject']")
    WebElement rejectButton;

    @FindBy(xpath = "//button[text()='OK']")
    WebElement okButton;
    public void clickOnApprovalIcon(String mobile){

        try {
            WebElement element=driver.findElement(By.xpath("//p[text()='"+mobile+"']/ancestor::li//button[@aria-label='action']"));
            if (element.isEnabled()){
                UtilClass.waitForElementAndClick(element,2000);
                HTPLLogger.info("Clicked on approval icon!!");
            }else {
                HTPLLogger.error("Unable to click on approval icon!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void profileSearchOnIndexPageForApproval(){
        clickOnApprovalIcon(BaseClass.getMobile());

    }

    public void verifyViewIcons(String docName){
     try {
         UtilClass.sleep(2000);
         WebElement viewIcon=driver.findElement(By.xpath("//h6[contains(text(),'"+docName+"')]/following::button[1]"));
         if (viewIcon.isEnabled()){
             UtilClass.waitForElementAndClick(viewIcon,2000);
             HTPLLogger.info("View button is enabled and Clicked on " + docName);
             UtilClass.sleep(2000);
             if (closButton.isEnabled()) {
                 UtilClass.waitForElementAndClick(closButton, 2000);
                 HTPLLogger.info("Clicked on close pdf icon.");
             }

         }else {
            HTPLLogger.error("Unable to click on"+docName);
         }

     } catch (Exception e) {
         throw new RuntimeException(e);
     }
    }


    public void verifyClickOnViewIcon(){
        verifyViewIcons("Aadhaar Card");
        verifyViewIcons("PAN Card");
        verifyViewIcons("Visiting Card");
        verifyViewIcons("Site Photo");
        verifyViewIcons("Customer Photo");
        verifyViewIcons("Cancelled Cheque");

    }

    public void verifyAcceptButton(){
        try {
            if (acceptButton.isEnabled()){
                UtilClass.waitForElementAndClick(acceptButton,2000);
                HTPLLogger.info("Reviewer approved the profile!!");
            }else {
                HTPLLogger.error("Unable to approve the profile!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void verifyRejectButton(){
        try {
            if (rejectButton.isEnabled()){
                UtilClass.waitForElementAndClick(rejectButton,2000);
                HTPLLogger.info("Reviewer rejected the profile!!");
            }else {
                HTPLLogger.error("Unable to reject the profile!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyCustomerProfile(String status) {
        try {
            switch (status.trim().toLowerCase()) {
                case "approve":
                    verifyAcceptButton();
                    clickOnOkButton();
                    break;
                case "reject":
                    verifyRejectButton();
                    clickOnOkButton();
                    break;
                default:
                    HTPLLogger.error("Invalid status provided: " + status);
                    throw new IllegalArgumentException("Status must be 'Approve' or 'Reject'");
            }
        } catch (Exception e) {
            throw new RuntimeException("Exception while verifying customer profile with status: " + status, e);
        }
    }

    public void clickOnOkButton(){
        UtilClass.sleep(2000);
        if (okButton.isEnabled()){
            UtilClass.waitForElementAndClick(okButton,2000);
            HTPLLogger.info("Clicked on Ok button!!");
        }
        else {
            HTPLLogger.info("Unable to click on Ok button!!");
        }
    }

}

