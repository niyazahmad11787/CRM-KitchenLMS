package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.OTPFetcher;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;

public class OpportunityApprovalPage {

    WebDriver driver;
    WebDriverWait wait;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static String scopeID;
    public OpportunityApprovalPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);  // Initialize elements
    }

    @FindBy(xpath = "//button[@type='button']//span[text()='CRM']")
    WebElement CrmButton;

    @FindBy(xpath = "//button[@type='button'][text()='Create Lead']")
    WebElement createLeadButton;

    @FindBy(xpath = "//input[@placeholder='Search...']")
    WebElement searchOpportunityID;
    @FindBy(xpath = "//label[contains(text(),'Interior Designer')]/following-sibling::div//div[@role='combobox']")
    WebElement interiorDesignerField;

    @FindBy(xpath = "//label[contains(text(),'Project Manager')]/following-sibling::div//div[@role='combobox']")
    WebElement projectManagerField;

    @FindBy(xpath = "//label[contains(text(),'Brand')]//following-sibling::div/div[@role='combobox']")
    WebElement brandField;

    @FindBy(xpath = "//input[@name='approx_qty']")
    WebElement approxQuantity;

    @FindBy(xpath = "//input[@name='approx_value']")
    WebElement approxValue;

    @FindBy(xpath = "//label[contains(text(),'Followup Date')]//following-sibling::div/input[@placeholder='dd-mm-yyyy']")
    WebElement followUpDate;
    @FindBy(xpath = "//label[contains(text(),'Exp Closure Date')]//following-sibling::div/input[@placeholder='dd-mm-yyyy']")
    WebElement ExpClosureDate;
    @FindBy(xpath = "//label[contains(text(),'Store Visit Date')]//following-sibling::div/input[@placeholder='dd-mm-yyyy']")
    WebElement storeVisitDate;
    @FindBy(xpath = "//label[text()='Conversion Potential']//following-sibling::div/div[@role='combobox']")
    WebElement conversionPotentialField;

    @FindBy(xpath = "//input[@name='remarks']")
    WebElement remarks;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitButton;

    @FindBy(xpath = "//p[contains(text(),\"Exp closure date can't be in the past\")]")
    WebElement expClosureDateError;

    @FindBy(xpath = "//p[contains(text(),\"Follow up date can't be in the past\")]")
    WebElement followupDateError;
    @FindBy(xpath = "//p[contains(text(),\"Store Visit date can't be in the past\")]")
    WebElement storeVisitDateError;

    @FindBy(xpath = "//button[@type='button'][text()='Update Follow up']")
    WebElement updateFollowUpButton;

    @FindBy(xpath = "//div[@role='combobox'][text()='In Progress']")
    WebElement LeadStatusDropDown;

    public void clickOnCustomerButton(){
        waitForElementAndClick(CrmButton,20);
    }
    public static String fetchOpportunityID(){
        String mobile= ConfigLoader.get("CREATED_CUSTOMER_MOBILE");
        System.out.println(mobile);
        scopeID= OTPFetcher.getOpportunityCode(mobile);
        System.out.println("Scope ID for approval :" +scopeID);
        return scopeID;
    }
    public static String getOpportunityID(){
        return scopeID;
    }

    public void clickOnCreateLeadButton(){
        UtilClass.sleep(2000);
        searchOpportunityID.sendKeys(fetchOpportunityID());
        UtilClass.sleep(2000);
        waitForElementAndClick(createLeadButton,2000);
    }
    public void checkLeadCreationFunctionality(){
        waitForElementAndClick(interiorDesignerField,2000);
        selectIDOption("Interior Designer");
        waitForElementAndClick(projectManagerField,2000);
        UtilClass.sleep(2000);
        selectIDOption("Project Manager");
        waitForElementAndClick(brandField,2000);
        selectIDOption(selectBrandName());
        approxQuantity.sendKeys(generateRandomPieces());
        approxValue.sendKeys(generateRandomApproxValue());
        String VisitDate = generateStoreVisitDate();
        String followUp_Date = generateFollowUpDate(VisitDate);
        String expClosure_Date = generateExpClosureDate(followUp_Date);
        setExpClosureDate(expClosure_Date);
        setFollowUpDate(followUp_Date);
        setStoreVisitDate(VisitDate);
        waitForElementAndClick(conversionPotentialField,2000);
        UtilClass.sleep(2000);
        selectIDOption(selectConversionPotential());
        UtilClass.sleep(2000);
        remarks.sendKeys("Created Lead by automation");
        waitForElementAndClick(submitButton,200);
    }
    public static LocalDate generateRandomFutureDate() {
        LocalDate today = LocalDate.now();
        long minDay = today.toEpochDay(); // Start from today
        long maxDay = today.plusYears(1).toEpochDay(); // Up to 1 year into the future
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);

    }
    public static String generateStoreVisitDate() {
        return generateRandomFutureDate().format(FORMATTER);
    }
    public static String generateExpClosureDate(String followUpDateStr) {
        LocalDate followUpDate = LocalDate.parse(followUpDateStr, FORMATTER);
        LocalDate expClosureDate = followUpDate.plusDays(ThreadLocalRandom.current().nextInt(1, 10));
        return expClosureDate.format(FORMATTER);
    }

    public static String generateFollowUpDate(String storeVisitDateStr) {
        LocalDate storeVisitDate = LocalDate.parse(storeVisitDateStr, FORMATTER);
        LocalDate followUpDate = storeVisitDate.plusDays(ThreadLocalRandom.current().nextInt(1, 10));
        return followUpDate.format(FORMATTER);
    }
    public void validateSubmitButton(){
        Assert.assertTrue(isExpClosureDateErrorDisplayed(),"Exp closure date can't be in the past");
        Assert.assertTrue(isFollowupDateErrorDisplayed(),"Follow up date can't be in the past!");
        Assert.assertTrue(isStoreVisitDateErrorDisplayed(),"Store visit Date date can't be in the past!");

    }
    /**
     * Set date in Exp Closure Date field.
     *
     * @param date The date to set (dd/MM/yyyy format).
     */
    public void setExpClosureDate(String date) {
        ExpClosureDate.clear();
        ExpClosureDate.sendKeys(date);
    }
    /**
     * Set date in Follow-up Date field.
     *
     * @param date The date to set (dd/MM/yyyy format).
     */
    public void setFollowUpDate(String date) {
        followUpDate.clear();
        followUpDate.sendKeys(date);
    }
    /**
     * Set date in Store Visit Date field.
     *
     * @param date The date to set (dd/MM/yyyy format).
     */
    public void setStoreVisitDate(String date) {
        storeVisitDate.clear();
        storeVisitDate.sendKeys(date);
    }
    public boolean isFollowupDateErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(followupDateError)).isDisplayed();
    }

    // Method to validate error message for Store Visit Date
    public boolean isStoreVisitDateErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(storeVisitDateError)).isDisplayed();
    }
    public boolean isExpClosureDateErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(expClosureDateError)).isDisplayed();
    }


    public void selectIDOption(String optionText){
        try {
            List<WebElement> options= driver.findElements(By.xpath("//ul[@role='listbox']//li"));

            for(WebElement option:options){
                if (option.getText().trim().equalsIgnoreCase(optionText)) {
                    option.click();
                    System.out.println("Selected option: " + optionText);
                    return;
                }
            }
            System.out.println("Option not found: " + optionText);

        } catch (Exception e) {
            System.out.println("Error selecting option: " + e.getMessage());
        }

    }

    private static final String[] Brand_Name = {
            "ASTRAL", "ASHIRVAD", "PLUMBER", "AQUINI", "JAQUAR",
            "JAQUAR FAUCET", "RADON", "ROCA", "SUPREME", "MULTITUBO","VALVEX"
    };
    public static String selectBrandName() {
        Random random = new Random();
        return Brand_Name[random.nextInt(Brand_Name.length)];
    }

    /**
     * Generates a random number between 1 and 500 and appends "Pieces".
     * @return A string in the format "X Pieces" where X is the random number.
     */
    public static String generateRandomPieces() {
        Random random = new Random();
        int randomNumber = random.nextInt(500) + 1;
        return randomNumber + " Pieces";
    }

    /**
     * Generates a random number between 1 and 500.
     * @return A string in the format "X" where X is the random number.
     */
    public static String generateRandomApproxValue() {
        Random random = new Random();
        int randomNumber = random.nextInt(500) + 1;
        return String.valueOf(randomNumber);
    }

    public static final String[] CONVERSION_POTENTIAL={
            "High", "Medium", "LOw"
    };
    public static String selectConversionPotential(){
        Random random=new Random();
        return CONVERSION_POTENTIAL[random.nextInt(CONVERSION_POTENTIAL.length)];
    }


    public void clickOnUpdateFollowUpbutton(){
        try {
            searchOpportunityID.sendKeys(getOpportunityID());
            UtilClass.sleep(1000);
            waitForElementAndClick(updateFollowUpButton,2000);
            HTPLLogger.info("Clicked on update follow up button!!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void changeLeadStatus(){
        try {
            UtilClass.sleep(1000);
            if (LeadStatusDropDown.isEnabled()){
                waitForElementAndClick(LeadStatusDropDown,2000);
                HTPLLogger.info("Clicked on Lead Status dropdown!!!");
                UtilClass.sleep(2000);
                selectLeadStatusOption("Boq Creation");
                UtilClass.sleep(2000);
                waitForElementAndClick(submitButton,2000);
            }else {
                HTPLLogger.error("Unable to click on lead status dropdown!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectLeadStatusOption(String status){
        try {
           WebElement statusOption= driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='"+status+"']"));
            if (statusOption.isEnabled()){
                waitForElementAndClick(statusOption,2000);
                HTPLLogger.info(status+" Option is selected");
            }else {
                HTPLLogger.error("Unable to select" +status);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
