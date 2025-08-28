package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.leadManagementPages.SentForCustomerApprovalPage;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyCustomerSentApprovalWithDiscountFunctionality extends BaseClass {

    CreateBoqPage createBoqPage;
    SentForCustomerApprovalPage sentForCustomerApprovalPage;
    MoLogin moLogin;
    CreateSitePage createSitePage;
    MoLogout moLogout;

    @BeforeMethod
    public void setUp(){
        createBoqPage=new CreateBoqPage(driver);
        sentForCustomerApprovalPage=new SentForCustomerApprovalPage(driver);
        moLogin=new MoLogin(driver);
        createSitePage=new CreateSitePage(driver);
        moLogout=new MoLogout(driver);

    }

    @Test(priority = 1)
    public void searchTaskIDOnIndexPage(){
        sentForCustomerApprovalPage.refreshPage();
        UtilClass.sleep(3000);
        createBoqPage.SearchEnquiryID();
        HTPLLogger.info("Enquiry ID search completed successfully.");
    }

    @Test(priority = 2)
    public void verifyCustomerApprovalViaWhatsAppWhenButtonDisabled(){
        UtilClass.sleep(2000);
        sentForCustomerApprovalPage.checkSentForCustomerApproval();
    }

    @Test(priority = 3)
    public void verifCustomerApprovalIsDone(){
        sentForCustomerApprovalPage.refreshPage();
        UtilClass.sleep(2000);
        createBoqPage.SearchEnquiryID();
    }

    @Test(priority = 4)
    public void interiorLogin(){
        moLogout.logoutFunction();
        UtilClass.sleep(2000);
        moLogin.performLoginOperation(ConfigLoader.get("interior.username"),ConfigLoader.get("interior.password"));
    }
    @Test(priority = 5)
    public void clickOnCrmButton(){
        createSitePage.verifyClickOnCrmButton();
    }
    @Test(priority = 6)
    public void searchTaskIDOnPage(){
        createBoqPage.SearchEnquiryID();
    }
    @Test(priority = 7)
    public void verifyCreateOrderButtonIsEnabled(){
        sentForCustomerApprovalPage.verifyCustomerApprovalIsDone();
    }

    @Test(priority = 8)
    public void verifyOrderIsCreatedOrNot(){
        sentForCustomerApprovalPage.createdOrder();
    }
    @Test(priority = 9)
    public void verifyPendingForPaymentButton(){
        sentForCustomerApprovalPage.checkPaymentPendingButton();
    }




}
