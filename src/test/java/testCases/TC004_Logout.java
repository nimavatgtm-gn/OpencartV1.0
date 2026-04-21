package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.homePage;
import pageObjects.LogoutPage;

public class TC004_Logout extends BaseClass {

	@Test(groups = {"Sanity", "Master"})
	public void verify_logout()
	{
	logger.info("****** Startign TC_004_LogOut *****");
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
	macc.clickLogout();
	
	//logout page
	LogoutPage lp=new LogoutPage(driver);
	lp.clickLogoutcont();
	
	boolean homepagedisp=hp.isHomepageDisp();
	Assert.assertTrue(homepagedisp);
	}
	
	catch(Exception e)
	{
		Assert.fail();
	}
	
	}
}
