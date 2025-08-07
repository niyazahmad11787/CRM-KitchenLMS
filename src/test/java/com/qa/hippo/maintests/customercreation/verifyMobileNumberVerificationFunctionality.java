package com.qa.hippo.maintests.customercreation;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.qa.hippo.main.pages.customeCreationPages.MobileVerificationPage;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.qa.hippo.main.baseclass.BaseClass.driver;

public class verifyMobileNumberVerificationFunctionality extends Serializers.Base {

    static MobileVerificationPage mobileVerificationPage;

    @BeforeMethod
    public void setUp(){
        mobileVerificationPage=new MobileVerificationPage(driver);
    }

    @Test(priority = 1)
    public static void verifyClickOnCustomerButton(){
        HTPLLogger.info("Customer Creation test Started !!!!");
        boolean isCustomerButtonClickable= mobileVerificationPage.verifyClickCustomerButton();
        Assert.assertTrue(isCustomerButtonClickable, "Customer button is not clickable!");

    }
    @Test(priority = 2 )
    public static void verifyClickOnAddCustomerButton(){
        boolean isAddCustomerButtonClickable= mobileVerificationPage.verifyClickAddCustomerButton();
        Assert.assertTrue(isAddCustomerButtonClickable, "Add Customer button is not clickable!");

    }
    @Test(priority = 3)
    public static void verifyMobileNumberInputField(){
        mobileVerificationPage.verifygetPhoneNumberFunctionality();
        mobileVerificationPage.verifyEnterMobileNumber();
    }
    @Test(priority = 4)
    public static void verifyOtpFunctionality() throws InterruptedException {
        boolean isOtpButtonClickable=  mobileVerificationPage.verifyClickGenerateOtpButton();
        Assert.assertTrue(isOtpButtonClickable, "Generate OTP button is not clickable!");
        boolean isOtpVerified = mobileVerificationPage.verifyOTPFunctionality();
        Assert.assertTrue(isOtpVerified, "OTP functionality verification failed!");
    }
}
