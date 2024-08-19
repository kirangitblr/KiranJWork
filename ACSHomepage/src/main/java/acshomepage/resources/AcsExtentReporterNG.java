package acshomepage.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class AcsExtentReporterNG {
	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"//acsreports//index.html";
		ExtentSparkReporter report=new ExtentSparkReporter(path); 
		report.config().setReportName("ACS Home Page Web Automation");
		report.config().setDocumentTitle("ACS Test Results");
		ExtentReports extent=new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester", "Kiran J");
		return extent;
		
	}

}
