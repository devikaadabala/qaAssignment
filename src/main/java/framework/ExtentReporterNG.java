package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class ExtentReporterNG {
    static ExtentReports extent;

    public static ExtentReports extentReportsPath() {
        String reportPath = System.getProperty("user.dir") + "//reports//testReport.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Extent Report Automation");
        reporter.config().setDocumentTitle("Test cases Reports");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("QA Engineer","Devika");
        return extent;
    }
}
