package testBase;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ResourceBundle;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.junit.runners.Parameterized.Parameter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
public WebDriver driver;
public Logger logger;
public ResourceBundle rb;
	
	@BeforeClass(groups= {"master","sanity","regression"})
	@Parameters({"browser"})
	public void setup(String br)
	{
		//for load config properties
		rb=ResourceBundle.getBundle("config");
		
		// for logging
		logger=LogManager.getLogger(this.getClass());//for logging
		
		if(br.equals("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			
		}
		else if(br.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		
	}
	
	public  String randomestring()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(8);
		return (generatedString);
		
	}
	public  int randomeInteger()
	{
		String generatednumeric=RandomStringUtils.randomNumeric(7);
		return (Integer.parseInt(generatednumeric));
		
	}
	
	//capture screenshot
	
	public void captureScreen(WebDriver driver, String tname) throws IOException //tname-name of testcase which was failed
	{
		TakesScreenshot ts=(TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir")+"\\screenshots\\"+tname+".png");
		FileUtils.copyFile(source, target);
	}

}
