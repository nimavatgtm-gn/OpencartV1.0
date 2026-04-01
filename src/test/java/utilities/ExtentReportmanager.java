package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testCases.BaseClass;

public class ExtentReportmanager implements ITestListener{

	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	String repName;
	
	public void onStart(ITestContext testContext) {
	
	/*SimpleDateFormat df=new SimpleDateFormat ("yyyy MM.dd.HH-mm.ss")
	Date dt=new Date):
	String currentdatetimestamp=df.format(dt);
	*/
	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time
	
	repName = "Test-Report-" + timeStamp + ".html";
	// ensure reports directory exists and create reporter with an absolute path
	File reportDir = new File(System.getProperty("user.dir") + File.separator + "reports");
	if (!reportDir.exists()) {
		reportDir.mkdirs();
	}
	File reportFile = new File(reportDir, repName);
	sparkReporter = new ExtentSparkReporter(reportFile.getAbsolutePath()); // specify location of report
	
	sparkReporter.config().setDocumentTitle("opencart Automation Report");//Title of report
	sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report
	sparkReporter.config().setTheme(Theme.DARK);
	
	extent = new ExtentReports();
	extent.attachReporter(sparkReporter);
	extent.setSystemInfo("Application", "opencart");
	extent.setSystemInfo( "Module", "Admin");
	extent.setSystemInfo("Sub Module","Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "QA");
	
	String os = testContext.getCurrentXmlTest().getParameter("os");
	extent.setSystemInfo("Operating System", os) ;
	String browser = testContext.getCurrentXmlTest().getParameter ("browser");
	extent.setSystemInfo("Browser", browser);
	
	List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	if(!includedGroups.isEmpty()) {
	extent.setSystemInfo("Groups", includedGroups.toString());
	}
}	
	public void onTestSuccess (ITestResult result) {
		// use method name for clearer test entries
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}
				
		public void onTestFailure(ITestResult result) 
		{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()) ;
		test.log(Status.FAIL, result.getName()+" got failed");
		if (result.getThrowable() != null) {
			test.log(Status.INFO, result.getThrowable().getMessage()) ;
		}
		try {	
			String imgPath = new BaseClass().captureScreen(result.getName());
			if (imgPath != null && !imgPath.isEmpty()) {
				test.addScreenCaptureFromPath(imgPath);
			}
			
		} 
		
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
		
		
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+" got skipped");
			if (result.getThrowable() != null) {
				test.log(Status.INFO, result.getThrowable().getMessage());
			}
		}
			
		public void onFinish(ITestContext testContext) {
		extent.flush();
		
		String pathofExtentReport = System.getProperty("user.dir") + File.separator + "reports" + File.separator + repName;
		File extentReport = new File(pathofExtentReport) ;
		try {
			if (Desktop.isDesktopSupported()) {
				Desktop.getDesktop().browse(extentReport.toURI());
			}
		} catch (IOException e) {
			e.printStackTrace();} 	
		
		/* >> will need to add java email dependency to user this - commons-email 
		* try { URL url = new
		* URL("file:///"+System.getProperty("user.dir")+"//reports//"+repName);
		*
		* // Create the email message
		ImageHtmlEmail email = new ImageHtmlEmail();
		* email.setDataSourceResolver(new DataSourceUrlResolver(url));
		* email. setHostName ("smtp.googlemai1.com");
		* email.setSmtpPort(465);
		* email.setAuthenticator(new DefaultAuthenticator("pavanoltraining@gmail.com", "password"
		* email.setSSLOnConnect(true);
		* email.setFrom("pavanoltraining@gmail.com"); //Sender
		* email.setSubject("Test Results");
		* email.setMsg("Please find Attached Report....");
		* email.addTo("pavankumar.busyqa@gmail.com"); //Receiver
		* email.attach(url, "extent report", "please check report...");
		* email.send(); // send the email
		* }
		* catch(Exception e) { e.printStackTrace(); }
		*/
	}
}