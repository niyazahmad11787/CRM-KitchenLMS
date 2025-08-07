package com.qa.hippo.main.pages.customeCreationPages;

import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.RandomAddressGenerator;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;
import static com.qa.hippo.main.utilities.UtilClass.waitForElementPresent;

public class CustomerDetailsStep1 {

    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "(//div[@role='combobox'])[1]")
    WebElement customerType;

    @FindBy(xpath = "//li[@data-value='IHB']")
    WebElement IhbCustomer;

    @FindBy(xpath = "//li[@data-value='CONTRACTOR']")
    WebElement CONTRACTORCustomer;

    @FindBy(xpath = "//li[@data-value='RESELLER']")
    WebElement RESELLERCustomer;

    @FindBy(xpath = "(//div[@role='combobox'])[2]")
    WebElement subCustomerType;

    @FindBy(xpath = "//li[@data-value='Applicator']")
    WebElement applicator;

    @FindBy(xpath = "//li[@data-value='Architect']")
    WebElement architect;

    @FindBy(xpath = "//input[@name='gst_number']")
    WebElement gstNumberField;

    @FindBy(xpath = "//input[@name='coa_number']")
    WebElement coaNumberField;

    @FindBy(xpath = "//input[@name='businessName']")
    WebElement businessNameField;

    @FindBy(xpath = "//label[contains(text(), 'Business Address Line 1')]/following-sibling::div//textarea[@name='addressLine1']")
    WebElement businessLine1Field;

    @FindBy(xpath = "//label[contains(text(), 'Business Address Line 2')]/following-sibling::div//textarea[@name='addressLine2']")
    WebElement businessLine2Field;

    @FindBy(xpath = "//input[@name='pincode']")
    WebElement pinCode;

    @FindBy(xpath = "//button[contains(text(), 'Next')]")
    WebElement nextButton;

    public CustomerDetailsStep1(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);

    }
    // Select customer type based on the provided value

    public void selectCustomerAndSubCustomerType(String customerTypeValue, String gstNumber,String coaNumber,String pin) throws InterruptedException {

        waitForElementPresent(customerType,20);
        customerType.click();
        String addressOne= RandomAddressGenerator.generateBusinessAddressLine();
        String addressTwo=RandomAddressGenerator.generateBusinessAddressLine();
        String businessName=RandomAddressGenerator.generateBusinessName();
        switch (customerTypeValue.toUpperCase()) {
            case "IHB":
                HTPLLogger.info("Select IHB customer type and Applicator Sub-customer type from dropdown!!!");
                IhbCustomer.click();
                waitForElementPresent(subCustomerType, 20);  // Ensure the sub-customer type element is present
                subCustomerType.click();
                waitForElementPresent(applicator, 20);
                applicator.click();
                HTPLLogger.info("Enter Business Address!!!");
                enterBusinessAddress(addressOne,addressTwo);
                HTPLLogger.info("Enter PinCode!!!");
                enterPincode(pin);
                HTPLLogger.info("Click on Next Button!!!");
                clickOnNextButton();

                break;
            case "CONTRACTOR":
                CONTRACTORCustomer.click();
                HTPLLogger.info("Select Contractor customer type and Architect Sub-customer type from dropdown!!!");
                waitForElementPresent(subCustomerType, 20);
                subCustomerType.click();
                waitForElementPresent(architect, 20);
                architect.click();

                // Handle GST and COA number requirement for Architect
                if ((gstNumber != null && !gstNumber.isEmpty()) || (coaNumber != null && !coaNumber.isEmpty())) {
                    if (gstNumber != null && !gstNumber.isEmpty()) {
                        waitForElementPresent(gstNumberField, 20);
                        HTPLLogger.info("Enter GST number!!");
                        gstNumberField.sendKeys(gstNumber);
                        coaNumberField.click();
                    } else if (coaNumber != null && !coaNumber.isEmpty()) {
                        waitForElementPresent(coaNumberField, 20);
                        coaNumberField.sendKeys(coaNumber);
                    }
                } else {
                    HTPLLogger.info("Error: Either GST number or COA number must be provided for Architect.");
                }
                validateAutoPopulatedFieldWithWait(businessNameField, "Business Name", false); // Non-editable
                UtilClass.sleep(1000);
                validateAutoPopulatedFieldWithWait(businessLine1Field, "Business Address Line 1", true); // Editable
                UtilClass.sleep(1000);
                validateAutoPopulatedFieldWithWait(businessLine2Field, "Business Address Line 2", true); // Editable
                UtilClass.sleep(200);
                validateAndTruncateBusinessLine2();// Truncate Business Line 2 if necessary

                break;

            case "RESELLER":
                HTPLLogger.info("Select Reseller customer type from dropdown!!!");
                RESELLERCustomer.click();
                HTPLLogger.info("Enter Business Address!!!");
                businessNameField.sendKeys(businessName);
                enterBusinessAddress(addressOne,addressTwo);
                HTPLLogger.info("Enter PinCode!!!");
                enterPincode(pin);
                clickOnNextButton();

                break;

            default:
                HTPLLogger.info("Invalid customer type: " + customerTypeValue);
                break;
        }
    }

    // Enter business address lines

    public void enterBusinessAddress(String lineOne, String lineTwo) {
        waitForElementPresent(businessLine1Field,10);
        businessLine1Field.sendKeys(lineOne);
        waitForElementPresent(businessLine2Field,10);
        businessLine2Field.sendKeys(lineTwo);
    }
    // Enter pincode

    public void enterPincode(String pincode) {
        wait.until(ExpectedConditions.visibilityOf(pinCode));
        pinCode.clear();
        pinCode.sendKeys(pincode);
    }

    private void validateAutoPopulatedFieldWithWait(WebElement field, String fieldName, boolean shouldBeEditable) {

        // Wait until the field has a non-empty value (using JavaScript)
        wait.until(driver -> {
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
            String fieldValue = (String) jsExecutor.executeScript("return arguments[0].value;", field);
            return fieldValue != null && !fieldValue.isEmpty();
        });

        // Retrieve the value after ensuring it's populated
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String fieldValue = (String) jsExecutor.executeScript("return arguments[0].value;", field);

        boolean isEditable = field.isEnabled();

        if (fieldValue == null || fieldValue.isEmpty()) {
            HTPLLogger.info("Error: " + fieldName + " should be auto-populated from GST API.");
        } else if (shouldBeEditable != isEditable) {
            if (shouldBeEditable) {
                HTPLLogger.info("Error: " + fieldName + " should be editable.");
            } else {
                HTPLLogger.info("Error: " + fieldName + " should be non-editable.");
            }
        } else {
            HTPLLogger.info(fieldName + " is correctly auto-populated and "
                    + (shouldBeEditable ? "editable." : "non-editable."));
        }
    }

    private void validateAndTruncateBusinessLine2() throws InterruptedException {
        waitForElementPresent(businessLine2Field, 20);
        String line2Text = businessLine2Field.getAttribute("value");

        if (line2Text != null && line2Text.length() > 40) {
            String tripText = line2Text.substring(0, 40);

            businessLine2Field.sendKeys(Keys.chord(Keys.CONTROL, "a"));
            businessLine2Field.sendKeys(Keys.BACK_SPACE);
            businessLine2Field.sendKeys(tripText); // Re-enter truncated text
            JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
//            jsExecutor.executeScript("arguments[0].click();", nextButton);
            clickOnNextButton();
        } else {
            HTPLLogger.info("Business Line 2 is within the 40-character limit.");
        }
    }
    // Click on the Next button

    public void clickOnNextButton() throws InterruptedException {
        String currentUrl=driver.getCurrentUrl();
        waitForElementAndClick(nextButton,10);
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
        String newUrl=driver.getCurrentUrl();
        // Verify URL change to confirm successful submission
        if (!newUrl.equals(currentUrl)) {
            HTPLLogger.info("Form Redirected to step two: " + newUrl);
        } else {
            HTPLLogger.info("Form redirection failed. No URL change detected.");
        }

    }

}
