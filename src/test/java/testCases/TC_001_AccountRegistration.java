package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass{	
	
	@Test(groups={"regression","master"})
	public void test_account_registration()
	{
		try
		{
		driver.get(rb.getString("appURL"));
		logger.info("Open the URL");
		driver.manage().window().maximize();
		logger.info("Maximize the page");
		
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Click on My Account");
		hp.clickRegister();
		logger.info("Click on Register");

		
		AccountRegisterPage regpage=new AccountRegisterPage(driver);
		
		regpage.setFirstName("Swapl");
		logger.info("Provided Fname");

		regpage.setLastName("Ko");
		logger.info("Provided last name");

		regpage.setEmail(randomestring()+"@gmail.com");
		logger.info("Provided gmail id");

		regpage.setPassword("123456");
		logger.info("Provided password");

		regpage.setConfirmPassword("123456");
		logger.info("Confirm Password");

		regpage.setTelephone("9879879870");
		logger.info("Provided phone number");

		regpage.setYes();
		logger.info("Select Yes");

		regpage.setNo();
		logger.info("Select No");

		regpage.setPrivacyPolicy();
		logger.info("Select check box");

		regpage.clickContinue();
		logger.info("Click on continue");

		
		String confmsg=regpage.getConfirmationMsg();
		
		if(confmsg.equals("Your Account Has Been Created!"))
		{
			logger.info("Account registration succesful");
			Assert.assertTrue(true);
			
		}
		else
		{
			logger.info("Account registration failed");
			captureScreen(driver, "test_account_registration");
			Assert.assertTrue(false);
		}
		

	}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("Finish TC_001_AccountRegistration");
	}
	
}
