package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataProvider {

	private String stableUsName;
	private String password;
	private String tempUsName;
	private String email;
	private String notValidEmail;
	private String specChar;
	private String startsFromSpace;
	private String emptyText;
	private String longText;
	private String localizationText;
	private String notExistedPassword;
	private String defaultSelect;
	private String femaleSelect;
	private String fileName = "data.properties";
			
	public DataProvider(){
		
		Properties prop = new Properties();
		InputStream input = null;
		
		
		try {
			input = new FileInputStream(fileName);
						
			prop.load(input);
			
			initData(prop);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			closeInputStream(input);
		}
	}
	
	private void initData(Properties prop) {
		
		this.stableUsName = prop.getProperty("stableUsName");
		this.password = prop.getProperty("password");
		this.tempUsName = prop.getProperty("tempUsName");
		this.email = prop.getProperty("email");
		this.notValidEmail = prop.getProperty("notValidEmail");
		this.specChar = prop.getProperty("specChar");
		this.startsFromSpace = prop.getProperty("startsFromSpace");
		this.emptyText = prop.getProperty("emptyText");
		this.longText = prop.getProperty("longText");
		this.localizationText = prop.getProperty("localizationText");
		this.notExistedPassword = prop.getProperty("notExistedPassword");
		this.defaultSelect = prop.getProperty("defaultSelect");
		this.femaleSelect = prop.getProperty("femaleSelect");
	}
	
	private void closeInputStream(InputStream input) {
		
		if (input != null) {
	    try {
	     input.close();
	    } catch (IOException e) {
	     e.printStackTrace(); 
	    }
	   }
	}
	
	public String getStableUsName() {
		return stableUsName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getTempUsName() {
		return tempUsName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getNotValidEmail() {
		return notValidEmail;
	}
	
	public String getSpecChar() {
		return specChar;
	}
	
	public String getStartsFromSpace() {
		return startsFromSpace;
	}
	
	public String getEmptyText() {
		return emptyText;
	}
	
	public String getLongText() {
		return longText;
	}
	
	public String getLocalizationText() {
		return localizationText;
	}
	
	public String getNotExistedPassword() {
		return notExistedPassword;
	}
	
	public String getDefaultSelect() {
		return defaultSelect;
	}
	
	public String getFemaleSelect() {
		return femaleSelect;
	}
	
}
