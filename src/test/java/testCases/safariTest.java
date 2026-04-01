package testCases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class safariTest {

	public static void main(String[] args) {
		WebDriver driver=new SafariDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://tutorialsninja.com/demo/");

	}

}
