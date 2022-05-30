package softway.sw;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FlipKartDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		WebElement clickOnSearchBox = driver.findElement(By.name("q"));
		clickOnSearchBox.click();
		clickOnSearchBox.sendKeys("iphone");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[text()='APPLE iPhone 11 (White, 64 GB)']")).click();
		Thread.sleep(2000);
		 //Get handles of the windows
        //String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String string : allWindowHandles) {
			driver.switchTo().window(string);
			
		}
        String price = driver.findElement(By.xpath("(//div[contains(.,'₹')])[12]")).getText();
		System.out.println(price);
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("//span[text()='Cart']")).click();	
		//Thread.sleep(2000);
		String ExpectedTitle = "Shopping Cart | Flipkart.com";
		String ActualTitle = driver.getTitle();
		String WindowHandle = driver.getWindowHandle();

		Thread.sleep(2000);

				if(ActualTitle.equals(ExpectedTitle)) {
					driver.switchTo().window(WindowHandle);
				}


		//WebElement Element = driver.findElement(By.xpath("//button[text()='+']"));
		
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("arguments[0].scrollIntoView();", Element);
		js.executeScript("window.scrollBy(0,2000)");
		driver.findElement(By.xpath("//button[text()='+']")).click();
		Thread.sleep(3000);
		String msg = driver.findElement(By.xpath("(//div[text()='Total Amount']/../..//span)[1]")).getText();
		System.out.println(msg);

		 driver.quit();
		 

		
		

	}

}
