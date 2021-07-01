package Examples;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class DataProviderExcelArray {
	
	WebDriver driver;
	WebDriverWait Wait;
	
	String appURL ="https://www.linkedin.com/";
	
	
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
	public Object[][] loginData(){
		Object[][] arrayObject=getExcelData("./sample_xls_doc.xls","Sheet1");
		return arrayObject;
	}

	public String[][] getExcelData(String filename,String sheetName) {

        String[][] arrayExcelData=null;
       
		try {
			FileInputStream file = new FileInputStream(filename);
	
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sh=wb.getSheet(sheetName);
        
        int totalNoOfCols=sh.getColumns();
        int totalNoOfRows=sh.getRows();
        
        arrayExcelData=new String[totalNoOfRows-1][totalNoOfCols];
        for(int i=1;i<totalNoOfRows;i++)
        {
        	for(int j=0;j<totalNoOfCols;j++) {
        		arrayExcelData[i-1][j]=sh.getCell(j, i).getContents();
        	}
        }
		}
		
		catch ( BiffException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return arrayExcelData;
      
	}

}
