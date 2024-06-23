

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCase {

	WebDriver driver = new ChromeDriver();
	String URL = "https://global.almosafer.com/en";
	String ExpectedLanaguge = "en";
	String ExpectedLanagugear = "ar";
	String ExpectedCurrency = "SAR";
	String ExpectedContactNumber = "+966554400000";
	boolean QitafLogoIsThere = true ; 
	Random rand = new Random();
	
	@BeforeTest
	public void mySetup() {
		
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		
	}
	
	
	@Test(enabled = false)
	public void checkthelangues () {
		
		WebElement htmltag = driver.findElement(By.tagName("html"));
	    String ActualLangagueOnTheWebsite = htmltag.getAttribute("lang");
	    Assert.assertEquals(ActualLangagueOnTheWebsite, ExpectedLanaguge);
		
		
	}
	
	@Test(enabled = false)
	public void TestTheCurrencyIsSAR() {
	String Acctual = driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']")).getText();
	Assert.assertEquals(Acctual, ExpectedCurrency);
	
	
	}
	
	@Test(enabled = false)
	public void TestContactNumber() {
	String Acctualcontactnumber = driver.findElement(By.tagName("strong")).getText();
	Assert.assertEquals(Acctualcontactnumber,ExpectedContactNumber);
	
	
	}
	
	@Test(enabled = false)
	public void CheckQitafLogoIfDisplayed() {
		boolean expected = true;
		WebElement FooterTag = driver.findElement(By.tagName("footer"));
        boolean atual = FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
        Assert.assertEquals(atual, expected);
  
	}
	
	@Test(enabled = false)
	public void CheckHotelTabIsNotSelected() {
		String excepted = "false";
	String acctual = 	driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected");
	Assert.assertEquals(acctual,excepted);
	}
	
	@Test(enabled = false)
	public void CheckDepatureAndReturnDate() {
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
	
	
	@Test(priority = 1)
	public void ChangeTheLanguageOfTheWebSiteRandomly() {
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
	
	@Test(priority = 2)
	public void HotelSelection() {
	WebElement hotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	hotelTab.click();
	
	WebElement serachHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));
	
	if(driver.getCurrentUrl().contains("en")) {
		String [] EnglishCities = {"dubai","jeddah","riyadh"}; 
		int random = rand.nextInt(EnglishCities.length);
		serachHotel.sendKeys(EnglishCities[random]);
		
	}
	else if(driver.getCurrentUrl().contains("ar")) {
		String [] ArbicCities = {"دبي","جدة"}; 
		int random =  rand.nextInt(ArbicCities.length);
		serachHotel.sendKeys(ArbicCities[random]);
	}
	
	}
	
	@Test(priority = 3)
	public void SelectNumberOfPeople() {
		WebElement web = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select myselect = new Select(web);
		int random = rand.nextInt(2);
		myselect.selectByIndex(random);
		WebElement serach = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		serach.click();
	}
	

	@Test(priority = 4)
	public void  CheckThePageIsFullyLoaded() {
         WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
         
         WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
         
         Assert.assertEquals(resultsTab.getText().contains("found") || resultsTab.getText().contains("وجدنا"),true);
 				
         
       
	}	
	
	@Test(priority = 5)
	public void sortItems() throws InterruptedException  {
		WebElement LowestPriceButton = driver.findElement(By.xpath("//button[@data-testid='HotelSearchResult__sort__LOWEST_PRICE']"));
				
		LowestPriceButton.click();
		
		Thread.sleep(2000);
		
		WebElement PricesContainer = driver.findElement(By.cssSelector(".sc-htpNat.KtFsv.col-9"));
		List<WebElement> prices = PricesContainer.findElements(By.className("Price__Value"));

		String firstPRice = prices.get(0).getText();

		String lastPrice = prices.get(prices.size() - 1).getText();

		int firstPriceAsInt = Integer.parseInt(firstPRice);

		int lastPriceAsInt = Integer.parseInt(lastPrice);
		
		Assert.assertEquals(firstPriceAsInt < lastPriceAsInt, true);
	}
	
	
}
