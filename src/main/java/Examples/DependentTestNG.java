package Examples;
import org.testng.annotations.*;

public class DependentTestNG {
	
	@Test(dependsOnMethods= {"OpenBrowser"})
	public void signIn(){
		System.out.println("SignIn successfull");
	}
	
	@Test
	public void OpenBrowser(){
		System.out.println("The browser is opened");
	}
	
	@Test(dependsOnMethods= {"signIn"})
	public void logout(){
		System.out.println("logout successfull");
	}

}
