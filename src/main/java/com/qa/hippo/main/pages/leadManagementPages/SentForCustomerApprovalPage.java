package com.qa.hippo.main.pages.leadManagementPages;

import com.qa.hippo.main.utilities.*;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SentForCustomerApprovalPage {

    WebDriver driver;
    public String messageID;
    public String mobileNumber;
    public String customerResponse="Yes I Approve";
    public SentForCustomerApprovalPage (WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//button[@type='button'][text()='Sent for Customer approval']")
    WebElement sentForCustomerApprovalButton;

    @FindBy(xpath = "//button[@type='button'][text()='Create Order']")
    WebElement createOrderButton;

    @FindBy(xpath = "//p[contains(text(), 'MSO/')]")
    WebElement orderNumber;

    @FindBy(xpath = "//button[@type='button'][text()='Pending for Payment']")
    WebElement pendingForPaymentButton;

    @FindBy(xpath = "//button[@type='button'][text()='Sent for customer approval']")
    WebElement sentForApprovalButton;

    /**
     * Checks if the "Sent for Customer Approval" button is disabled.
     * If it is disabled, proceeds to fetch message ID and trigger customer approval via WhatsApp.
     */
    public void checkSentForCustomerApprovalButton() {
        try {
            if (!sentForCustomerApprovalButton.isEnabled()) {
                String buttonText = sentForCustomerApprovalButton.getText();
                HTPLLogger.info(buttonText + " is disabled. Proceeding with customer approval...");
                fetchAndVerifyCustomerApproval();
            } else {
                HTPLLogger.warn(sentForCustomerApprovalButton.getText() + " is enabled. Skipping approval flow.");
            }
        } catch (Exception e) {
            HTPLLogger.error("Error in checkSentForCustomerApprovalButton: " + e.getMessage());
            throw new RuntimeException("Failed to check customer approval button", e);
        }
    }

    public void checkSentForCustomerApproval(){
        try {
            if (!sentForApprovalButton.isEnabled()) {
                String buttonText = sentForApprovalButton.getText();
                HTPLLogger.info(buttonText + " is disabled. Proceeding with customer approval...");
                fetchAndVerifyCustomerApproval();
            } else {
                HTPLLogger.warn(sentForApprovalButton.getText() + " is enabled. Skipping approval flow.");
            }
        } catch (Exception e) {
            HTPLLogger.error("Error in checkSentForCustomerApprovalButton: " + e.getMessage());
            throw new RuntimeException("Failed to check customer approval button", e);
        }
    }
    /**
     * Fetches the WhatsApp message ID for approval and triggers customer approval.
     */
    private void fetchAndVerifyCustomerApproval() {
        try {
            String enquiryId = CreateBoqPage.getEnquiryID();
            messageID = OTPFetcher.getMessageID(enquiryId);
            HTPLLogger.info("Fetched message ID: " + messageID);
            sendCustomerApprovalViaWhatsApp();
        } catch (Exception e) {
            HTPLLogger.error("Error while fetching message ID: " + e.getMessage());
            throw new RuntimeException("Failed to fetch message ID", e);
        }
    }

    /**
     * Sends approval response to WhatsApp API.
     */
    private void sendCustomerApprovalViaWhatsApp() {
        try {
            mobileNumber = ConfigLoader.get("CREATED_CUSTOMER_MOBILE");

            if (messageID != null && mobileNumber != null) {
               Response response= WhatsAppAPIUtility.sendWhatsAppReply(messageID, mobileNumber, customerResponse);
                System.out.println("Response Status Code: " + response.getStatusCode());
                System.out.println("Response Body: " + response.getBody().asString());
                HTPLLogger.info("Sent customer approval via WhatsApp for mobile: " + mobileNumber);
            } else {
                HTPLLogger.warn("Missing message ID or mobile number. Skipping WhatsApp approval.");
            }
        } catch (Exception e) {
            HTPLLogger.error("Error while sending WhatsApp approval: " + e.getMessage());
            throw new RuntimeException("Failed to send WhatsApp approval", e);
        }
    }

    public void verifyCustomerApprovalIsDone(){
        try {
            UtilClass.sleep(2000);
            if (createOrderButton.isEnabled()) {
                String buttonText = createOrderButton.getText();
                HTPLLogger.info(buttonText + " is enabled && customer approval is done!!");
            } else {
                HTPLLogger.warn(createOrderButton.getText() + " is disabled. customer approval flow not done!!");
            }

        } catch (Exception e) {
            HTPLLogger.error("Error in verifyCustomerApprovalIsDone: " + e.getMessage());
            throw new RuntimeException("Failed to check create order button", e);
        }
    }

    public void createdOrder(){
        try {
            UtilClass.waitForElementAndClick(createOrderButton,2000);
            UtilClass.sleep(2000);
            if (orderNumber.isDisplayed())
            {
                HTPLLogger.info("Created order : "+ orderNumber.getText());
            }
            else {
                HTPLLogger.warn(orderNumber.getText() + " order is not created!!");
            }

        } catch (Exception e) {
            HTPLLogger.error("Error in createdOrder: " + e.getMessage());
            throw new RuntimeException("Failed to create order", e);
        }
    }

    public void checkPaymentPendingButton(){
        try {
            if (pendingForPaymentButton.isEnabled()){
                String buttonText = pendingForPaymentButton.getText();
                HTPLLogger.info(buttonText + " is enabled && customer approval is done!!");
            }else {
                HTPLLogger.warn(pendingForPaymentButton.getText() + " is disabled. Skipping approval flow.");
            }
        } catch (RuntimeException e) {
            HTPLLogger.error("Error in checkPaymentPendingButton: " + e.getMessage());
            throw new RuntimeException("Failed to checkPaymentPendingButton", e);
        }
    }

    public void refreshPage(){
        try {
            driver.navigate().refresh();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

}
