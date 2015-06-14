package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.internal.ElementScrollBehavior;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverController {

	public static final String DOWNLOAD_DIRECTORY = System.getProperty("user.dir");
    public static final int DEFAULT_TIMEOUT_SEC = 30;
    public static final int SHORT_TIMEOUT_SEC = 10;

    @NotNull
	public static WebDriver getWebDriver(@NotNull Browser browser) {
        switch (browser) {
            case CHROME:
                OSUtils.killProcess("chromedriver.exe");
                
                String chromePath = String.format("%s/drivers/chromedriver.exe", System.getProperty("user.dir"));
                System.setProperty("webdriver.chrome.driver", chromePath);

                System.setProperty("webdriver.chrome.logfile", "NUL");
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("download.prompt_for_download", "false");
                prefs.put("download.default_directory", DOWNLOAD_DIRECTORY);
                prefs.put("savefile.default_directory", DOWNLOAD_DIRECTORY);
                prefs.put("safebrowsing.enabled", "true");
                options.setExperimentalOption("prefs", prefs);
                                
                return new ChromeDriver(options);
            case FIREFOX:
            
            	DesiredCapabilities firefoxCaps = DesiredCapabilities.firefox();
                firefoxCaps.setCapability(CapabilityType.PAGE_LOADING_STRATEGY, "conservative");
                firefoxCaps.setCapability(CapabilityType.ELEMENT_SCROLL_BEHAVIOR, ElementScrollBehavior.BOTTOM);
                FirefoxProfile profile = new FirefoxProfile();
                profile.setPreference("browser.download.folderList", 2); // 0 - Desktop, 1 - System default, 2 - custom
                profile.setPreference("browser.download.dir", DOWNLOAD_DIRECTORY);
                profile.setPreference("browser.helperApps.alwaysAsk.force", false);
                profile.setPreference("browser.download.manager.showWhenStarting", false);
                profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-msdos-program"); // *.exe
                FirefoxBinary binary = new FirefoxBinary();
                return new FirefoxDriver(binary, profile, firefoxCaps);
            
            default:
                return new HtmlUnitDriver(true);
        }
    }

    
    @NotNull
    public static WebDriver getDriver(Browser browser) {

        Logger log = LogManager.getLogger(WebDriverController.class);
        log.info("Going to start Browser: " + browser.toString());
        WebDriver driver = getWebDriver(browser);

        driver.manage().timeouts().pageLoadTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS); //for async JavaScript
        driver.manage().window().maximize();
        return driver;
    }
}
