package testCases;

import org.testng.annotations.Test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.homePage;

public class TC01_AccountRegistration extends BaseClass {

	
	@Test(groups = {"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("Starting TC001 AccountRegistrationTest");	
		try {
	homePage hp=new homePage(driver);
	hp.clickMyAccount();
	logger.info("Clicked on My Account Link.. ");
	hp.clickRegister();
	logger.info("Providing Register Link...");
	
	AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
	//Thread.sleep(3000);put for safari as it is not working without wait
	logger.info("Providing customer details...");
	regpage.setFirstName(randomeString().toUpperCase());
	regpage.setLastName(randomeString().toUpperCase());
	regpage.setEmail (randomeString()+"@gmail.com");// rando
	regpage.setTelephone (randomeNumber());
	
	String password=randomeAlphaNumberic();
	regpage.setPassword (password);
	regpage.setConfirmPassword(password);
	
	regpage.setPrivacyPolicy();
	regpage.clickContinue();
	//Thread.sleep(3000); put for safari as it is not working without wait
	logger.info("Validating expected message...");
	String confmsg=regpage.getConfirmationMsg();
	if(confmsg.equals("Your Account Has Been Created!"))
	{
	Assert.assertTrue(true);
	}
	else
	{logger.error ("Test failed..");
	logger.debug("Debug logs..");
	Assert.assertTrue(false);
	}
		}
	//Assert.assertEquals(confmsg,"Your Account Has Been Created!!!");
	catch(Exception e)
	{
	logger.error ("Test failed..");
	logger.debug("Debug logs..");
	Assert.fail();
	}

	
		logger.info("Finished TC001 AccountRegistrationTest");
	}	
	

}
