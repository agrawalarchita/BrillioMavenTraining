package Examples;

import org.testng.annotations.*;

public class AnnotationMavenDemo {

	@Test
	public void tc1() {
		System.out.println("TestCase1");
	}

	@Test(priority=1)
	public void tc2() {
		System.out.println("Test Case 2");
	}
	
	@Test(priority=0)
	public void tc3() {
		System.out.println("TestCase3");
	}
	
	@BeforeTest
	public void open() {
		System.out.println("Open");
	}
	
	@AfterTest
	public void close() {
	    System.out.println("close");
	}
	
	@AfterMethod
	public void closeBrowser() {
		
		System.out.println("close Browser");
	}
	
	@BeforeMethod
	public void openBrowser() {
		
		System.out.println("Open Browser");
	}
	
}
