package com.qa.hippo.maintests.leadCreation;

import com.aventstack.extentreports.ExtentTest;
import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.pages.leadManagementPages.ChangeOwner;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.leadManagementPages.MarkLost;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyMarkLostFunctionality extends BaseClass {

    MarkLost markLost;
    MoLogin moLogin;
    CreateSitePage createSitePage;
    ChangeOwner changeOwner;
    MoLogout moLogout;
    @BeforeMethod
    public void setUp(){
        markLost=new MarkLost(driver);
        moLogin=new MoLogin(driver);
        createSitePage=new CreateSitePage(driver);
        changeOwner=new ChangeOwner(driver);
        moLogout=new MoLogout(driver);
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

    @Test(priority = 4)
    public void verifyMarkLostButtonIsClickable(){
        markLost.clickOnMarkLostButton();
    }
    @Test(priority = 5)
    public void verifyRemarksInputField(){
        markLost.enterLostReason();
    }

    @Test(priority = 6)
    public void verifyClickOnFinalMarkLostSubmitButton(){
        markLost.clickOnMarkLostSubmitButton();
        markLost.checkMarkLostIsDone();
    }

    @Test(priority = 7)
    public void performLogout(){
        moLogout.logoutFunction();
    }
}
