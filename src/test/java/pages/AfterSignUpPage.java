package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AfterSignUpPage extends BasePage<AfterSignUpPage> {

	private String endOfURL = "account?new=1";
	
	@FindBy(xpath = "//*[@class='dropdown']/a[contains(@href, '/members/')]")
	protected WebElement userDropDown;
	
	public AfterSignUpPage(WebDriver driver) {
		super(driver);
		
	}
	
	@Override
	public String getPageURL() {
		
		return baseUrl + endOfURL;
	}

	@Override
	protected void checkUniqueElements() throws Error {
		Assert.assertThat(waitAndIsDisplayed(userDropDown), Matchers.is(true));
		
	}

}
