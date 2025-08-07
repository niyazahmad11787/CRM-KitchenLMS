package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.OTPFetcher;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;
import static com.qa.hippo.main.utilities.UtilClass.waitForElementPresent;

public class CreateBoqPage {

    WebDriver driver;
    WebDriverWait wait;
    private static String enquiryID;
    public CreateBoqPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@type='button'][text()='Create BOQ']")
    WebElement createBoqButton;

    @FindBy(xpath = "//h6[text()='Product']")
    WebElement ProductTab;

    @FindBy(xpath = "//button[@type='button'][text()='Next']")
    WebElement nextButton;

    @FindBy(xpath = "//input[@role='combobox']")
    WebElement skuInputField;

    @FindBy(xpath = "//div[contains(@class, 'MuiBox-root')]//h6[contains(@class, 'MuiTypography-subtitle1') and contains(text(), 'PHILIPS_TEST')]")
    WebElement skuName;

    @FindBy(xpath = "//input[@type='number' and @placeholder='Enter discount']")
    WebElement discountField;

    @FindBy(xpath = "//button[@type='button'][text()='Send for Approval']")
    WebElement submitApprovalButton;

    @FindBy(xpath = "//button[@type='button'][text()='Submit']")
    WebElement submitButton;

    @FindBy(xpath = "//div[@role=\"presentation\"]/div/div")
    WebElement closePdfWindow;
    @FindBy(xpath = "//input[@placeholder='Search...']")
    WebElement searchEnquiryID;

    public void clickOnCreateBOQButton(){
        UtilClass.sleep(2000);
        waitForElementAndClick(createBoqButton,20);
    }
    public void clickOnServices() {
        if (selectedServices.isEmpty()) {
            System.out.println("No services selected. Call selectRandomServices() first!");
            return;
        }
        for (String service : selectedServices) {
            isCheckBoxSelected(service);
            UtilClass.sleep(200L);
        }
    }
    private static final String[] SERVICE_NAMES = {
            "Modular Kitchen Carcass", "Modular Kitchen Shutter", "Top Ply With Front Edge Band", "Wall Cabinet Filler 100X700(Hdhmr)", "Top Ply With Front Edge Band",
            "Shelf Base Back(Hdhmr)",
    };
    private static List<String> selectedServices = new ArrayList<>();
    public static void selectRandomServices(int count) {
        List<String> serviceList = Arrays.asList(SERVICE_NAMES);
        Collections.shuffle(serviceList);
        selectedServices = serviceList.subList(0, Math.min(count, SERVICE_NAMES.length));
    }
    public void isCheckBoxSelected(String articleName) {
        WebElement value = driver.findElement(By.xpath("//span[text()='" + articleName + "']/preceding::input[@type='checkbox'][1]"));
        if (!value.isSelected()) {
            value.click();
            System.out.println(articleName + " is selected now!");
        } else {
            System.out.println(articleName + " is already selected.");
        }
    }
    public void SelectServices(){
        selectRandomServices(1);
        clickOnServices();
    }


    public void clickOnProductTab(){
        waitForElementAndClick(ProductTab,20);
    }
    private static final String[] PRODUCT_NAMES = {
            "GTPT", "Cutlery Tray", "Drawer System", "DRAWER LOCK",
            "Liftup","DOOR LOCK","Rolling Shutter","S-Crausel","BPO","HANDLES "
    };
    private static List<String> selectedproducts = new ArrayList<>();

    public static void selectRandomProducts(int count) {
        List<String> serviceList = Arrays.asList(PRODUCT_NAMES);
        Collections.shuffle(serviceList);
        selectedproducts = serviceList.subList(0, Math.min(count, PRODUCT_NAMES.length));
    }
    public void clickOnProducts() {
        if (selectedproducts.isEmpty()) {
            System.out.println("No Products selected. Call selectRandomProducts() first!");
            return;
        }
        for (String product : selectedproducts) {
            isCheckBoxSelected(product);
            UtilClass.sleep(200L);
        }
    }

    public void selectProducts(){
        UtilClass.sleep(1000);
        selectRandomProducts(1);
        clickOnProducts();
    }

    public void clickOnNextButton(){
        waitForElementAndClick(nextButton,20);
    }



    public void SKUSearch(){
        UtilClass.sleep(2000);
        skuInputField.sendKeys("10000000");
        UtilClass.sleep(2000);
        WebElement skuOption = driver.findElement(By.xpath("//ul[@role='listbox']/li[contains(text(), '1000000023')]"));
        skuOption.click();
    }
    public void verifySelectedSKUName(){
        waitForElementPresent(skuName,2000);
        System.out.println("Selected Sku is - "+ skuName.getText());
    }

    public void enterQuantity() throws InterruptedException {
        List<WebElement> fields= driver.findElements(By.xpath("//input[@type='text' and @value='1']"));
        for (WebElement value:fields){
            value.sendKeys("2");
            Thread.sleep(200L);
        }

    }

    public void enterDiscount(String discount) {
        try {
                System.out.println("Entering discount value: " + discount);
                discountField.clear();
                discountField.sendKeys(discount);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Failed while entering discount: " + e.getMessage());
        }
    }

    public void clickOnSubmitApprovalButton(){
        System.out.println("Driver in clickOnSubmitApprovalbutton: " + driver);
        System.out.println("Element to click: " + submitApprovalButton);
        waitForElementAndClick(submitApprovalButton, 2000);
    }

    public void clickOnSubmitbutton(){
        System.out.println("Driver in clickOnSubmitbutton: " + driver);
        System.out.println("Element to click: " + submitButton);
        waitForElementAndClick(submitButton, 2000);
    }

    public void closePDFWindow(){
        UtilClass.sleep(3000);
        waitForElementAndClick(closePdfWindow,2000);
        System.out.println("PDF Window is Closed!!!!!!");
    }


    private boolean isSKUFieldPresent(WebElement item) {
        List<WebElement> skuField = item.findElements(By.xpath("//input[@role='combobox']"));
        return !skuField.isEmpty();
    }
    private void handleProduct(WebElement product) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        List<WebElement> skuFields = product.findElements(By.xpath("//input[@role='combobox']"));


        if (skuFields.size() < 2) {
            System.out.println("Not enough SKU fields found in the product container.");
            return;
        }
        WebElement firstSkuField = skuFields.get(0);
        firstSkuField.clear();
        firstSkuField.sendKeys("1000000023");
        WebElement skuOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@role='listbox']/li[contains(text(), '1000000023')]")));
        skuOption.click();
        js.executeScript("arguments[0].value='1000000023';", firstSkuField);
        firstSkuField.sendKeys(Keys.ENTER);
        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);  // Small pause to ensure focus shift

        // **Enter SKU in the second field**
        WebElement secondSkuField = skuFields.get(1);
        secondSkuField.clear();
        secondSkuField.sendKeys("2000000045");
        WebElement skuOption2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@role='listbox']/li[contains(text(), '2000000045')]")));
        skuOption2.click();
        js.executeScript("arguments[0].value='2000000045';", secondSkuField);
        secondSkuField.sendKeys(Keys.ENTER);
        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);

        WebElement thirdSkuField = skuFields.get(2);
        thirdSkuField.clear();
        thirdSkuField.sendKeys("2000000045");
        WebElement skuOption3 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//ul[@role='listbox']/li[contains(text(), '2000000045')]")));
        skuOption3.click();
        js.executeScript("arguments[0].value='2000000045';", thirdSkuField);
        thirdSkuField.sendKeys(Keys.ENTER);
        actions.sendKeys(Keys.TAB).perform();
        Thread.sleep(1000);

    }
    public void processItems() {
        List<WebElement> items = driver.findElements(By.xpath("//input[@role='combobox']")); // Adjust selector
        for (WebElement item : items) {
            try {
                if (isSKUFieldPresent(item)) {
                    System.out.println("Processing Product..."+item);
                    handleProduct(item);
                } else {
                    System.out.println("Processing Service..." + item);
//                    handleService(item);
                }
            } catch (Exception e) {
                System.out.println("Error processing item: " + e.getMessage());
            }
        }
    }

    public static String fetchEnquiryCode(){
        String mobile= ConfigLoader.get("CREATED_CUSTOMER_MOBILE");
        System.out.println(mobile);
        enquiryID= OTPFetcher.getEnquiryID(mobile);
        System.out.println("Scope ID for approval :" +enquiryID);
        return enquiryID;
    }

    public static String getEnquiryID(){
        return enquiryID;
    }

    public void SearchEnquiryID(){
        try {
            UtilClass.sleep(2000);
            searchEnquiryID.clear();
            searchEnquiryID.sendKeys(fetchEnquiryCode());
            HTPLLogger.info("Searched Opportunity ID : "+fetchEnquiryCode());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
