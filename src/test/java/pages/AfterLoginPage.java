package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AfterLoginPage extends BasePage<AfterLoginPage> {

	
	private String endOfURL = "account";
	
	@FindBy(xpath = "//*[@class='dropdown']/a[contains(@href, '/members/')]")
	protected WebElement userDropDown;
	
	public AfterLoginPage(WebDriver driver) {
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
