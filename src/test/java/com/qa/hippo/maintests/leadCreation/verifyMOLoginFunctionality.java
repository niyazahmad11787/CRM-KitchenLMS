package com.qa.hippo.maintests.leadCreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.utilities.ConfigLoader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyMOLoginFunctionality extends BaseClass {


    MoLogin moLogin;


    @BeforeMethod
    public void setUp(){
        openApplication();
        moLogin=new MoLogin(driver);
    }

    @Test(priority = 1)
    public void verifyMoLoginFunctionality(){
        moLogin.performLoginOperation(ConfigLoader.get("salesUser.username"),ConfigLoader.get("salesUser.password"));
    }

}
