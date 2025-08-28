package com.qa.hippo.main.pages.leadManagementPages;

import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.qa.hippo.main.pages.leadManagementPages.OpportunityApprovalPage.fetchOpportunityID;
import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;

public class ChangeOwner{

    WebDriver driver;
    public ChangeOwner(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[text()='Change Owner']")
    WebElement changeOwnerButton;

    @FindBy(xpath = "//div[@role='combobox']")
    WebElement changeOwnerDropdown;

    @FindBy(xpath = "//button[text()='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//input[@placeholder='Search...']")
    WebElement searchOpportunityID;

    @FindBy(xpath = "//h6[text()='No data found']")
    WebElement noDataFound;

    @FindBy(xpath = "//button[@type='button'][text()='Create Lead']")
    WebElement createLeadButton;

    public void checkChangeOwnerButton(){
        try {
            UtilClass.sleep(2000);
            if (changeOwnerButton.isEnabled()){
                UtilClass.waitForElementAndClick(changeOwnerButton,2000);
                HTPLLogger.info("Clicked on changeOwner Button");
            }else {
                HTPLLogger.error("Unable to click on change owner button!!");
            }

        } catch (RuntimeException e) {
            Assert.fail("Issue in checkChangeOwnerButton method" +e);
        }
    }

    public void selectExecutive(){
        try {

            UtilClass.sleep(2000);
            if (changeOwnerDropdown.isDisplayed()){
                UtilClass.waitForElementAndClick(changeOwnerDropdown,2000);
                HTPLLogger.info("Clicked on change owner dropdown!!");
                UtilClass.sleep(2000);
                valueSelectFromDrop("CommonExe");
            }else {
                HTPLLogger.error("Unable to click on dropdown.!!!");
            }
        } catch (RuntimeException e) {
            Assert.fail("Issue in selectExecutive method" +e);
        }
    }

    public void valueSelectFromDrop(String value){
        try {
           WebElement element= driver.findElement(By.xpath("//ul/li[text()='"+value+"']"));
           UtilClass.waitForElementPresent(element,2000);
           element.click();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnSubmitButton(){
        try {
            UtilClass.waitForElementAndClick(submitButton,2000);
            UtilClass.sleep(2000);
            if (noDataFound.isDisplayed()){
                HTPLLogger.info("Change owner successfully assigned!!");
            }else {
                HTPLLogger.error("Unable change lead owner!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchOpportunity(){
        UtilClass.sleep(2000);
        searchOpportunityID.sendKeys(fetchOpportunityID());
    }

    public void checkLeadOnIndexPage(){
        try {
            searchOpportunity();
            if (createLeadButton.isEnabled()){
                waitForElementAndClick(createLeadButton,2000);
               HTPLLogger.info("Lead is present on index page and clicked on same!!");
            }else {
                HTPLLogger.error("Lead is not present on index page!!");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
