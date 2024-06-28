import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class TestData {

	
	//WebDriver driver = new ChromeDriver();
	 protected static WebDriver driver;
	String URL = "https://global.almosafer.com/en";
	String ExpectedLanaguge = "en";
	String ExpectedLanagugear = "ar";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean QitafLogoIsThere = true ; 
	Random rand = new Random();
	

    @BeforeSuite
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
	
	
	
	
	 public void TheDefaultConfiguration() {
		    driver.get(URL);
			driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
	    }
	
	
	
}
