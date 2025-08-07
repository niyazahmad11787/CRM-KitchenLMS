package com.qa.hippo.main.pages.customeCreationPages;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.utilities.ConfigWriter;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import com.qa.hippo.main.utilities.OTPFetcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.qa.hippo.main.utilities.UtilClass.waitForElementAndClick;
import static com.qa.hippo.main.utilities.UtilClass.waitForElementPresent;

public class MobileVerificationPage {


    WebDriver driver;
    private static String mobile_number;

    public MobileVerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this); // Initialize elements using PageFactory
    }


    @FindBy(xpath = "//button[@type='button']//span[text()='Customer']")
    WebElement customerButton;

    @FindBy(xpath = "//button[@type='button'][text()='Customer']")
    WebElement addCustomer;

    @FindBy(xpath = "//input[@id='otpVerificationDialogBoxMobile']")
    WebElement enterMobileField;

    @FindBy(id = "otpVerificationDialogBoxSubmit")
    WebElement generateOtpButton;
    @FindBy(xpath = "//input[contains(@aria-label, 'Digit')]")
    List<WebElement> otpFields;

    @FindBy(xpath = "//button[@id='otpVerificationDialogBoxSubmit']")
    WebElement submitOtpButton;

    public boolean verifyClickCustomerButton() {
        try {
            waitForElementPresent(customerButton,10);
            customerButton.click();
            HTPLLogger.info("Click on Customer Icon on HomePage..");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyClickAddCustomerButton() {
        try {
            waitForElementPresent(addCustomer, 10);
            HTPLLogger.info("Click on Add Customer button..");
            addCustomer.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String verifygetPhoneNumberFunctionality() {
        mobile_number = BaseClass.getMobile(); // This assumes BaseClass already generated the number
        HTPLLogger.info("Random Mobile Number generated: " + mobile_number);

        // ✅ Save the number to config.staging.properties
        ConfigWriter.setProperty("CREATED_CUSTOMER_MOBILE", mobile_number);

        return mobile_number;
    }
    public static String getMobile(){
        return mobile_number;
    }
    public void verifyEnterMobileNumber()
    {
        waitForElementPresent(enterMobileField, 10);
        HTPLLogger.info("Enter Mobile Number.....");
        enterMobileField.sendKeys(mobile_number);

    }
    // Method to click 'Generate OTP' button
    public boolean verifyClickGenerateOtpButton() throws InterruptedException {
        try {
            HTPLLogger.info("Click on Generate Otp button!!");
            waitForElementAndClick(generateOtpButton,10);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean verifyOTPFunctionality() throws InterruptedException {
        try {
            String otp= OTPFetcher.fetchOTP(mobile_number);
            if (otp != null) {
                enterOTP(otp);
                HTPLLogger.info("Enter otp and click on Submit button!!");
                clickSubmitOtpButton();
            } else {
                HTPLLogger.info("OTP not found for the specified mobile number.");
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void enterOTP(String otp) {

        if (otpFields.size() == 6) {
            for (int i = 0; i < otp.length(); i++) {
                otpFields.get(i).sendKeys(String.valueOf(otp.charAt(i)));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            HTPLLogger.info("Expected 6 OTP input fields, found: " + otpFields.size());
        }
    }
    public void clickSubmitOtpButton() throws InterruptedException {

        UtilClass.sleep(2000);
        submitOtpButton.click();

    }



}
