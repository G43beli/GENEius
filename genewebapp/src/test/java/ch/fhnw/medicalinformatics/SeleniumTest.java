package ch.fhnw.medicalinformatics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SeleniumTest {

	@Test
	public void webTest() {
		
		if (!TestSettings.EXECUTE_WEBTESTS) return;
		
		System.setProperty("webdriver.chrome.driver", "/Users/davidherzig/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();

		driver.get(TestSettings.URL);
				
		WebElement soDropdown = driver.findElement(By.id("inputform:searchoption_input"));
		WebElement stInput = driver.findElement(By.id("inputform:searchterm"));
		WebElement retrieveButton = driver.findElement(By.id("inputform:retrieveButton"));
		
		Select searchOption = new Select(soDropdown);
		searchOption.selectByValue("Search by ID");

		stInput.clear();
		stInput.sendKeys("70");
		retrieveButton.click();

		// wait until browser closes
		try {
			Thread.sleep(5000);
		} catch (Exception ex) {

		}

		driver.close();
		driver.quit();
	}
	
}