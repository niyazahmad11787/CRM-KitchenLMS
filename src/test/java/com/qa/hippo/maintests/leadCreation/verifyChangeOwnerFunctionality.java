package com.qa.hippo.maintests.leadCreation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.pages.leadManagementPages.ChangeOwner;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.leadManagementPages.OpportunityApprovalPage;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyChangeOwnerFunctionality extends BaseClass {


    ChangeOwner changeOwner;
    MoLogin moLogin;
    CreateSitePage createSitePage;
    CreateBoqPage createBoqPage;
    MoLogout moLogout;
    OpportunityApprovalPage opportunityApprovalPage;
    @BeforeMethod
    public void setUp(){
        changeOwner=new ChangeOwner(driver);
        moLogin=new MoLogin(driver);
        createSitePage=new CreateSitePage(driver);
        createBoqPage=new CreateBoqPage(driver);
        moLogout=new MoLogout(driver);
        opportunityApprovalPage=new OpportunityApprovalPage(driver);
    }
    @Test(priority = 1)
    public void leadUserLogin(){
        moLogin.performLoginOperation(ConfigLoader.get("leadUser.username"),ConfigLoader.get("leadUser.password"));
    }
    @Test(priority = 2)
    public void ToverifyCrmButton(){
        boolean isCrmButtonClickable= createSitePage.verifyClickOnCrmButton();
        Assert.assertTrue(isCrmButtonClickable, "CRM button is not clickable!");

    }
    @Test(priority = 3)
    public void searchTaskIDOnIndexPage(){
        changeOwner.searchOpportunity();
    }


    @Test(priority=4)
    public void verifyChangeOwnerFunctionality(){
        changeOwner.checkChangeOwnerButton();
        changeOwner.selectExecutive();
    }

    @Test(priority = 5)
    public void verifyChangeOwnerSubmission(){
        changeOwner.clickOnSubmitButton();
    }

    @Test(priority = 6)
    public void performLogout(){
        moLogout.logoutFunction();
    }

    @Test(priority = 7)
    public void executiveLogin(){
        moLogin.performLoginOperation(ConfigLoader.get("executive.username"),ConfigLoader.get("executive.password"));
    }

    @Test(priority = 8)
    public void ToverifyClickOnCrmButton(){
        boolean isCrmButtonClickable= createSitePage.verifyClickOnCrmButton();
        Assert.assertTrue(isCrmButtonClickable, "CRM button is not clickable!");

    }
    @Test(priority = 9)
    public void verifyLeadIsPresentOnIndexPage(){
        changeOwner.checkLeadOnIndexPage();
        opportunityApprovalPage.selectBudgetRange();
        opportunityApprovalPage.selectHouseType();
        opportunityApprovalPage.selectBHKConfig();
        opportunityApprovalPage.selectDetailedProjectScope();
        opportunityApprovalPage.checkLeadCreationFunctionality();
    }




}
