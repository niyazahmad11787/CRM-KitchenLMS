package com.qa.hippo.maintests.customercreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.customeCreationPages.CustomerDetailsStep1;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.RandomAddressGenerator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class verifyCustomerDetailsFormStepOne extends BaseClass {

    CustomerDetailsStep1 customerDetailsStep1;

    @BeforeMethod
    public void setUp(){
        customerDetailsStep1=new CustomerDetailsStep1(driver);
    }

    @Test(priority = 1)
    public void verifyCustomerDetailsFunctionalityForStepOne() throws InterruptedException {

        customerDetailsStep1.selectCustomerAndSubCustomerType(ConfigLoader.get("CUSTUMER_TYPE"),ConfigLoader.get("GST_NUMBER"),ConfigLoader.get("COA_NUMBER"), RandomAddressGenerator.generateZipCode());

    }
}
