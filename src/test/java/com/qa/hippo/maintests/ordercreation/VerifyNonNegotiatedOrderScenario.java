package com.qa.hippo.maintests.ordercreation;

import com.qa.hippo.main.baseclass.BaseClass;
import com.qa.hippo.main.pages.MoLogin;
import com.qa.hippo.main.pages.ordercreation.CreateOrder;
import com.qa.hippo.main.pages.ordercreation.HomePage;
import com.qa.hippo.main.utilities.ConfigLoader;
import com.qa.hippo.main.utilities.UtilClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerifyNonNegotiatedOrderScenario extends BaseClass {


    HomePage homePage;
    CreateOrder createOrder;
    UtilClass utilClass;

    @BeforeMethod
    public void setUp(){

        homePage=new HomePage(driver);
        createOrder=new CreateOrder(driver);
        utilClass=new UtilClass(driver);

    }

    @Test(priority = 1)
    public void verifyClickOnOrderButtonOnHomePage(){
        homePage.clickOnOrderButtonOnHomePage();
    }
    @Test(priority = 2)
    public void verfiyClickOnCreateRequestButton(){
        homePage.clickOnCreateRequestButton();
    }

    @Test(priority = 3)
    public void verifySelectDeliveryTypeAsDelivery(){
        createOrder.selectDeliveryType("Delivery");
    }

    @Test(priority = 4)
    public void verifySearchCustomer(){
        createOrder.searchCustomer(ConfigLoader.get("CREATED_CUSTOMER_MOBILE"));
    }

    @Test(priority = 5)
    public void verifyCustomerDetails(){
        createOrder.getCustomerName();
        createOrder.getDeliveryAddress();
        createOrder.getStoreName();
    }
    @Test(priority = 6)
    public void clickOnNextButton(){
        createOrder.clickOnNextButton();
    }
    @Test(priority = 7)
    public void verifySystemPriceToggle(){
        createOrder.printSystemPriceToggleState();
    }

    @Test(priority = 8)
    public void verifyRemarksIsDisabled(){
        createOrder.checkRemarksDropdown();
    }

    @Test(priority = 9)
    public void verifySearchSkuAndGetSkuDetails(){
        createOrder.searchSKU();
        createOrder.getSKUName();
        createOrder.getPrice();
        createOrder.getInventory();
        createOrder.getDesiredPrice();
    }

    @Test(priority = 10)
    public void verifyEnterQuantity(){
        createOrder.enterQuantity();
    }

    @Test(priority = 11)
    public void verifyFinalOrderValue(){
        createOrder.getOrderValue();
    }

    @Test(priority = 12)
    public void verifySkuAddedInLineItem(){
        createOrder.clickOnAddButton();
    }

    @Test(priority = 13)
    public void verifyCreateOrderOnFinalSubmit(){
        createOrder.clickOnSubmitButton();
        createOrder.checkOrderCreated();
        createOrder.searchRequestNumber();
    }

    @Test(priority = 14)
    public void verifyClickOnStageButton(){
        createOrder.clickOnSalesStageButton();
    }


    @Test(priority = 15)
    public void verifyOrderCreationFromSalesUser(){
        createOrder.scrollTillSubmitButton();
        createOrder.clickOnOkButton();
//        createOrder.clickOnOkButton();
//        createOrder.refreshPage();
    }

}
