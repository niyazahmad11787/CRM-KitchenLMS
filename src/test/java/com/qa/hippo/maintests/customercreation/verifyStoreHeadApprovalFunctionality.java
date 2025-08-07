package com.qa.hippo.maintests.customercreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.MoLogout;
import com.qa.hippo.main.pages.customeCreationPages.MobileVerificationPage;
import com.qa.hippo.main.pages.customeCreationPages.ProfileApproval;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class verifyStoreHeadApprovalFunctionality extends BaseClass {


    MoLogin moLogin;
    MobileVerificationPage mobileVerificationPage;
    ProfileApproval storeHeadApproval;
    MoLogout moLogout;
    @BeforeMethod
    public void setUp(){
        moLogin=new MoLogin(driver);
        mobileVerificationPage=new MobileVerificationPage(driver);
        storeHeadApproval=new ProfileApproval(driver);
        moLogout=new MoLogout(driver);
    }

    @Test(priority = 1)
    public void verifyStoreHeadApprovalLogin(){
        WebDriverWait waitForReload = new WebDriverWait(driver, Duration.ofSeconds(30));
        moLogin.enterUserId(ConfigLoader.get("storehead.username"),waitForReload);
        moLogin.enterPassword(ConfigLoader.get("storehead.password"),waitForReload);
        UtilClass.sleep(2000);
        moLogin.clickOnLoginButton(waitForReload);
        UtilClass.sleep(2000);
        mobileVerificationPage.verifyClickCustomerButton();
    }
    @Test(priority = 2)
    public void verifyCustomerSearchOnIndexPage(){
        storeHeadApproval.profileSearchOnIndexPageForApproval();
    }

    @Test(priority = 3)
    public void verifyViewButtonAndDocument(){
        storeHeadApproval.verifyClickOnViewIcon();
    }

    @Test(priority = 4)
    public void verifyProfileApproval(){
        storeHeadApproval.verifyCustomerProfile("approve");
    }
    @Test(priority = 5)
    public void performLogout(){
        moLogout.logoutFunction();
    }

}
