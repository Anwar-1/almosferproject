import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class MainPageTestCases extends TestData {

	public void CheckTheLanguageTest() {
		WebElement htmltag = driver.findElement(By.tagName("html"));
	    String ActualLangagueOnTheWebsite = htmltag.getAttribute("lang");
	    Assert.assertEquals(ActualLangagueOnTheWebsite, ExpectedLanaguge);
	}
	
	public void CheckTheCurrencyTest() {
		String Acctual = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']")).getText();
		Assert.assertEquals(Acctual, ExpectedCurrency);
	}
	
	public  void  CheckContactNumberTest() {

		String Acctualcontactnumber = driver.findElement(By.tagName("strong")).getText();
		Assert.assertEquals(Acctualcontactnumber,ExpectedContactNumber);
	}
	
	public void CheckQitafLogoTest() {
		boolean expected = true;
		WebElement FooterTag = driver.findElement(By.tagName("footer"));
        boolean atual = FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
        Assert.assertEquals(atual, expected);
	}
	 
	public void CheckHotelTabIsNotSelectedTest() {

		String excepted = "false";
		String acctual = 	driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
		Assert.assertEquals(acctual,excepted);
	}
	
	public void CheckDepartureAndReturnDateTest() {

		LocalDate today = LocalDate.now();
	    int ExpectedDepatureDate=  today.plusDays(1).getDayOfMonth();
	    int ExpectedReturneDate = today.plusDays(2).getDayOfMonth();
	
	 String   atualDepatureDate = driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText();
	 String   atualReturneDate  = driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText();
	 
	 int atualDepatureDateasint = Integer.parseInt(atualDepatureDate);
	 int atualReturneDateasint = Integer.parseInt(atualReturneDate);
	 
	 Assert.assertEquals(atualDepatureDateasint,ExpectedDepatureDate);
	 Assert.assertEquals(atualReturneDateasint,ExpectedReturneDate);
	 
	}
	
	public void CheckTheLanguageHasChangedRandomlyTest() {
		String [] websites = { "https://global.almosafer.com/en", "https://global.almosafer.com/ar" };
		int random = rand.nextInt(websites.length);
		driver.get(websites[random]);
		
		if(driver.getCurrentUrl().contains("en")) {
			WebElement htmltag = driver.findElement(By.tagName("html"));
		    String ActualLangagueOnTheWebsite = htmltag.getAttribute("lang");
		    Assert.assertEquals(ActualLangagueOnTheWebsite, ExpectedLanaguge);
		}
		else if(driver.getCurrentUrl().contains("ar")) 
		{
			WebElement htmltag = driver.findElement(By.tagName("html"));
		    String ActualLangagueOnTheWebsite = htmltag.getAttribute("lang");
		    Assert.assertEquals(ActualLangagueOnTheWebsite, ExpectedLanagugear);
		}
	}
	
}

