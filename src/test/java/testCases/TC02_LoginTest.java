package testCases;

import org.apache.commons.math3.util.Incrementor.MaxCountExceededCallback;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.homePage;

public class TC02_LoginTest extends BaseClass {
	
	@Test(groups = {"Sanity", "Master"})
	public void verify_login()
	{
	logger.info("****** Startign TC_002_LoginTest *****");
	try
	{
	
		//HomePage
	homePage hp=new homePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();
	
	//Login
	LoginPage pg=new LoginPage(driver);
	pg.setEmail(p.getProperty("email"));
	pg.setPassword(p.getProperty("password"));
	pg.clickLogin();
	
	//MyAccount
	MyAccountPage macc=new MyAccountPage(driver);
	boolean targetPage=macc.isMyAccountPageExists();
	Assert.assertTrue(targetPage);//Assert.assertEquals(targetPage, true, "Login failed");
	
	}
	catch(Exception e)
	{
		Assert.fail();
	}
}
}