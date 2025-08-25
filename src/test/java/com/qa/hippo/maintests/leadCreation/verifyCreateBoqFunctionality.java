package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.*;
import com.qa.hippo.main.pages.leadManagementPages.CreateBoqPage;
import com.qa.hippo.main.pages.leadManagementPages.CreateSitePage;
import com.qa.hippo.main.pages.leadManagementPages.OpportunityApprovalPage;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class verifyCreateBoqFunctionality extends BaseClass {


    MoLogin moLogin;
    OpportunityApprovalPage opportunityApprovalPage;
    CreateSitePage createSitePage;
    CreateBoqPage createBoqPage;
    MoLogout moLogout;
    @BeforeMethod
    public void setup() {
        moLogin=new MoLogin(driver);
        opportunityApprovalPage=new OpportunityApprovalPage(driver);
        createSitePage=new CreateSitePage(driver);
        createBoqPage=new CreateBoqPage(driver);
        moLogout=new MoLogout(driver);
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

    @Test(priority = 4)
    public void verifyClickOnCreateBOQButton(){
        createBoqPage.clickOnCreateBOQButton();
    }
    @Test(priority = 5)
    public void verifyServiceIsSelected(){
        createBoqPage.SelectServices();
    }
    @Test(priority = 6,enabled = false)
    public void verifyClickOnProductTab(){
        createBoqPage.clickOnProductTab();
    }
    @Test(priority = 7,enabled = false)
    public void verifyProductIsSelected(){
        createBoqPage.selectProducts();
    }
    @Test(priority = 8)
    public void verifyNextButton(){
        createBoqPage.clickOnNextButton();
    }

    @Test(priority = 9,enabled = false)
    public void selectSKU(){
        createBoqPage.SKUSearch();
    }
    @Test(priority = 10,enabled = false)
    public void verifySelectedSkuName(){
        createBoqPage.verifySelectedSKUName();
    }
    @Test(priority = 11)
    public void enterQuantityInService() throws InterruptedException {
        createBoqPage.enterQuantity();
    }
    @Test(priority = 12)
    @Parameters({"discountOption", "discountValue"})
    public void verifyDiscountfunctionality( @Optional("NO") String discountOption,
                                             @Optional("0") String discountValue){
        if (discountOption.equalsIgnoreCase("YES")) {
            createBoqPage.enterDiscount(discountValue);
            createBoqPage.clickOnSubmitApprovalButton();
        } else {
            System.out.println("No discount applied.");
            createBoqPage.clickOnSubmitbutton();
        }
        System.out.println("Driver after discount: " + BaseClass.driver);// Send only value
    }

    @Test(priority = 13)
    public void verifyPdfWindowIsClosed(){
        createBoqPage.closePDFWindow();
    }
    @Parameters("discountOption")
    @Test(priority = 14)
    public void performLogout(String discountOption) {
        if ("YES".equalsIgnoreCase(discountOption)) {
            moLogout.logoutFunction();
        } else {
            throw new SkipException("Skipping logout since boqApproval is YES.");
        }
    }

}
