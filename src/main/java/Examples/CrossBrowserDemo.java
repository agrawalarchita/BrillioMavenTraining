package Examples;

import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CrossBrowserDemo {	

		WebDriver driver;
		
		@BeforeTest
		@Parameters("browser")
	     public void setup(String browser) throws Exception {
			if(browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
				
			}
			
			else if(browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
				
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			
			else {
				throw new Exception("Browser is not correct");
			}
			
		}
		

		@Test
		public void demo1() throws InterruptedException {
	        driver.get("https://petstore.octoperf.com/actions/Account.action");	        
	        
			driver.findElement(By.name("username")).sendKeys("j2ee");
			driver.findElement(By.name("password")).clear();
			Thread.sleep(10000);
			driver.findElement(By.name("password")).sendKeys("j2ee");
			driver.findElement(By.name("signon")).click();
			Thread.sleep(10000);
		}

	
}
