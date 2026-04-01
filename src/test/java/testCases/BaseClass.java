package testCases;

import org.apache.logging.log4j.LogManager; // to generate log file and log message in console and file
import org.apache.logging.log4j.Logger; // to generate log file and log message in console and file

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.text.RandomStringGenerator; // to generate random string and number
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

public class BaseClass {

public static WebDriver driver;
public Logger logger; 
public Properties p;

	@BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven" })
	@Parameters({"browser", "os"})
	public void setup(String br, String os) throws InterruptedException, IOException
	{
		
		//Loading config-properties file
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p=new Properties ();
		p.load(file);
		logger=LogManager.getLogger(this.getClass());
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		DesiredCapabilities capabilities=new DesiredCapabilities();
		
		//os
		if(os.equalsIgnoreCase("windows"))
		{capabilities.setPlatform(Platform.WIN11) ;}
		else if (os.equalsIgnoreCase("mac"))
		{
		capabilities.setPlatform(Platform.MAC);		
		}
		else if(os.equalsIgnoreCase("linux"))
		{capabilities.setPlatform(Platform.LINUX);
		}
		else
		{
		System.out.println("No matching os");
		return;
		}
		
		//browser
		switch(br.toLowerCase())
		{
		case "chrome": capabilities.setBrowserName("chrome"); break;
		case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
		case "firefox": capabilities.setBrowserName("firefox"); break;
		default: System.out.println("No matching browser"); return;
		}
		driver=new RemoteWebDriver (new URL("http://10.75.162.160:4444"), capabilities);
		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		switch(br.toLowerCase())
				{
		case "chrome" : driver=new ChromeDriver(); break;
		case "edge" : driver=new EdgeDriver(); break;
		case "firefox": driver=new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name.."); return;
				}
		}
		
		
	//driver.manage().deleteAllCookies();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	//driver.get("https://tutorialsninja.com/demo/");
	driver.get(p.getProperty("appURL"));
	driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity", "Regression", "Master", "DataDriven" })
	public void tearDown()
	{
	driver.quit();
	}
	
	public String captureScreen(String tname) throws IOException {
		
	
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format (new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"//screenshots//" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	public String randomeString()
	{
	 RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').get();
	 String numeric = generator.generate(5);
	 return numeric;
	}
	
	
	public String randomeNumber()
	{RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', '9').get();
	 String numeric1 = generator.generate(10);
	 return numeric1;}
	
	public String randomeAlphaNumberic()
	{
		RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('0', 'z').get();
		 String numeric2 = generator.generate(10);
		 return numeric2;}
	
}
