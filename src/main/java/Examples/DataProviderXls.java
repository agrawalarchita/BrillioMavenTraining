package Examples;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderXls {

	WebDriver driver;
	WebDriverWait Wait;
	
	String appURL ="https://www.linkedin.com/login";	
	
	private By byEmail= By.id("username");
	private By byPassword= By.id("password");
	private By bySignIn=By.xpath("//button[@type='submit']");
	private By byError=By.id("error-for-username");
	
	@BeforeClass
	public void testSetup() {
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();		
		Wait = new WebDriverWait(driver,10);
	}
	
	@Test(dataProvider="inputdata")
	public void verifyInvalidLogin(String userName, String password) {
		
		driver.get(appURL);
		
	   driver.findElement(byEmail).sendKeys(userName);
        driver.findElement(byPassword).sendKeys(password);
        Wait.until(ExpectedConditions.visibilityOfElementLocated(bySignIn));
        driver.findElement(bySignIn).click();
        
        Wait.until(ExpectedConditions.presenceOfElementLocated(byError));
       String actualErrorMessage=driver.findElement(byError).getText();
       String requiredErrorMessage="Please enter a valid username";
       Assert.assertEquals(actualErrorMessage, requiredErrorMessage);
       
	}
	
	@DataProvider(name="inputdata")
	public Object [][] getCellData() throws IOException {
		
		// locate excel file
		FileInputStream file = new FileInputStream("./sample_xls_doc.xls");
		//create workbook instance
		HSSFWorkbook wb=new HSSFWorkbook(file);
		//go to desired sheet
		HSSFSheet s=wb.getSheet("sheet1");
		int rowcount = s.getLastRowNum()+1;
		int cellcount=s.getRow(0).getLastCellNum();
		
		Object data[][] = new Object[rowcount][cellcount];
		
		for(int i=0;i<rowcount;i++) {
			Row r = s.getRow(i);
			 
		    for(int j=0;j<cellcount;j++) {
		    	Cell c=r.getCell(j);
		    	data[i][j]=c.getStringCellValue();
		    }
		}
		wb.close();
		return data;
	}
	
}
