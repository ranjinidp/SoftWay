package softway.sw;

import java.time.Duration;
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
		//Open https://www.flipkart.com/
		driver.get("https://www.flipkart.com/");
		
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.findElement(By.xpath("//button[text()='✕']")).click();
		
		//Search for the product you like(Any phones)
		WebElement clickOnSearchBox = driver.findElement(By.name("q"));
		clickOnSearchBox.click();
		clickOnSearchBox.sendKeys("iphone");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		//Click on the first item from the list
		driver.findElement(By.xpath("//div[text()='APPLE iPhone 11 (White, 64 GB)']")).click();
		//Thread.sleep(2000);
		 //Get handles of the windows
        //String mainWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String string : allWindowHandles) {
			driver.switchTo().window(string);
			
		}
        String price = driver.findElement(By.xpath("(//div[contains(.,'₹')])[12]")).getText();
        
        //Print the price of the item
		System.out.println(price);
		
		//Add to cart in guest mode
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();

		String ExpectedTitle = "Shopping Cart | Flipkart.com";
		String ActualTitle = driver.getTitle();
		String WindowHandle = driver.getWindowHandle();

		Thread.sleep(2000);

				if(ActualTitle.equals(ExpectedTitle)) {
					//Go to the cart page
					driver.switchTo().window(WindowHandle);
				}

		WebElement Element = driver.findElement(By.xpath("//button[text()='+']"));		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", Element);
		//js.executeScript("window.scrollBy(0,2000)");
		
		//Increase the quantity by 1
		driver.findElement(By.xpath("//button[text()='+']")).click();
		Thread.sleep(3000);
		String msg = driver.findElement(By.xpath("(//div[text()='Total Amount']/../..//span)[1]")).getText();
		
		//Print the price after addition of quantity
		System.out.println(msg);

		driver.quit();
		 

		
		

	}

}
