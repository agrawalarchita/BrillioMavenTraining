package Examples;

import org.testng.Assert;
import org.testng.annotations.*;

public class RetryTest {
	
	/*
	@Test(retryAnalyzer = Examples.IRetryAnalyzerDemo.class)
	public void test11() {
		Assert.assertEquals(false, true);
	}
    */
	
	@Test
	public void test1() {
		Assert.assertEquals(false, true);
	}

	@Test
	public void test2() {
		Assert.assertEquals(false, true);
	}


}
