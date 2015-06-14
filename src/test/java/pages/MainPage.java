package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage<MainPage> {
	
		
	@FindBy(xpath = "//*[text()='Recent Submissions']")
	protected WebElement recentSubmis;
			
	public MainPage(WebDriver driver) {
		super(driver);
		        		
	}

	@Override
	public String getPageURL() {
		return String.format("%s",baseUrl);
	}

	@Override
	protected void checkUniqueElements() throws Error {
		Assert.assertThat(waitAndIsDisplayed(recentSubmis), Matchers.is(true));
		
	}
}
