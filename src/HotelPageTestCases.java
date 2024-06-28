import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HotelPageTestCases extends TestData {

	 public void HotelSelectionTest() {
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
	 
	 public void HotelNumberOfVisitorTest() {
		 WebElement web = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
			Select myselect = new Select(web);
			int random = rand.nextInt(2);
			myselect.selectByIndex(random);
			WebElement serach = driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
			serach.click();
	 }
	 
	 public void HotelPageIsFullyLoadedTest() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
         
         WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='HotelSearchResult__resultsFoundCount']")));
         
         Assert.assertEquals(resultsTab.getText().contains("found") || resultsTab.getText().contains("وجدنا"),true);
	 }
	 
	 public void HotelSortingTest() throws InterruptedException {
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
