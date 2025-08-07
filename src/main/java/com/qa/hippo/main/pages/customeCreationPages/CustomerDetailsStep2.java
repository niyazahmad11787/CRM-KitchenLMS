package com.qa.hippo.main.pages.customeCreationPages;

import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CustomerDetailsStep2 {


    WebDriver driver;
    WebDriverWait wait;

    public CustomerDetailsStep2(WebDriver driver){
        this.driver=driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "//input[@name='first_name']")
    WebElement firstName;

    @FindBy(xpath = "//input[@name='last_name']")
    WebElement lastName;

    @FindBy(xpath = "//input[@name='alternateMobile']")
    WebElement alternateMobile;
    @FindBy(xpath = "//input[@name='email']")
    WebElement emailTextBox;

    @FindBy(xpath = "//input[@name='dob']")
    WebElement dobTextBox;

    @FindBy(xpath = "//input[@name='age']")
    WebElement ageField;

    @FindBy(xpath = "//input[@type='radio' and @value='Male']")
    WebElement maleRadioButton;

    @FindBy(xpath = "//input[@type='radio' and @value='Female']")
    WebElement femaleRadioButton;

    @FindBy(xpath = "//input[@type='radio' and @value='Single']")
    WebElement singleRadioButton;

    @FindBy(xpath = "//input[@type='radio' and @value='Married']")
    WebElement marriedRadioButton;

    @FindBy(xpath = "//input[@role='combobox']")
    WebElement educationDropdown;

    @FindBy(xpath = "(//div[@role='combobox' and @aria-haspopup='listbox'])[1]")
    WebElement occupationDropdown;

    @FindBy(xpath = "//input[@name='familySize']")
    WebElement familySizeTextField;

    @FindBy(xpath = "//label[contains(text(), 'Upload Visiting Card')]//input[@type='file']")
    WebElement visitingCard;

    @FindBy(xpath = "//li[@data-value='Letter of Incorporation']")
    WebElement letterOfIncorporation;

    @FindBy(xpath = "//label[contains(text(), 'Upload Site Photo')]//input[@type='file']")
    WebElement uploadSitePhoto;

    @FindBy(xpath = "//label[contains(text(), 'Upload Aadhaar Card')]//input[@type='file']")
    WebElement uploadAadhaarButton;

    @FindBy(xpath = "//label[contains(text(), 'Upload Cancelled Cheque')]//input[@type='file']")
    WebElement cancelledChequeVButton;

    @FindBy(xpath = "//label[contains(text(),'Upload PAN Card')]/input[@type='file']")
    WebElement uploadPanButton;

    @FindBy(xpath = "(//div[@role='combobox'])[2]")
    WebElement hearAboutDropdown;

    @FindBy(xpath = "//button[contains(text(), 'Capture Customer Photo')]")
    WebElement cupturePhotoButton;

    @FindBy(xpath = "//button[contains(text(), 'Capture Photo')]")
    WebElement clickPhotoButton;

    @FindBy(xpath = "//textarea[@name='notes']")
    WebElement notesTextField;
    @FindBy(xpath = "//button[contains(text(), 'Submit')]")
    WebElement submitbutton;


    // Enter first name

    public void enterFirstName(String fName) {
        wait.until(ExpectedConditions.visibilityOf(firstName));
        firstName.clear();
        firstName.sendKeys(fName);
    }
    // Enter last name

    public void enterLastName(String lName) {
        wait.until(ExpectedConditions.visibilityOf(lastName));
        lastName.clear();
        lastName.sendKeys(lName);
    }
    public void enterAlternateMobile(String mobile) {
        wait.until(ExpectedConditions.visibilityOf(alternateMobile));
        alternateMobile.clear();
        alternateMobile.sendKeys(mobile);
    }

    public void enterEmail(String email){
        wait.until(ExpectedConditions.visibilityOf(emailTextBox));
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }

    public void enterDateOfBirth(String dob){
        wait.until(ExpectedConditions.visibilityOf(dobTextBox));
        dobTextBox.clear();
        dobTextBox.sendKeys(dob);

    }

    public void checkAgeField(){
        UtilClass.sleep(2000);
        try {
            if (!ageField.isEnabled()){
                UtilClass.sleep(2000);
                System.out.println(ageField.getText());
                HTPLLogger.info("Calculated Age is ->" + ageField.getText());
            }else {
                HTPLLogger.error("Age field is enabled!!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectRadionbutton(WebElement option){
        try {
            if (!option.isSelected()){
                option.click();
                HTPLLogger.info(option.getText()+" is Selected!!");
            }else {
                HTPLLogger.error(option.getText()+" unable to select!!");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void verifyGenderRadioButton(){
        selectRadionbutton(maleRadioButton);
    }
    public void verifyMaritalStatus(){
        selectRadionbutton(singleRadioButton);
    }

    public void selectDropdownValue(WebElement element,String option){
        try {
            UtilClass.waitForElementPresent(element,2000);
            element.click();
            element.sendKeys(option);
            element.sendKeys(Keys.ARROW_DOWN);
            element.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void selectEducationOption(){
        selectDropdownValue(educationDropdown,"High School");
    }

    public void selectDropdown(WebElement element,String optionValue){
        UtilClass.waitForElementAndClick(element,2000);
       WebElement option=driver.findElement(By.xpath("//ul[@role='listbox']/li[text()='"+optionValue+"']"));
       UtilClass.waitForElementAndClick(option,2000);
    }

    public void selectOccupationValue(){
        selectDropdown(occupationDropdown,"Business");
    }

    public void slider(){
        selectIncomeRangeByLabel(driver, "10 - 50 Lakhs");
    }

    public void selectIncomeRangeByLabel(WebDriver driver, String incomeLabel) {
        try {
            WebElement label = driver.findElement(By.xpath("//span[contains(text(),'" + incomeLabel + "')]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", label);
            label.click();
            HTPLLogger.info("Selected income range: " + incomeLabel);
        } catch (Exception e) {
            HTPLLogger.error("Unable to select income range: " + incomeLabel);
            e.printStackTrace();
        }
    }

    public void enterFamilySize(){
        UtilClass.waitForElementPresent(familySizeTextField,2000);
        familySizeTextField.sendKeys("10");
    }

    public void hearAboutHippo(){
        selectDropdown(hearAboutDropdown,"Digital");
    }

    public void uploadVisitingCardButton(){
        UtilClass.sleep(2000);
        visitingCard.sendKeys(ConfigLoader.get("FILE"));
    }

    public void uploadAadharCard(){
        UtilClass.sleep(2000);
        uploadAadhaarButton.sendKeys(ConfigLoader.get("FILE"));
    }

    public void uploadSitePhoto(){
        UtilClass.sleep(1000);
        uploadSitePhoto.sendKeys(ConfigLoader.get("FILE"));
    }

    public void uploadCancelledCheque(){
        UtilClass.sleep(1000);
        cancelledChequeVButton.sendKeys(ConfigLoader.get("FILE"));
    }

    public void uploadPanCard(){
        UtilClass.sleep(1000);
        uploadPanButton.sendKeys(ConfigLoader.get("FILE"));
    }

    public void captureCustomerPhoto(){
      try {
          UtilClass.waitForElementAndClick(cupturePhotoButton,2000);
          UtilClass.sleep(2000);
          if (clickPhotoButton.isEnabled()){
              clickPhotoButton.click();
              HTPLLogger.info("Clicked on Capture photo button");
          }else {
              HTPLLogger.error("Unable to click on Capture photo button!!");
          }
      } catch (Exception e) {
          throw new RuntimeException(e);
      }

    }

    public void enterNotes(){
        notesTextField.sendKeys("Customer created through automated Selenium script for testing purposes.");
    }

    public void finalSubmitFormDetails() {
        try {
            if (submitbutton.isEnabled()) {
                String currentUrl = driver.getCurrentUrl();

                submitbutton.click();
                HTPLLogger.info("Clicked on Submit button !!!");

                // Wait until URL changes or timeout after 10 seconds
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(currentUrl)));

                String redirectedUrl = driver.getCurrentUrl();
                if (redirectedUrl.equals("https://orders.hippobetabox.in/customer/list")) {
                    HTPLLogger.info("✅ Customer created successfully and redirected to customer list page.");
                } else {
                    HTPLLogger.warn("⚠️ Submit button clicked, but redirection did not occur as expected.");
                    HTPLLogger.warn("Current URL: " + redirectedUrl);
                }

            } else {
                HTPLLogger.error("❌ Unable to click on submit button. It is either disabled or not interactable.");
            }

        } catch (Exception e) {
            HTPLLogger.error("🚫 Exception occurred while submitting the customer form: ", e);
            throw new RuntimeException(e);
        }
    }



}

