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

    @Test(priority = 1,enabled = false)
    public void verifyStoreUserLogin(){
        moLogin.performLoginOperation(ConfigLoader.get("salesUser.username"),ConfigLoader.get("salesUser.password"));
    }
    @Test(priority = 2)
    public void ToverifyCrmButton(){
        boolean isCrmButtonClickable= createSitePage.verifyClickOnCrmButton();
        Assert.assertTrue(isCrmButtonClickable, "CRM button is not clickable!");

    }

    @Test(priority = 3,dependsOnMethods = "ToverifyCrmButton")
    public void verifyCustomerButtonIsClickable(){
        boolean isCustomerButtonClickable = createSitePage.verifyCustomerRoleButton();
        Assert.assertTrue(isCustomerButtonClickable, "Customer button is not clickable!");
    }
    @Test(priority = 4,dependsOnMethods = "verifyCustomerButtonIsClickable")
    public void verifyCreatedCustomerOnIndexPage(){
        HTPLLogger.info("Search Created Customer!!!");
        createSitePage.verfiySearchBar();
    }
    @Test(priority = 5,dependsOnMethods = "verifyCreatedCustomerOnIndexPage")
    public void verifySiteTabeIsClickable(){
        HTPLLogger.info("Click on Site tab!!");
        boolean isSitesTabClickable = createSitePage.verifySitesTab();
        Assert.assertTrue(isSitesTabClickable, "Sites tab is not clickable!");
    }
    @Test(priority = 6,dependsOnMethods = "verifySiteTabeIsClickable")
    public void verifyThatAddPersonButton(){
        HTPLLogger.info("Click on Add Site Button !!");
        boolean isAddPersonButtonClickable = createSitePage.AddPersonButton();
        Assert.assertTrue(isAddPersonButtonClickable, "Add Person button is not clickable!");
    }
    @Test(priority = 7,dependsOnMethods = "verifyThatAddPersonButton")
    public void verifyTypeOfProject_And_TypeOfSiteDropdown() {
        HTPLLogger.info("Select Type of project and type of site!!");
        boolean isProjectDropdownCorrect = createSitePage.ToVerifyTypeOfProjectDropdown();
        boolean isSiteDropdownCorrect = createSitePage.ToverifyTypeOfSiteDropdown();
        Assert.assertTrue(isProjectDropdownCorrect, "Project dropdown values are incorrect!");
        Assert.assertTrue(isSiteDropdownCorrect, "Site dropdown values are incorrect!");
    }
    @Test(priority = 8,dependsOnMethods = "verifyTypeOfProject_And_TypeOfSiteDropdown")
    public void verifyAddressInputFields(){
        HTPLLogger.info("Enter Address Details !!!");
        boolean areAddressFieldsValid = createSitePage.ToVerifyAddressFields();
        Assert.assertTrue(areAddressFieldsValid, "Address input fields are not valid!");

    }
    @Test(priority = 9,dependsOnMethods = "verifyAddressInputFields")
    public void verifyOthersDataInputFields(){
        HTPLLogger.info("Enter Other Details like Carpet Area !!!");
        boolean areOtherFieldsValid=  createSitePage.ToVerifyOtherDetailsFields();
        Assert.assertTrue(areOtherFieldsValid, "Other input fields are not valid!");
    }

    @Test(priority = 10,dependsOnMethods = "verifyOthersDataInputFields")
    public void verifyTentativePossessionsDateIsSelected(){
        HTPLLogger.info("Enter Tentative Possessions Date!!!");
        boolean isDateSelected = createSitePage.ToVerifyTentativePossessionDate();
        Assert.assertTrue(isDateSelected, "Tentative possession date is not selected!");

    }
    @Test(priority = 11,dependsOnMethods = "verifyTentativePossessionsDateIsSelected")
    public void verifySiteisCreatedOrNot(){
        boolean isSiteCreated= createSitePage.ToVerifySiteCreated();
        Assert.assertTrue(isSiteCreated, "Site was not created successfully!");
    }
}
