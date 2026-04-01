package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import pageObjects.homePage;
import utilities.DataProviders;

public class TC03_LoginDDT extends BaseClass {

	/*Data is valid - login success - test pass - logout
	Data is valid -- login failed - test fail
	Data is invalid - login success - test fail - logout
	Data is invalid -- login failed - test pass
	| */
	
	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "DataDriven") // getting data provider from DataProviders class and using LoginData as data provider name
	public void verify_loginDDT(String email, String password, String exp) //taking two parameters as we have two columns in excel sheet
	{
		
		logger.info("****** Startign TC_003_LoginDDT *****");
		try {
		
		//HomePage
		homePage hp=new homePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		//Login
		LoginPage pg=new LoginPage(driver);
		pg.setEmail(email);
		pg.setPassword(password);
		pg.clickLogin();
		
		//MyAccount
		MyAccountPage macc=new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		if(exp.equals("Valid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(true); // Data is valid - login success - test pass - logout
			}
			else
			{
				Assert.assertTrue(false); // Data is valid -- login failed - test fail
			}
		}
		else if(exp.equals("Invalid"))
		{
			if(targetPage==true)
			{
				macc.clickLogout();
				Assert.assertTrue(false); // Data is invalid - login success - test fail - logout
				// So if the data in invalid then user should not be able to login - that is expected
				// But if user is able to login with invalid data then that is failure
			}
			else
			{
				Assert.assertTrue(true); // Data is invalid -- login failed - test pass
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished TC_003_LoginDDT *****");
	}
}
