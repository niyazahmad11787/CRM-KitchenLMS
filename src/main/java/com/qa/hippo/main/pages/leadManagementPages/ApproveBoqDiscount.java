package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.HTPLLogger;
import com.qa.hippo.main.utilities.UtilClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ApproveBoqDiscount{

    WebDriver driver;
    public String DiscountApprovalMessage="Whatsapp sent to customer!";
    public ApproveBoqDiscount(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@type='button'][text()='Approve BOQ Discount']")
    WebElement approveBoqDiscountButton;

    @FindBy(xpath = "//p[strong[contains(text(), 'Project Type:')]]")
    WebElement projectTypeName;

    @FindBy(xpath = "//p[strong[contains(text(), 'Customer Name:')]]")
    WebElement customerName;
    @FindBy(xpath = "//p[strong[contains(text(), 'Discount:')]]/following::input[1]")
    WebElement discountInput;

    @FindBy(xpath = "//p[strong[contains(text(), 'Gross Total:')]]")
    WebElement grossTotalAmount;

    @FindBy(xpath = "//button[@type='button'][text()='View BOQ (PDF)']")
    WebElement viewPdfButton;
    @FindBy(xpath = "(//div[@role=\"presentation\"]/div/div)[3]")
    WebElement closePdfWindow;

    @FindBy(xpath = "//button[@type='button'][text()='Proceed to Confirm']")
    WebElement proceedToConfirmButton;

    @FindBy(xpath = "//button[@type='button'][text()='Confirm and Approve']")
    WebElement finalApproveButton;

    @FindBy(xpath = "//div[text()='Whatsapp sent to customer!']")
    WebElement approvedMessage;
    public void clickOnApproveBoqDiscountButton(){
        try {
            UtilClass.sleep(2000);
            if (approveBoqDiscountButton.isEnabled()){
                UtilClass.waitForElementAndClick(approveBoqDiscountButton,2000);
                HTPLLogger.info("Clicked on Approve Boq Discount Button!!");
            }
            else {
                HTPLLogger.error("Approve Boq discount button is not enabled!! ");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getDetailsOnApproveDiscountPopup(WebElement element){
        try {
            UtilClass.waitForElementPresent(element,2000);
            if (element.isDisplayed()){
                HTPLLogger.info(element.getText());
            }else {
                HTPLLogger.error(element.getText()+" not shown!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getProjectTypeFromPopUp(){
        getDetailsOnApproveDiscountPopup(projectTypeName);
    }

    public void getCustomerName(){
        getDetailsOnApproveDiscountPopup(customerName);
    }

    public void getDiscountValue(){
        try {
            UtilClass.sleep(2000);
            String discountValue = discountInput.getAttribute("value");
            HTPLLogger.info("Discount % is : " + discountValue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getGrossTotal(){
        getDetailsOnApproveDiscountPopup(grossTotalAmount);
    }

    public void clickOnViewPDF(){
        try {
            UtilClass.sleep(2000);
            if (viewPdfButton.isEnabled()){
                UtilClass.waitForElementAndClick(viewPdfButton,2000);
                HTPLLogger.info("Clicked on view Pdf Button!!");
            }else {
                HTPLLogger.error("Unable to click on view Pdf Button!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void closePDFWindow(){
        try {
            if (closePdfWindow.isEnabled()){
                UtilClass.sleep(2000);
                closePdfWindow.click();
                HTPLLogger.info("Clicked on close pdf !!");
            }else {
                HTPLLogger.error("Close pdf button is not enabled!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void clickOnProceedToConfirmButton(){
        try {
            if (proceedToConfirmButton.isEnabled()){
                UtilClass.sleep(2000);
                proceedToConfirmButton.click();
                HTPLLogger.info("Clicked on Proceed to confirm button!!");
            }else {
                HTPLLogger.error("Unable to click on proceed to confirm button!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void finalApprovalButton(){
        try {
            if (finalApproveButton.isEnabled()){
                UtilClass.sleep(2000);
                finalApproveButton.click();
                HTPLLogger.info("Clicked on Final Approval Button !!");
            }else {
                HTPLLogger.error("Unable to click on final approval button!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void validateApprovalMessage(){
        try {
            UtilClass.sleep(2000);
            if (approvedMessage.getText().equalsIgnoreCase(DiscountApprovalMessage)){
                HTPLLogger.info(approvedMessage.getText());
            }else {
                HTPLLogger.error("Not Approved"+approvedMessage.getText());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
