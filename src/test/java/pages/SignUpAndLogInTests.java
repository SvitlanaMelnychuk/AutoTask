package pages;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses(
        {   
            SignUpTest.class,
            LogInTest.class
        })

public class SignUpAndLogInTests {

}
