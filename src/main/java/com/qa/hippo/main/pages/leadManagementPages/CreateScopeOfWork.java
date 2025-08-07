package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.UtilClass;
import com.qa.hippo.main.utilities.OTPFetcher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;

public class CreateScopeOfWork {
    WebDriver driver;
    private static final Logger log = LogManager.getLogger(CreateScopeOfWork.class);

    public CreateScopeOfWork(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);  // Initialize elements
    }
    @FindBy(xpath = "(//p[text()='Project Type: '])[1]")
    WebElement selectSiteCard;

    @FindBy(xpath = "//input[@placeholder='Search by site code']")
    WebElement searchFieldForSiteCode;

    @FindBy(xpath = "//button[@type='button'][text()='Scope Of Work']")
    WebElement ScopeWorkTab;

    @FindBy(xpath = "//span[text()='MODULAR KITCHEN']/ancestor::label//input[@type='checkbox']")
    WebElement ModularKitcherCheckbox;

    @FindBy(xpath = "//span[text()='WARDROBE']/ancestor::label//input[@type='checkbox']")
    WebElement wardRobeCheckbox;

    @FindBy(xpath = "//span[text()='FULL HOME INTERIORS']/ancestor::label//input[@type='checkbox']")
    WebElement fullHomeInteriorsCheckbox;

    @FindBy(xpath = "//input[@placeholder='dd-mm-yyyy']")
    WebElement FollowUpDate;

    @FindBy(xpath = "//div[@aria-haspopup='listbox']")
    WebElement LeadOwner;

    @FindBy(xpath = "//ul[@role=\"listbox\"]/li[text()='BusinessM']")
    WebElement LeadOwnerOption;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement SubmitButton;

    @FindBy(xpath = "//div[contains(@class, 'MuiAlert-message') and text()='Opportunity Updated Successfully!']")
    WebElement SuccessMessage;

    public void ToverifySiteClick(){
        String mobile= ConfigLoader.get("CREATED_CUSTOMER_MOBILE");
        String site_Code = OTPFetcher.getSiteCode(mobile);
        log.info("Site code is : "+site_Code);
        searchFieldForSiteCode.sendKeys(site_Code);
        waitForElementAndClick(selectSiteCard,2000);
    }
    public void ToverifyScopeWorkTabClick(){
        waitForElementAndClick(ScopeWorkTab,2000);
    }
    public void ToVerifyServicesCheckBox(WebElement element){
        try {
            if (element.isSelected()) {
                log.info("Checkbox for "+element.getText()+" is already selected.");
            } else {
                log.info("Checkbox for "+element.getText()+" is not selected. Selecting it now.");
                element.click();
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void verifyModularKitchenCheckBox(){
        ToVerifyServicesCheckBox(ModularKitcherCheckbox);
    }
    public void verifyWardRobeCheckbox(){
        ToVerifyServicesCheckBox(wardRobeCheckbox);
    }

    public void verifyFullHomeInteriorCheckbox(){
        ToVerifyServicesCheckBox(fullHomeInteriorsCheckbox);
    }
    public void ToVerifyFollowupDate(){


        try {
            // Get the current value of the date field
            String dateValue = FollowUpDate.getAttribute("value");
            String specificDate = "15-12-2024"; // The specific date to set

            // Check if the field is prefilled or blank
            if (dateValue == null || dateValue.isEmpty()) {
                log.info("Date field is empty. Setting the specific date.");
            } else {
                log.error("Date field already has a value: " + dateValue + ". Updating to the specific date.");
            }

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1];", FollowUpDate, specificDate);

            // Optionally trigger an onchange event to ensure the change is recognized
            js.executeScript("arguments[0].dispatchEvent(new Event('change'));", FollowUpDate);
            log.info("Date set to: " + specificDate);
        } catch (RuntimeException e) {
            System.err.println("An error occurred while setting the date: " + e.getMessage());
            throw e; // Re-throw the exception for further handling
        }

    }

    public void ToverifyLeadOwner(String option){
        try {
            LeadOwner.click();
          WebElement dropOption=  driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='"+option+"']"));
            waitForElementAndClick(dropOption,20);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }


    }
    public void ToVerifyClickOnSubmitButton(){
        try {
            waitForElementAndClick(SubmitButton,2000);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }

    public void ToVerifyOpportunityCreated(){
        String successMsg="Opportunity Updated Successfully!";
        if (SuccessMessage.getText().equalsIgnoreCase(successMsg)){
            log.info(SuccessMessage.getText());
            UtilClass.sleep(2000);
//            UtilClass.refreshPage(driver);
        }
        else {
            log.error("Failed to created Opportunity!");
        }
    }

}

