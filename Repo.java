package URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Repo {
	
WebDriver wd;
		
	By bts_Log_out=By.className("icon-key");
	By bts_Log_out_AL=By.xpath("//*[@id=\"modelWindow\"]/div/div/div/a[2]");

	//Log in method Parameters variables
	By Username=By.name("Username");
	By Password=By.name("Password");
	By Login_btn=By.className("loginbutton");
	//Log out method Parameters variables
	By TAn=By.id("ProfileName");
	By Log_out=By.linkText("Log Out");
	By Log_out_AL=By.xpath("//*[@id=\"modelWindow\"]/div/div/div[3]/a"); 
	
	public void LoginUS(String un1,String pwd1) throws InterruptedException//method for  loginUS(String "Username",String "Password")
	{
	wd.findElement(Username).sendKeys(un1);

	wd.findElement(Password).sendKeys(pwd1);

	wd.findElement(Login_btn).click();
	Thread.sleep(3000);
		}

	public void LogoutUS() throws InterruptedException //method for  logoutUS()
	{
	wd.findElement(TAn).click();
	wd.findElement(Log_out).click();
	Thread.sleep(1000);
	wd.findElement(Log_out_AL).click();
	
	}
	
	public void Logoutbts() throws InterruptedException //method for  logoutUS()
	{
	wd.findElement(bts_Log_out).click();
	Thread.sleep(1000);
	wd.findElement(bts_Log_out_AL).click();
	
	
	}
	
	
	public void browser_url_set(String browser_type, String link)//method to set the browser type(chrome , firefox) and the url (user url)
	{
		switch(browser_type)
		{
		
		case "chrome" :
			System.setProperty("webdriver.chrome.driver", "D:\\Eclipes\\Webdrivers\\chromedriver.exe");
		    wd=new ChromeDriver();
			wd.get(link);
			break; 
		
		case "firfox" :
			 System.setProperty("webdriver.gecko.driver", "D:\\Eclipes\\Webdrivers\\geckodriver.exe");
			 wd=new FirefoxDriver();
			 wd.get(link);
			 break; 
			 
		case " default" :
			 System.out.println("Select Proper browser type");
			 break; 
		}
	}


	

}
