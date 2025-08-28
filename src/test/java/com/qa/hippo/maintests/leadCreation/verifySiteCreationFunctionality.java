package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifySiteCreationFunctionality extends BaseClass {

    static CreateSitePage createSitePage;
    MoLogin moLogin;
    @BeforeMethod
    public void setUp(){
        createSitePage=new CreateSitePage(driver);
        moLogin=new MoLogin(driver);
    }

    @Test(priority = 1)
    public void ToverifyCrmButton(){
        boolean isCrmButtonClickable= createSitePage.verifyClickOnCrmButton();
        Assert.assertTrue(isCrmButtonClickable, "CRM button is not clickable!");

    }

    @Test(priority = 2,dependsOnMethods = "ToverifyCrmButton")
    public void verifyCustomerButtonIsClickable(){
        boolean isCustomerButtonClickable = createSitePage.verifyCustomerRoleButton();
        Assert.assertTrue(isCustomerButtonClickable, "Customer button is not clickable!");
    }
    @Test(priority = 3,dependsOnMethods = "verifyCustomerButtonIsClickable")
    public void verifyCreatedCustomerOnIndexPage(){
        HTPLLogger.info("Search Created Customer!!!");
        createSitePage.verfiySearchBar();
    }
    @Test(priority = 4,dependsOnMethods = "verifyCreatedCustomerOnIndexPage")
    public void verifySiteTabeIsClickable(){
        HTPLLogger.info("Click on Site tab!!");
        boolean isSitesTabClickable = createSitePage.verifySitesTab();
        Assert.assertTrue(isSitesTabClickable, "Sites tab is not clickable!");
    }
    @Test(priority = 5,dependsOnMethods = "verifySiteTabeIsClickable")
    public void verifyThatAddPersonButton(){
        HTPLLogger.info("Click on Add Site Button !!");
        createSitePage.AddPersonButton();
    }
    @Test(priority = 6,dependsOnMethods = "verifyThatAddPersonButton")
    public void verifyTypeOfProject_And_TypeOfSiteDropdown() {
        HTPLLogger.info("Select Type of project and type of site!!");
        createSitePage.ToVerifyTypeOfProjectDropdown();
       createSitePage.ToverifyTypeOfSiteDropdown();
    }
    @Test(priority = 7,dependsOnMethods = "verifyTypeOfProject_And_TypeOfSiteDropdown")
    public void verifyAddressInputFields(){
        HTPLLogger.info("Enter Address Details !!!");
        createSitePage.ToVerifyAddressFields();
    }
    @Test(priority = 8,dependsOnMethods = "verifyAddressInputFields")
    public void verifyOthersDataInputFields(){
        HTPLLogger.info("Enter Other Details like Carpet Area !!!");
       createSitePage.ToVerifyOtherDetailsFields();
    }

    @Test(priority = 9,dependsOnMethods = "verifyOthersDataInputFields")
    public void verifyTentativePossessionsDateIsSelected(){
        HTPLLogger.info("Enter Tentative Possessions Date!!!");
        createSitePage.ToVerifyTentativePossessionDate();
    }
    @Test(priority = 10,dependsOnMethods = "verifyTentativePossessionsDateIsSelected")
    public void verifySiteisCreatedOrNot(){
        createSitePage.ToVerifySiteCreated();
    }
}
