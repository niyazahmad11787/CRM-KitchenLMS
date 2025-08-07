package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.SentForCustomerApprovalPage;
import com.qa.hippo.main.utilities.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyCustomerSentApprovalWithNoDiscountFunctionality extends BaseClass {

    SentForCustomerApprovalPage sentForCustomerApprovalPage;
    CreateBoqPage createBoqPage;

    @BeforeMethod
    public void setUp(){
        sentForCustomerApprovalPage=new SentForCustomerApprovalPage(driver);
        createBoqPage=new CreateBoqPage(driver);
    }

    @Test(priority = 1)
    public void searchTaskStatus(){
        UtilClass.sleep(2000);
        createBoqPage.SearchEnquiryID();
        HTPLLogger.info("Enquiry ID search completed successfully.");
    }
    @Test(priority = 2)
    public void verifyCustomerApprovalViaWhatsAppWhenButtonDisabled(){
        UtilClass.sleep(2000);
        sentForCustomerApprovalPage.checkSentForCustomerApprovalButton();
    }

    @Test(priority = 3)
    public void verifCustomerApprovalIsDone(){
        sentForCustomerApprovalPage.refreshPage();
        UtilClass.sleep(2000);
        createBoqPage.SearchEnquiryID();
        sentForCustomerApprovalPage.verifyCustomerApprovalIsDone();
    }

    @Test(priority = 4)
    public void verifyOrderIsCreatedOrNot(){
        sentForCustomerApprovalPage.createdOrder();
    }
    @Test(priority = 5)
    public void verifyPendingForPaymentButton(){
        sentForCustomerApprovalPage.checkPaymentPendingButton();
    }





}
