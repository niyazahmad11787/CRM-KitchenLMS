package com.qa.hippo.main.pages.ordercreation;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.*;
import org.openqa.selenium.devtools.v85.webauthn.model.AuthenticatorId;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateOrder {

    WebDriver driver;
    public String requestNumber;

    public CreateOrder(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//input[@placeholder=\"Customer Search\"]")
    WebElement customerSearchField;

    @FindBy(xpath = "//input[@placeholder=\"Enter customer name\"]")
    WebElement customerNameField;

    @FindBy(xpath = "(//div[@role='combobox'])[3]")
    WebElement deliveryAddress;

    @FindBy(xpath = "(//div[@role='combobox' and @id='demo-simple-select'])[3]")
    WebElement storeName;

    @FindBy(xpath = "//button[@type='button'][text()='Next']")
    WebElement nextButton;

    @FindBy(xpath = "//label[contains(.,'System Price')]//input[@type='checkbox']")
    WebElement systemPriceToggle;

    @FindBy(xpath = "(//input[@id=\"combo-box-demo\"])[1]")
    WebElement remarksDropdown;

    @FindBy(xpath = "//input[@placeholder=\"SKU#\"]")
    WebElement skuSearchInputField;
    @FindBy(xpath = "(//p[text()='Price:']/following::input)[1]")
    WebElement priceField;

    @FindBy(xpath = "(//p[text()='Price:']/following::input)[2]")
    WebElement inventoryField;

    @FindBy(xpath = "//textarea[@aria-label=\"search1\"]")
    WebElement skuNameField;

    @FindBy(xpath = "//input[@placeholder=\"Desired Price\"]")
    WebElement desiredPriceField;

    @FindBy(xpath = "//input[@placeholder=\"Req Qty\"]")
    WebElement quantityField;

    @FindBy(xpath = "//input[@placeholder=\"Order Value\"]")
    WebElement orderValueField;

    @FindBy(xpath = "//button[@type='button'][text()='Add']")
    WebElement addButton;

    @FindBy(xpath = "//button[@type='button'][text()='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//p[@id=\"alert-dialog-description\"]")
    WebElement successMessage;

    @FindBy(xpath = "//button[@type='button'][text()='OK']")
    WebElement okButton;

    @FindBy(xpath = "//button[@type='button'][text()='Ok']")
    WebElement OkButton;

    @FindBy(xpath = "//input[@type='text' and @aria-label=\"search\"]")
    WebElement searchInputField;

    @FindBy(xpath = "//div[@data-testid='stageTest']/p")
    WebElement stageSalesButton;
    public void searchCustomer(String number){
        try {
            if (customerSearchField.isEnabled()){
                customerSearchField.sendKeys(number);
                UtilClass.sleep(2000);
               WebElement element= driver.findElement(By.xpath("//div[@role='presentation']//li[contains(text(), '"+number+"')]"));
               element.click();
               HTPLLogger.info("Customer is searched!!!");
            }else {
                HTPLLogger.error("Customer search field is not enabled!!");
            }

        } catch (RuntimeException e) {
            Assert.fail("Issue in searchCustomer method"+e);
        }
    }

    public void selectDeliveryType(String deliveryType){
        try {
            UtilClass.sleep(2000);
           WebElement element= driver.findElement(By.xpath("//input[@type='radio' and @value='"+deliveryType+"']"));
           if (element.isSelected()){
               HTPLLogger.info(element.getText()+" delivery type is selected!!!");
           }else {
               UtilClass.waitForElementAndClick(element,2000);
               HTPLLogger.error("No delivery type is selected!!");
           }

        } catch (RuntimeException e) {
            Assert.fail("Issue in selectDeliveryType method"+e);
        }
    }

    public void getCustomerName(){
        try {
            UtilClass.sleep(200L);
            if (!customerNameField.isEnabled()){
                HTPLLogger.info("Searched customer name :"+customerNameField.getAttribute("value"));
            }else {
                HTPLLogger.error("Unable to select customer name!!!");
            }

        } catch (RuntimeException e) {
            Assert.fail("Issue in getCustomerName method"+e);
        }
    }

    public void getDeliveryAddress(){
        try {

            UtilClass.sleep(2000);
            if (deliveryAddress.isEnabled()){
                HTPLLogger.info("Customer address :"+deliveryAddress.getText());
            }else {
                HTPLLogger.error("Unable to fetch customer address!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void getStoreName(){
        try {
            UtilClass.sleep(2000);
            if (!storeName.isEnabled()){
                HTPLLogger.info("Store Name is "+ storeName.getText());
            }else {
                HTPLLogger.error("Store name is enabled!!");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnNextButton(){
        try {
            UtilClass.sleep(200L);
            nextButton.click();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks the current state of the System Price toggle
     * @return true if ON, false if OFF
     */
    public boolean isSystemPriceToggleOn() {
        return systemPriceToggle.isSelected();
    }

    /**
     * Prints the toggle state
     */
    public void printSystemPriceToggleState() {
        if (isSystemPriceToggleOn()) {
            System.out.println("System Price toggle is ON");
        } else {
            System.out.println("System Price toggle is OFF");
            systemPriceToggle.click();
            HTPLLogger.info("Clicked on system price toggle!!");
        }
    }

    public void checkRemarksDropdown(){
        try {
            UtilClass.sleep(2000);
            if (!remarksDropdown.isEnabled()){
                HTPLLogger.info("Remarks dropdown is disabled!!");
            }else {
                HTPLLogger.info("Remarks dropdown is enabled , selecting remarks!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchSKU(){
        try {
            if (skuSearchInputField.isEnabled()){
                skuSearchInputField.sendKeys("1000000023");
                UtilClass.sleep(2000);
                WebElement element= driver.findElement(By.xpath("//div[@role='presentation']//li[contains(text(), '1000000023')]"));
                element.click();
            }else {
                HTPLLogger.error("Unable to search sku!!");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void getPrice(){
        try {
            UtilClass.sleep(2000);
            if (!priceField.isEnabled()){
                HTPLLogger.info("Sku Price :" + priceField.getAttribute("value"));
            }else {
                HTPLLogger.error("Issue in getPrice method , Price field is enabled!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void getInventory(){
        try {
            UtilClass.sleep(2000);
            if (!inventoryField.isEnabled()){
                HTPLLogger.info("Inventory :" + inventoryField.getAttribute("value"));
            }else {
                HTPLLogger.error("Issue in getInventory method , Inventory field is enabled!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void getSKUName(){
        try {
            UtilClass.sleep(2000);
            if (!skuNameField.isEnabled()){
                HTPLLogger.info("SKU Name :" + skuNameField.getAttribute("value"));
            }else {
                HTPLLogger.error("Issue in getSKUName method , SKU Name field is enabled!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void getDesiredPrice(){
        try {
            UtilClass.sleep(2000);
            if (!desiredPriceField.isEnabled()){
                HTPLLogger.info("Desired price :" + desiredPriceField.getAttribute("value"));
            }else {
                HTPLLogger.error("Issue in getDesiredPrice method , Desired Price field is enabled!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void enterQuantity(){
        try {
            UtilClass.sleep(2000);
            if (quantityField.isEnabled()){
                quantityField.sendKeys("150");
            }else {
                HTPLLogger.error("Quantity field is disabled!!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void getOrderValue(){
        try {
            UtilClass.sleep(2000);
            if (!orderValueField.isEnabled()){
                HTPLLogger.info("Total Order value :" + orderValueField.getAttribute("value"));
            }else {
                HTPLLogger.error("Order field is enabled!!!");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnAddButton(){
        try {
            UtilClass.sleep(2000);
            UtilClass.waitForElementAndClick(addButton,2000);
            HTPLLogger.info("Clicked on Add button!!");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnSubmitButton(){
        try {
            UtilClass.sleep(3000);
            UtilClass.waitForElementAndClick(submitButton,2000);
            HTPLLogger.info("Clicked on Add button!!");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkOrderCreated() {
        try {
            UtilClass.sleep(2000);

            if (successMessage.isDisplayed()) {
                String text = successMessage.getText();
                HTPLLogger.info("Full Success Message: " + text);

                // ✅ Extract only the request number (MSOR/xxxx)
                String requestNumber = "";
                if (text != null && !text.isEmpty()) {
                    // Regex way (most reliable)
                    Pattern pattern = Pattern.compile("(MSOR/\\d+)");
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        requestNumber = matcher.group(1);
                    }
                }

                if (!requestNumber.isEmpty()) {
                    HTPLLogger.info("Extracted Request Number: " + requestNumber);
                    this.requestNumber = requestNumber;  // store in class variable
                } else {
                    HTPLLogger.error("Could not extract request number from message: " + text);
                }

                UtilClass.sleep(200L);
                okButton.click();

            } else {
                HTPLLogger.error("Order is not created!!");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed in checkOrderCreated()", e);
        }
    }

    public void searchRequestNumber() {
        try {
            UtilClass.sleep(3000);

            if (requestNumber == null || requestNumber.isEmpty()) {
                HTPLLogger.error("Request number is null or empty, cannot search!");
                return;
            }

            if (searchInputField.isDisplayed()) {
                System.out.println("Searching with Request Number: " + requestNumber);
                searchInputField.clear();
                searchInputField.sendKeys(requestNumber);
            } else {
                HTPLLogger.error("Search Field is not visible");
            }

        } catch (RuntimeException e) {
            throw new RuntimeException("Failed while searching request number", e);
        }
    }

    public void clickOnSalesStageButton(){
        clickOnStageButton(stageSalesButton);

    }

    public void clickOnStageButton(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait until element is clickable
            WebElement clickableElement = wait.until(ExpectedConditions.elementToBeClickable(element));

            clickableElement.click();
            HTPLLogger.info("Clicked on Stage button!!");

        } catch (TimeoutException e) {
            HTPLLogger.error("Stage button was not clickable within timeout!");
            throw new RuntimeException("Stage button not clickable", e);
        } catch (RuntimeException e) {
            HTPLLogger.error("Failed to click on Stage button: " + e.getMessage());
            throw e;
        }
    }

    public void scrollTillSubmitButton(){
        UtilClass.scrollToElement(submitButton);
        clickOnSubmitButton();
    }

    public void clickOnOkButton(){
        try {
            if (OkButton.isEnabled()){
                UtilClass.waitForElementAndClick(OkButton,2000);
                HTPLLogger.info("Clicked on OkButton!!");
            }

        } catch (TimeoutException e) {
            HTPLLogger.error("OK Button was not clickable within timeout!");
            throw new RuntimeException("OK Button not clickable", e);
        } catch (ElementClickInterceptedException e) {
            HTPLLogger.warn("Normal click failed, retrying with JavaScript click...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", OkButton);
        } catch (RuntimeException e) {
            HTPLLogger.error("Failed to click on OK Button: " + e.getMessage());
            throw e;
        }
    }

    public void refreshPage(){
        UtilClass.sleep(3000);
        driver.navigate().refresh();
    }

}

