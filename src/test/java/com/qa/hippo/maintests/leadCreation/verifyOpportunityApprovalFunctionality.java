package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.pages.leadManagementPages.OpportunityApprovalPage;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class verifyOpportunityApprovalFunctionality extends BaseClass {

    static OpportunityApprovalPage opportunityApprovalPage;
    MoLogin loginPg;
    MoLogout moLogout;
    @BeforeClass
    public void setup() {
        loginPg=new MoLogin(driver);
        opportunityApprovalPage=new OpportunityApprovalPage(driver);
        moLogout=new MoLogout(driver);
    }

    @Test(priority = 1)
    public void ToVerifyLeadUserLogin(){
        loginPg.performLoginOperation(ConfigLoader.get("leadUser.username"),ConfigLoader.get("leadUser.password"));
    }
    @Test(priority = 2)
    public void ToVerifyApprovalFunctioanlity(){

        opportunityApprovalPage.clickOnCustomerButton();
        opportunityApprovalPage.clickOnCreateLeadButton();
        opportunityApprovalPage.selectBudgetRange();
        opportunityApprovalPage.selectHouseType();
        opportunityApprovalPage.selectBHKConfig();
        opportunityApprovalPage.selectDetailedProjectScope();
        opportunityApprovalPage.checkLeadCreationFunctionality();
    }

    @Test(priority = 3)
    public void verifyUpdateFollowUpButton(){
        opportunityApprovalPage.clickOnUpdateFollowUpbutton();
    }

    @Test(priority = 4)
    public void verifyChangeLeadStatus(){
        opportunityApprovalPage.changeLeadStatus();
    }

    @Test(priority = 5)
    public void performLogout(){
        moLogout.logoutFunction();
    }
}
