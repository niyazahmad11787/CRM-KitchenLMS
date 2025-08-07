package com.qa.hippo.main.utilities.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import com.qa.hippo.main.utilities.ExtentReporterNG;
import com.qa.hippo.main.utilities.HTPLLogger;
import org.testng.*;

public class Listeners implements ITestListener, ISuiteListener {

    ExtentReports extentReports = ExtentReporterNG.getReportObject();
    // Use ThreadLocal for safe parallel execution
    public static ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getMethod().getMethodName());
        extentTestThread.set(test);
        HTPLLogger.setLogger(result.getTestClass().getRealClass(), test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extentTestThread.get().log(Status.PASS, "Test Passed!!");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extentTestThread.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTestThread.get().log(Status.SKIP, "Test Skipped!!");
    }

    // ❌ DO NOT use this for report opening — it runs after each <test> block
    @Override
    public void onFinish(ITestContext context) {
        extentReports.flush(); // Still flush after each test context (safe to keep)
    }

    // ✅ Use this one to open report once after entire suite
//    @Override
//    public void onFinish(ISuite suite) {
//        try {
//            File htmlFile = new File(System.getProperty("user.dir") + "/reports/POSAutomationReport.html");
//
//            if (htmlFile.exists()) {
//                if (Desktop.isDesktopSupported() && !GraphicsEnvironment.isHeadless()) {
//                    Desktop.getDesktop().browse(htmlFile.toURI());
//                    System.out.println("✅ Opened report after suite: " + htmlFile.getAbsolutePath());
//                } else {
//                    System.out.println("⚠️ Desktop is not supported or environment is headless. Skipping auto-open of report.");
//                }
//            } else {
//                System.out.println("❌ Report file not found: " + htmlFile.getAbsolutePath());
//            }
//
//        } catch (IOException e) {
//            System.out.println("⚠️ Failed to open report. Reason: " + e.getMessage());
//            // Optional: log stacktrace if needed
//            e.printStackTrace();
//        }
//    }
}

