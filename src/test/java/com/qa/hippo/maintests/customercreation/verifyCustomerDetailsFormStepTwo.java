package com.qa.hippo.maintests.customercreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.customeCreationPages.CustomerDetailsStep2;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.qa.hippo.main.utilities.RandomNameGenerator.generateRandomFirstName;
import static com.qa.hippo.main.utilities.RandomNameGenerator.generateRandomLastName;

public class verifyCustomerDetailsFormStepTwo extends BaseClass {

    CustomerDetailsStep2 customerDetailsStep2;
    MoLogout moLogout;

    @BeforeMethod
    public void setUp(){
        customerDetailsStep2=new CustomerDetailsStep2(driver);
        moLogout=new MoLogout(driver);
    }

    @Test(priority = 1)
    public void verifyFirstAndLastNameInputFields(){
        HTPLLogger.info("Enter First & Last Name!!");
        customerDetailsStep2.enterFirstName(generateRandomFirstName());
        customerDetailsStep2.enterLastName(generateRandomLastName());
    }

    @Test(priority = 2)
    public void verifyAlternateMobileNumber(){
        customerDetailsStep2.enterAlternateMobile(getMobile());
        HTPLLogger.info("Entered alternate mobile number");
    }

    @Test(priority = 3)
    public void verifyEmaiId(){
        customerDetailsStep2.enterEmail("niyaz.ahmad@gmail.com");
        HTPLLogger.info("Entered email Id!!");
    }

    @Test(priority = 4)
    public void verifyDateOfBirth(){
        customerDetailsStep2.enterDateOfBirth("28/06/1998");
        customerDetailsStep2.checkAgeField();
    }

    @Test(priority = 5)
    public void verifyGenderRadioButton(){
        customerDetailsStep2.verifyGenderRadioButton();
    }

    @Test(priority = 6)
    public void verifyMaritalStatus(){
        customerDetailsStep2.verifyMaritalStatus();
    }

    @Test(priority = 7)
    public void verifyEducationDropDown(){
        customerDetailsStep2.selectEducationOption();
    }

    @Test(priority = 8)
    public void verifyOccupationDropdown(){
        customerDetailsStep2.selectOccupationValue();
    }

    @Test(priority = 9)
    public void verifySliderFunctionality(){
        customerDetailsStep2.slider();
    }

    @Test(priority = 10)
    public void verifyFamilySizeInputField(){
        customerDetailsStep2.enterFamilySize();
    }

    @Test(priority = 11)
    public void verifyHearAboutHippo(){
        customerDetailsStep2.hearAboutHippo();
    }
    @Test(priority = 12)
    public void verifyUploadVisitingCardButton(){
        customerDetailsStep2.uploadVisitingCardButton();
    }

    @Test(priority = 13)
    public void verifyUploadAadhaarCard(){
        customerDetailsStep2.uploadAadharCard();
    }

    @Test(priority = 14)
    public void verifyUploadSitePhotoButton(){
        customerDetailsStep2.uploadSitePhoto();
    }

    @Test(priority = 15)
    public void verifyUploadCancelledCheque(){
        customerDetailsStep2.uploadCancelledCheque();
    }
    @Test(priority = 16)
    public void verifyUploadPanCard(){
        customerDetailsStep2.uploadPanCard();
    }

    @Test(priority = 17)
    public void verifyCaptureCustomerPhoto(){
        customerDetailsStep2.captureCustomerPhoto();
    }
    @Test(priority = 18)
    public void verifyNotesField(){
        customerDetailsStep2.enterNotes();
    }

    @Test(priority = 19)
    public void verifyFinalSubmitButton(){
        customerDetailsStep2.finalSubmitFormDetails();
    }

    @Test(priority = 20)
    public void performLogout(){
        moLogout.logoutFunction();
    }

}
