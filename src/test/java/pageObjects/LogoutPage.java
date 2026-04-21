package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage  extends BasePage {

	public LogoutPage(WebDriver driver) {
		super (driver);
		}
	
	@FindBy(xpath = "//div[@class=\"pull-right\"]/a")
	WebElement logoutcont;
	
	public void clickLogoutcont()
	{logoutcont.click();
	}
}
