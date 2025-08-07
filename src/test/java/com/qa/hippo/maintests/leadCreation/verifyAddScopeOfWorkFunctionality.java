package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.leadManagementPages.CreateScopeOfWork;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class verifyAddScopeOfWorkFunctionality extends BaseClass {

    static CreateScopeOfWork addScopeOfWork;
    static MoLogout moLogout;
    static CreateSitePage createSitePage;
    @BeforeClass
    public void setup() {
        addScopeOfWork=new CreateScopeOfWork(driver);
        moLogout=new MoLogout(driver);
        createSitePage=new CreateSitePage(driver);
    }
    @Test(priority = 1)
    public void verifyClickOnSearchedSite(){
        createSitePage.verifySitesTab();
        HTPLLogger.info("Click on created site!!");
        addScopeOfWork.ToverifySiteClick();
    }
    @Test(priority = 2)
    public void verifySiteTabIsClickable(){
        HTPLLogger.info("Click on Scope of work tab!!!");
        addScopeOfWork.ToverifyScopeWorkTabClick();
    }
    @Test(priority = 3)
    public void verifyScopeOfWorkCheckBoxIsSelected(){
        addScopeOfWork.verifyModularKitchenCheckBox();
        HTPLLogger.info("WardRobe checkbox is selected!!");
//        addScopeOfWork.verifyWardRobeCheckbox();
//        HTPLLogger.info("WardRobe checkbox is selected!!");
    }

    @Test(priority = 4)
    public void verifyFollowUpDateIsShown(){
        HTPLLogger.info("Select Follow Up Date....");
        addScopeOfWork.ToVerifyFollowupDate();

    }
    @Test(priority = 5)
    public void verifyLeadOwnerDropdown(){
        addScopeOfWork.ToverifyLeadOwner(ConfigLoader.get("LEAD_USER"));
        HTPLLogger.info("Lead Owner Selected from dropdown....");

    }
    @Test(priority = 6)
    public void verifyOpportunityIsCreated(){
        HTPLLogger.info("Opportunity is created !!");
        addScopeOfWork.ToVerifyClickOnSubmitButton();
        addScopeOfWork.ToVerifyOpportunityCreated();
    }

    @Test(priority = 7)
    public void performLogout(){
    moLogout.logoutFunction();
    }
}
