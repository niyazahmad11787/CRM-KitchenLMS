package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyCustomerSentApprovalWithDiscountFunctionality extends BaseClass {

    MoLogin moLogin;
    CreateBoqPage createBoqPage;
    CreateSitePage createSitePage;

    @BeforeMethod
    public void setUp(){
        moLogin=new MoLogin(driver);
        createSitePage=new CreateSitePage(driver);
        createBoqPage=new CreateBoqPage(driver);
    }



    @Test(priority = 1)
    public void interiorDesignerLogin(){
        moLogin.performLoginOperation(ConfigLoader.get("interior.username"),ConfigLoader.get("interior.password"));
    }

    @Test(priority = 2)
    public void clickOnCrmButton(){
        createSitePage.verifyClickOnCrmButton();
    }
    @Test(priority = 3)
    public void searchTaskIDOnIndexPage(){
        createBoqPage.SearchEnquiryID();
    }





}
