package com.qa.hippo.main.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {

    public static ExtentReports getReportObject(){
        String path=System.getProperty("user.dir")+"//reports//CRMAutomationResult.html";
        ExtentSparkReporter reporter=new ExtentSparkReporter(path);


        reporter.config().setReportName("MO-CRM Automation Result");
        reporter.config().setDocumentTitle("Test Results");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");

        ExtentReports extentReports=new ExtentReports();
        extentReports.attachReporter(reporter);

        // Custom Info
        extentReports.setSystemInfo("Name","Niyaz Ahmad");
        extentReports.setSystemInfo("Designation", "QA Executive");
        extentReports.setSystemInfo("Environment", "Staging");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentReports.setSystemInfo("Browser", "Chrome");
        return extentReports;
    }

}

