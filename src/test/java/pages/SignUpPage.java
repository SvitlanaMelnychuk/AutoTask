package pages;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class SignUpPage extends BasePage<SignUpPage> {
	
	@FindBy(xpath = "//*[@name='username']")
	private WebElement userName;

	@FindBy(xpath = "//*[@name='password']")
	private WebElement password;

	@FindBy(xpath = "//*[@name='confirmpassword']")
	private WebElement confPassw;

	@FindBy(xpath = "//*[@name='email']")
	private WebElement email;
	
	@FindBy(xpath = "//select[@name='gender']")
	private WebElement genderSelect;
	
	@FindBy(xpath = "//*[@name='captcha']")
	public WebElement captchaInput;

	@FindBy(xpath = "//*[@class='ok']/input")
	public WebElement createButton;
	
	@FindBy(xpath = "//*[@id='resultsignup']/p")
	public WebElement resultMsg;
	
	private String endOfURL = "signup";
	
	public SignUpPage(WebDriver driver) {
		super(driver);
				
	}
	
	public void setUserName(String userNameValue) {
		waitVisibleAndSend(userName, userNameValue);
	}
	
	public void setPassword(String passwordValue) {
		waitVisibleAndSend(password, passwordValue);
	}
	
	public void setConfPassw(String confPasswordValue) {
		waitVisibleAndSend(confPassw, confPasswordValue);
	}
	
	public void setEmail(String EmailValue) {
		waitVisibleAndSend(email, EmailValue);
	}
	
	public void selectGender(String gender) {
		selectByText(genderSelect, gender);
	}
	
	public void setCaptcha(String captcha) {
		waitVisibleAndSend(captchaInput, captcha);
	}
	
	public void submitSignUp() {
		
		waitClickableAndClick(createButton);
	}
	
	public AfterSignUpPage signUp(String userNameValue, String passwordValue, String confPasswordValue, String EmailValue, String gender, String captcha) {
		setUserName(userNameValue);
		setPassword(passwordValue);
		setConfPassw(confPasswordValue);
		setEmail(EmailValue);
		selectGender(gender);
		setCaptcha(captcha);
		submitSignUp();
		return new AfterSignUpPage(driver);
	}

	public String getResultText() {
		waitVisibleElement(resultMsg);
		return resultMsg.getText();
	}

	@Override
	public String getPageURL() {
		
		return baseUrl + endOfURL;
	}

	@Override
	protected void checkUniqueElements() throws NoSuchElementException {
        Assert.assertThat(waitAndIsDisplayed(createButton), Matchers.is(true));
    }
}
