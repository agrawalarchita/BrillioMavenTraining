package Examples;
import org.testng.annotations.*;

public class expException {

	@Test(expectedExceptions=ArithmeticException.class)
	public void divideByZero() {
		int i = 1/0;		
		System.out.println(i);
	}

/*	@Test
	public void divideByZero2() {
		int i=1/0;
	}*/
	
}
