package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homePage extends BasePage {

	WebDriver driver;
	public homePage (WebDriver driver)
	{super (driver);}

	@FindBy(xpath="//span[normalize-space()= 'My Account' ]")
	WebElement InkMyaccount;
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement InkRegister;
	@FindBy(linkText = "Login")
	WebElement linkLogin;
	@FindBy(xpath = "//div[@class='swiper-pagination slideshow0 swiper-pagination-clickable swiper-pagination-bullets']//span[@class='swiper-pagination-bullet swiper-pagination-bullet-active']")
	WebElement blt;
	
	public void clickMyAccount()
	{
	InkMyaccount.click();
	}
	
	public void clickRegister()
	{InkRegister.click();}
	
	public void clickLogin()
	{linkLogin.click();}
	
	public boolean isHomepageDisp()
	{
		boolean disp=blt.isDisplayed();
		return disp;
	}
}
