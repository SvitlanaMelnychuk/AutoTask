package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginErrorPage extends BasePage<LoginErrorPage> {

	private String endOfURL = "login";
	
	public LoginErrorPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//p[@class='pasgood']") 
	private WebElement errorMessage;


	
	@Override
	public String getPageURL() {
		
		return baseUrl + endOfURL;
	}

	@Override
    protected void checkUniqueElements() throws NoSuchElementException {
        Assert.assertThat(waitAndIsDisplayed(errorMessage), Matchers.is(true));
    }
	
	public String getErrorText() {
		waitVisibleElement(errorMessage);
		return errorMessage.getText();
	}


}
