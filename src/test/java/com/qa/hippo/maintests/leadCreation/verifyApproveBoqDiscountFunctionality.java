package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.*;
import com.qa.hippo.main.pages.leadManagementPages.ApproveBoqDiscount;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.leadManagementPages.OpportunityApprovalPage;
import com.qa.hippo.main.utilities.ConfigLoader;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Slf4j
public class verifyApproveBoqDiscountFunctionality extends BaseClass {


    MoLogin login;
    CreateSitePage createSitePage;
    OpportunityApprovalPage opportunityApprovalPage;
    ApproveBoqDiscount approveBoqDiscount;
    CreateBoqPage createBoqPage;
    @BeforeMethod
    public void setUp(){
        login=new MoLogin(driver);
        createSitePage=new CreateSitePage(driver);
        approveBoqDiscount=new ApproveBoqDiscount(driver);
        opportunityApprovalPage=new OpportunityApprovalPage(driver);
        createBoqPage=new CreateBoqPage(driver);
    }

    @Test(priority = 1)
    public void verifyLeadUserLogin(){
        login.performLoginOperation(ConfigLoader.get("leadUser.username"),ConfigLoader.get("leadUser.password"));
    }
    @Test(priority = 2)
    public void ToverifyCrmButton(){
        boolean isCrmButtonClickable= createSitePage.verifyClickOnCrmButton();
        Assert.assertTrue(isCrmButtonClickable, "CRM button is not clickable!");

    }
    @Test(priority = 3)
    public void searchTaskIDOnIndexPage(){
        createBoqPage.SearchEnquiryID();
    }

    @Test(priority = 4)
    public void verifyApproveBoqDiscountButton(){
        approveBoqDiscount.clickOnApproveBoqDiscountButton();
    }


    @Test(priority = 5)
    public void verifyDetailsOnApproveBoqDiscountPopup(){
        approveBoqDiscount.getCustomerName();
        approveBoqDiscount.getProjectTypeFromPopUp();
        approveBoqDiscount.getDiscountValue();
        approveBoqDiscount.getGrossTotal();
    }

    @Test(priority = 6)
    public void verifyViewPdfButton(){
        approveBoqDiscount.clickOnViewPDF();
        approveBoqDiscount.closePDFWindow();
    }
    @Test(priority = 7)
    public void verifyConfirmDiscountButton(){
        approveBoqDiscount.clickOnProceedToConfirmButton();
    }
    @Test(priority = 8)
    public void verifyFinalApprovalButton(){
        approveBoqDiscount.finalApprovalButton();
    }
}
