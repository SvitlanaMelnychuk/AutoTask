package pages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import utils.Browser;
import utils.DataProvider;
import utils.WebDriverController;

public class TestLogIn {
	private WebDriver driver;
	private final Logger log = LogManager.getLogger(TestLogIn.class);
	private DataProvider data = new DataProvider();
		
	@Before
	public void setUp() {
		
		PropertyConfigurator.configure("log4j.properties");
		driver = WebDriverController.getDriver(Browser.FIREFOX);
			
	}
	
	@Test
	public void testLoginLink() {
		
		log.info("Test Login Link ");
		new MainPage(driver).get().goToLoginForm();
		log.info("Login link is valid ");
	}
	
	@Test
	public void testEmptyFields() {
		
		log.info("Test Login form in case when all fields are empty ");
		new MainPage(driver).get().goToLoginForm().login(data.getEmptyText(), data.getEmptyText());
		new LoginErrorPage(driver).isLoaded();
		Assert.assertTrue("Error message doesn't appear", new LoginErrorPage(driver).getErrorText().equals("Error: Register before submission."));
		log.info("loginError Page is loaded after entering empty text to all fields ");
	}
	
	@Test
	public void testEmptyPassw() {
		
		log.info("Test Login form in case when password field is empty ");
		new MainPage(driver).get().goToLoginForm().login(data.getStableUsName(), data.getEmptyText());
		new LoginErrorPage(driver).isLoaded();
		Assert.assertTrue("Error message doesn't appear", new LoginErrorPage(driver).getErrorText().equals("Error: Password not entered."));
		log.info("loginError Page is loaded after entering empty text to password field ");
	}
	
	@Test
	public void testLoginNotExistedPassw() {
		
		log.info("Test Login form in case when not existed password is entered to password field ");
		new MainPage(driver).get().goToLoginForm().login(data.getStableUsName(), data.getNotExistedPassword());
		new LoginErrorPage(driver).isLoaded();
		Assert.assertTrue("Error message doesn't appear", new LoginErrorPage(driver).getErrorText().equals("Error: Wrong username/password combination."));
		log.info("loginError Page is loaded after entering not existed password ");
	}
	
	@Test
	public void testPositiveLogin() {
		
		log.info("Test Positive Login");
		new MainPage(driver).get().goToLoginForm().login(data.getStableUsName(), data.getPassword()).isLoaded();
		log.info("You are loged successfully");
	}
	
	@After
	public void quit() {
		
		driver.close();
	}

}
