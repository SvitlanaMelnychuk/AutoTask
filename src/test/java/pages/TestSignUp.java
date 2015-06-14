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

public class TestSignUp {

	private WebDriver driver;
	private SignUpPage signUp;
	private DataProvider data = new DataProvider();
			
	private final Logger log = LogManager.getLogger(TestSignUp.class);
		
	@Before
	public void setUp() {
		
		PropertyConfigurator.configure("log4j.properties");
		driver = WebDriverController.getDriver(Browser.CHROME);
		
	}
			
	@Test
	public void testSignUpLink() {
		
		log.info("Test SignUp Link ");
		new MainPage(driver).get().goToSignUpPage().isLoaded();
		log.info("SignUp link is valid ");
	}
			
	@Test
	public void testEmptyFields() {
		
		log.info("Test SignUp Page in case when all fields are empty ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getEmptyText(), data.getEmptyText(), data.getEmptyText(), data.getEmptyText(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: Register before submission."));
		log.info("'Error: Register before submission.' message is appeared ");
	}

	@Test
	public void testEmptyPassword() {
		
		log.info("Test SignUp Page in case when password field is empty ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getTempUsName(), data.getEmptyText(), data.getEmptyText(), data.getEmptyText(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: Password not entered."));
		log.info("'Error: Password not entered.' message is appeared ");
	}
	
	@Test
	public void testEmptyConfPass() {
		
		log.info("Test SignUp Page in case when confirm password field is empty ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getTempUsName(), data.getPassword(), data.getEmptyText(), data.getEmptyText(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: Confirm password not entered."));
		log.info("'Error: Confirm password not entered.' message is appeared ");
	}
	
	@Test
	public void testNotMatchedConfPass() {
		
		log.info("Test SignUp Page in case when not existed password is entered to confirm password field ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getTempUsName(), data.getPassword(), data.getNotExistedPassword(), data.getEmptyText(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: The password you entered does not match the confirm password."));
		log.info("'Error: The password you entered does not match the confirm password.' message is appeared ");
	}
	
	@Test
	public void testInvalidEmail() {
		
		log.info("Test SignUp Page in case when not valid email is entered to email field ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getTempUsName(), data.getPassword(), data.getPassword(), data.getNotValidEmail(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: Your e-mail address is invalid."));
		log.info("'Error: Your e-mail address is invalid.' message is appeared ");
	}
	
	@Test
	public void testEmptyCaptcha() {
		
		log.info("Test SignUp Page in case when captcha field is empty ");
		signUp = new SignUpPage(driver).get(); 
		signUp.signUp(data.getTempUsName(), data.getPassword(), data.getPassword(), data.getEmail(), data.getDefaultSelect(), data.getEmptyText());
		Assert.assertTrue("Error message doesn't appear", signUp.getResultText().equals("Error: Image code wrong, please try again."));
		log.info("'Error: Image code wrong, please try again.' message is appeared ");
	}
	
	@After
	public void quit() {
		
		driver.close();
	}
	
}
