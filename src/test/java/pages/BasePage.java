package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.hamcrest.Matchers;

public abstract class BasePage<T extends BasePage<T>> extends LoadableComponent<T> {
	
	protected WebDriver driver;
	protected static final int DEFAULT_TIMEOUT_SEC = 120;
	
	protected String baseUrl = "http://www.openforbeta.com/";
	protected final Logger log = LogManager.getLogger(BasePage.class);
			
	@FindBy(xpath = "//a[text()='Signup']")
	protected WebElement signUpLink;
	
	@FindBy(xpath = "//a[text()='Login']")
	protected WebElement loginLink;
	
	@FindBy(xpath = "//*[@id='login-form']")
	protected WebElement loginForm;
	
	@FindBy(xpath = "//*[@name='lusername']")
	protected WebElement username;
	
	@FindBy(xpath = "//*[@name='lpassword']")
	protected WebElement password;
	
	@FindBy(xpath = "//em[text()='Login']")
	protected WebElement loginButton;
				
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
        PageFactory.initElements(driver, this);
		
	}
	
	@Override
    protected void load() {
        log.info("Loading page: " + getPageURL());
        driver.get(getPageURL());
                
    }

    @Override
    protected void isLoaded() throws NoSuchElementException {
    	    		
            Assert.assertThat("Wrong page URL", driver.getCurrentUrl(), Matchers.equalToIgnoringCase(getPageURL()));
            checkUniqueElements();
     }
	
	public abstract String getPageURL();
	
	protected abstract void checkUniqueElements() throws Error;
		
	public WebElement waitVisibleElement(WebElement element) 
	{
		return new WebDriverWait(driver, DEFAULT_TIMEOUT_SEC)
	            .until(ExpectedConditions.visibilityOf(element));
	}


	public void waitClickable(WebElement element) 
	{
		new WebDriverWait(driver, DEFAULT_TIMEOUT_SEC)
	            .until(ExpectedConditions.elementToBeClickable(element));
	}
			
	public void waitClickableAndClick(WebElement element)
	{
    	waitClickable(element);
    	element.click();
	}
	
	public void waitVisibleAndSend(WebElement element, String pswValue) 
	{
		waitVisibleElement(element);
		element.sendKeys(pswValue);
	}
	
	public boolean waitAndIsDisplayed(WebElement element) 
	{
		waitVisibleElement(element);
		return element.isDisplayed();
	}
	
	public String getText(WebElement element) 
	{
		waitVisibleElement(element);
		return element.getText();
	}
	
	public void selectByText(WebElement element, String item)
	{
    	Select select = new Select(element);
    	select.selectByVisibleText(item);
	}
	
	public SignUpPage goToSignUpPage() 
	{
		waitClickableAndClick(signUpLink);
		return new SignUpPage(driver);
	}
	
	public MainPage goToLoginForm() 
	{
		waitClickableAndClick(loginLink);
		Assert.assertTrue("Login form isn't opened", waitAndIsDisplayed(loginForm));
		return new MainPage(driver);
	}
	
	public void setUser(String usName) {
		waitVisibleAndSend(username, usName);
	}
	
	public void setPassword(String pswValue) {
		waitVisibleAndSend(password, pswValue);
	}
	
	public void submitLogIn() {
		
		waitClickableAndClick(loginButton);
	}
	
	public AfterLoginPage login(String usName, String pswValue) {
		
		setUser(usName);
		setPassword(pswValue);
		submitLogIn();
		return new AfterLoginPage(driver);
	}
	
}
