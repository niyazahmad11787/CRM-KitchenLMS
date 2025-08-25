package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.RandomAddressGenerator;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;
import static com.qa.hippo.main.utilities.UtilClass.waitForElementPresent;

public class CreateSitePage {
    WebDriver driver;
    WebDriverWait wait;
    public static String siteCode;

    public CreateSitePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);  // Initialize elements
    }

    @FindBy(xpath = "//button[@type='button']//span[text()='CRM']")
    WebElement CrmButton;

    @FindBy(xpath = "//div[@role='tablist']//button[text()='Customers']")
    WebElement Customer_Role_Button;

    @FindBy(xpath = "//input[@placeholder='Search...']")
    WebElement SearchBar;

    @FindBy(xpath = "//h6[text()='Cust ID: ']")
    WebElement SearchedCustomer;

    @FindBy(xpath = "//button[@type='button'][contains(text(),'Sites')]")
    WebElement SitesTab;

    @FindBy(css = "button.MuiButtonBase-root .MuiSvgIcon-root[data-testid='PersonAddIcon']")
    WebElement personAddButton;

    @FindBy(xpath = "//label[contains(text(),'Type of Project')]/following-sibling::div//div[@role='combobox']")
    WebElement typeOfProject;

    @FindBy(xpath = "//ul[@role='listbox']/li[text()='New']")
    WebElement new_TypeofProjectOption;

    @FindBy(xpath = "//label[contains(text(),'Type of Site')]/following-sibling::div//div[@role='combobox']")
    WebElement typeOfSite;

    @FindBy(xpath = "//ul[@role='listbox']/li[text()='Apartment']")
    WebElement Apartment_typeOfSiteOption;

    @FindBy(xpath = "//input[@name='pincode']")
    WebElement pinCode;

    @FindBy(xpath = "//input[@name='street_name_apartment_name']")
    WebElement societyName;

    @FindBy(xpath = "//input[@name='total_area']")
    WebElement carpetArea;

    @FindBy(xpath = "//button[@type='submit']")
    WebElement submitbutton;

    @FindBy(xpath = "//input[@placeholder='dd-mm-yyyy']")
    WebElement dateInput;
    @FindBy(xpath = "(//h6[text()='Site ID: '])[1]")
    WebElement siteID;

    public boolean verifyClickOnCrmButton() {
        try {
            waitForElementAndClick(CrmButton, 2000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyCustomerRoleButton() {
        try {
            waitForElementAndClick(Customer_Role_Button, 1000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void verfiySearchBar() {
        UtilClass.sleep(2000);
        String mobile = ConfigLoader.get("CREATED_CUSTOMER_MOBILE");
        SearchBar.sendKeys(mobile);
        UtilClass.sleep(2000);
        SearchBar.sendKeys(Keys.ENTER);
        waitForElementAndClick(SearchedCustomer, 1000);
    }

    public boolean verifySitesTab() {
        try {
            UtilClass.sleep(2000);
            SitesTab.click();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public boolean AddPersonButton() {
        try {
            UtilClass.sleep(2000);
            personAddButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public boolean ToVerifyTypeOfProjectDropdown(){

        try{
            waitForElementPresent(typeOfProject,2000);
            typeOfProject.click();
            UtilClass.sleep(2000);
            selectDropDownOption();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean ToverifyTypeOfSiteDropdown(){
        try {

            waitForElementAndClick(typeOfSite,2000);
            UtilClass.sleep(2000);
            selectDropDownOption();
            return true;

        } catch (Exception e) {
            return false;
        }
    }
    public boolean ToVerifyAddressFields(){
        try {
            pinCode.sendKeys(RandomAddressGenerator.generateZipCode());
            pinCode.sendKeys(Keys.ENTER);
            UtilClass.sleep(2000);
            societyName.sendKeys(RandomAddressGenerator.generateBusinessAddressLine());
            return true;
        } catch (Exception e) {
            return false;
        }

    }
    public boolean ToVerifyOtherDetailsFields(){
        try {
            UtilClass.sleep(2000);
            waitForElementPresent(carpetArea,2000);
            carpetArea.sendKeys("500.22");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Method to Create Site and validate URL change after submission.
     */


    public boolean ToVerifySiteCreated(){
        try {
            String currentUrl = driver.getCurrentUrl();
            UtilClass.sleep(2000);
            waitForElementAndClick(submitbutton,2000);
            wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));
            String newUrl=driver.getCurrentUrl();
            if (!newUrl.equals(currentUrl)) {
                HTPLLogger.info("Site created successfully! Redirected to: " + newUrl);
                waitForElementAndClick(SitesTab,2000);
                waitForElementPresent(siteID,2000);
                siteCode=siteID.getText();
            } else {
                HTPLLogger.error("Site creation failed ! No URL change detected.");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ToVerifyTentativePossessionDate(){

        try {
            // Define the specific date (e.g., 15th December 2024)
            int year = 2024;
            int month = 12; // December
            int day = 28;

            LocalDate specificDate = LocalDate.of(year, month, day);

            // Check if the specific date is in the future
            LocalDate today = LocalDate.now();
            if (!specificDate.isAfter(today)) {
                HTPLLogger.error("The specified date is not in the future. Please provide a valid future date.");

            }
            // Format the specific date to match the input field format
            String formattedDate = specificDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            HTPLLogger.info("Specific Date: " + formattedDate);
            UtilClass.sleep(2000);
            dateInput.sendKeys(formattedDate);

            HTPLLogger.info("Specific date entered successfully!");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public void selectDropDownOption(){
        try {
            List<WebElement> options = driver.findElements(By.xpath("//ul[@role='listbox']//li"));

            if (options.size() <= 1) {
                HTPLLogger.error("No valid options available to select.");
                return;
            }
            Random random = new Random();
            int randomIndex = 1 + random.nextInt(options.size() - 1);

            WebElement randomOption = options.get(randomIndex);
            randomOption.click();

            HTPLLogger.info("Randomly selected option: " + randomOption.getText());

        } catch (Exception e) {
            HTPLLogger.error("Error selecting random option: " + e.getMessage());
        }

    }
}
