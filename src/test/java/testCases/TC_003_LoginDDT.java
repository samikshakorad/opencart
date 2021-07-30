package testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.XLUtility;

public class TC_003_LoginDDT extends BaseClass
{
	@Test(dataProvider="LoginData")
	public void test_LoginDDT(String email, String pwd, String exp)
	{
		logger.info("Starting TC_003_LoginDDT");
		try 
		{
			driver.get(rb.getString("appURL"));
			driver.manage().window().maximize();
			logger.info("OpenUrl and Maximaize it.");
			
			HomePage hp=new HomePage(driver);
			hp.clickMyAccount();
			logger.info("click on My account");
			hp.clickLogin();
			logger.info("Click on Login");
			
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			logger.info("Provided emailid");

			lp.setPassword(pwd);
			logger.info("provided password");

			lp.clickLogin();
			logger.info("Click on Login");
			
			boolean targetpage=lp.isMyAccountPageExists();
			
			if(exp.equals("valid"))
			{
				if(targetpage==true)
				{
					logger.info("Login succesfully");
					MyAccountPage accpage=new MyAccountPage(driver);
					accpage.clickLogout();
					logger.info("Logout succesfully");
					Assert.assertTrue(true);
				}
				else
				{
					logger.error("Login failed");
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equals("Invalid"))
			{
				if(targetpage==true)
				{
					logger.info("Login succesfully");
					MyAccountPage accpage=new MyAccountPage(driver);
					accpage.clickLogout();
					logger.info("Logout succesfully");
					Assert.assertTrue(false);
				}
				else
				{
					logger.error("Login failed");
					Assert.assertTrue(true);
				}
			}			
			
		}
		catch(Exception e)
		{
			logger.fatal("login failed");
			Assert.fail();
		}
		
		logger.info("Finish Tc_003_LoginDDT");
	}
	
	
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		String path=".\\testData\\Opencart_LoginData.xlsx";
		XLUtility xlutil=new XLUtility(path);
		
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols =xlutil.getCellCount("Sheet1",1);
		
		String logindata[][]=new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)
		{
			for(int j=0;j<totalcols;j++)
			{
				logindata[i-1][j]=xlutil.getCellData("Sheet1", i, j);//1,0
			}
		}
		return logindata;
	
		

	}

}
