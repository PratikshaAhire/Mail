package MAIL;



import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DriverRepo {
	
	WebDriver wd;
	
	//Log in method Parameters variables
	By Username=By.name("Username");
	By Password=By.name("Password");
	By Login_btn=By.xpath("//*[@id=\"frmLogin\"]/div[3]/button");
	//Log out method Parameters variables
	By TAn=By.id("ProfileName");
	By Log_out=By.linkText("Log Out");
	By Log_out_AL=By.xpath("//*[@id=\"modelWindow\"]/div/div/div[3]/a"); 
	
	//Site_information method Parameters variables
	By StrName1=By.name("StrName");
	By StrNumber1=By.name("StrNumber");
	By MapBlockLotSelect1=By.name("MapBlockLotSelect");

	// Global Lists
	String[] Mail= {"MailStNum","MailStName","MailCity","MailState","MailZip"};
    String[] App= {"AppStNum","AppStName","AppCity","AppState","AppZip"};
    String[] Owner= {"OwnerStNum","OwnerStName","OwnerCity","OwnerState","OwnerZip"};
    
    String N_APP;// stores the type of application
    
    By OwnerName1=By.id("OwnerName");
  	By OwnerStNum1=By.id("OwnerStNum");
  	By OwnerStName1=By.id("OwnerStName");
  	By OwnerCity1=By.id("OwnerCity");
  	By OwnerState1=By.id("OwnerState");
  	By OwnerZip1=By.id("OwnerZip");
  	By OwnerPhone1=By.id("OwnerPhone");
  	By OwnerEmail1=By.id("OwnerEmail");
  	
  	By AppName1=By.id("AppName");
   	By AppStNum1=By.id("AppStNum");
   	By AppStName1=By.id("AppStName");
   	By AppCity1=By.id("AppCity");
   	By AppState1=By.id("AppState");
   	By AppZip1=By.id("AppZip");
   	By AppPhone1=By.id("AppPhone");
   	By AppEmail1=By.id("AppEmail");

  	
	By Name_of_Premises1=By.id("NameOfPremises");
	By ContactPersonPhone1=By.id("ContactPersonPhone");
	
	
	
	By Declaration1=By.id("Declaration");
	By InstructionDeclaration1=By.id("InstructionDeclaration");
	By submit_form1=By.id("submit_form");
	
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

	
	public void New_app(String app_type) throws InterruptedException //method to select the type of application
	{
	      wd.findElement(By.id("new-app")).click();
	      N_APP=app_type;
	      switch(app_type)
	  	{
	      	case "CI" :	    
	      		Thread.sleep(3000);
	      		wd.findElement(By.linkText("Certificate Of Inspection")).click();
	      		String Title = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title);
	      		Thread.sleep(3000);
	      		break;
			 
			case "COMM" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Commercial Building Permit")).click();
	      		String Title2 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title2);
				Thread.sleep(3000);
	   		 break;
	   		 
			case "CSP" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Commercial Short Permit")).click();
	      		String Title3 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title3);
				
		   		Thread.sleep(3000);
		   		break;
		   		 
			case "ELECT" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Electrical Permit")).click();
	      		String Title4 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title4);
		   		Thread.sleep(3000);
		   		break;
		   		   		 
			case "GAS" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Gas Permit")).click();
	      		String Title5 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title5);
		   		Thread.sleep(3000);
		   		break;
		   
		   		 
			case "MECH" :
				Thread.sleep(3000);
				 wd.findElement(By.linkText("Mechanical Permit")).click();
		      		String Title6 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
		      		System.out.println(Title6);
		   		Thread.sleep(3000);
		   		break;
		    
		   		 
			case "PLUMB" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Plumbing Permit")).click();
	      		String Title7 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title7);
		   		Thread.sleep(3000);
		   		break;
		   		 
		   	
			case "RESI" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Residential Building Permit")).click();
		      	String Title8 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
		      	System.out.println(Title8);
		   		Thread.sleep(3000);
		   		break;
		   		
		   		
			case "RSP" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Residential Short Permit")).click();
	      		String Title9 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title9);
		   		Thread.sleep(3000);
		   		break;
		   		 
		   		
			case "SHEET" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Sheet Metal Permit")).click();
	      		String Title10 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title10);	   		Thread.sleep(3000);
		   		break;
		   		 
		   		
			case "SIGN" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Sign Permit")).click();
	      		String Title11 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title11);
		   		Thread.sleep(3000);
		   		break;
		   		
		   		
			case "SMKMOD" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Smoke And CO Modification Permit")).click();
	      		String Title12 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title12);
		   		Thread.sleep(3000);
		   		break;
		   		 
		   		
			case "SFA" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Solid Fuel Appliance Permit")).click();
	      		String Title13 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title13);
		   		Thread.sleep(3000);
		   		break;
		   		 
		   		
			case "FIREALARM" :
				 Thread.sleep(3000);
				 wd.findElement(By.linkText("Sprinkler and Fire Alarm Systems Application")).click();
		      		String Title14 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
		      		System.out.println(Title14);
		   		 Thread.sleep(3000);
		   		 break;
		   		
		   		 
			case "TENT" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Tent Permit")).click();
	      		String Title15 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title15);
		   		Thread.sleep(3000);
		   		break;
		   		
			case "TRENCH" :
				Thread.sleep(3000);
				wd.findElement(By.linkText("Trench Permit")).click();
	      		String Title16 = wd.findElement(By.xpath("//*[@id=\"appportlet\"]/div/div[1]/div/span")).getText();
	      		System.out.println(Title16);
				Thread.sleep(3000);
		    	break;
		   		 
			case " default" :
				 System.out.println("Select Proper Application Type");
		   	      	Thread.sleep(3000);
				 break; 
	      
	  	}
		
	}


		public void LoginUS(String un1,String pwd1)//method for  loginUS(String "Username",String "Password")
		{
		wd.findElement(Username).sendKeys(un1);
		wd.findElement(Password).sendKeys(pwd1);
		wd.findElement(Login_btn).click();
		}

		public void LogoutUS() throws InterruptedException //method for  logoutUS()
		{
		wd.findElement(TAn).click();
		wd.findElement(Log_out).click();
		Thread.sleep(1000);
		wd.findElement(Log_out_AL).click();
		
		}
		
		public void Site(String StrName , String StrNumber, String MapBlockLotSelect ) throws InterruptedException //method  to get the value for site information
		{
			
			System.out.println("================Section 1: Site Information==================");
			Select s = new Select(wd.findElement(StrName1));// for street name
			s.selectByVisibleText(StrName);
		    String Site_StrName = s.getFirstSelectedOption().getText();
		    System.out.println("Street Name : "+Site_StrName);
		   	    
		   // Thread.sleep(3000);
		    
		    Select s1=new Select(wd.findElement(StrNumber1));// for street number
		    s1.selectByValue(StrNumber);
		    String Site_StrNum = s1.getFirstSelectedOption().getText();
		    System.out.println("Street Number : "+Site_StrNum);
		    
		   
		   //Thread.sleep(3000);
		        
		    Select s2=new Select(wd.findElement(MapBlockLotSelect1));// for MapBlockLot
		    s2.selectByVisibleText(MapBlockLotSelect);
		    String Site_MapBlockLot = s2.getFirstSelectedOption().getText();
		    System.out.println("MapBlockLot : "+Site_MapBlockLot);
			
			}
		
		
		
		By addChk=By.name("addChk");
		
		//method  to get the value for mail address information
	
		public void Mail_add(String mail) throws InterruptedException, UnsupportedFlavorException, IOException{
			System.out.println("================Section 4: Mail Address ==================");
			
			if(mail=="SI")
					mail="Site Information";
				 else if(mail=="OI")
					 mail="Owner Information";
				 else
					 mail="Applicant Information";
				 
			
			 Select s4 = new Select(wd.findElement(By.id("addChk")));
			 s4.selectByVisibleText(mail);
			 String mail1 = s4.getFirstSelectedOption().getText();
			 System.out.println("Same as: "+mail1);
			 	
		      switch(mail1) {
		      case "Site Information" :
		  		String copy = Keys.chord(Keys.CONTROL,Keys.chord("c"));
		  		wd.findElement(By.id("MailStNum")).sendKeys(Keys.CONTROL+"a");
		  	    wd.findElement(By.id("MailStNum")).sendKeys(copy);
		  	    Clipboard clipboard1 = Toolkit.getDefaultToolkit().getSystemClipboard();
		  	    Transferable contents = clipboard1.getContents(null);
		  	    String MailStNum = (String) contents.getTransferData(DataFlavor.stringFlavor);
		  	    System.out.println("Mail Street Number : " +MailStNum);

		  		 	 
		  	    wd.findElement(By.id("MailStName")).sendKeys(Keys.CONTROL+"a");
		  	    wd.findElement(By.id("MailStName")).sendKeys(copy);
		  	    Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
		  	    Transferable contents2 = clipboard2.getContents(null);
		  	    String MailStName = (String) contents2.getTransferData(DataFlavor.stringFlavor);
		  	    System.out.println("Mail Street Name : " +MailStName);
		  	break;
		  	
		  		    
		      case "Owner Information" :
		    	  
		    	  
		    	  	ArrayList<String> Mail1=new ArrayList<String>();
		  			ArrayList<String> Own1=new ArrayList<String>();
		  			Mail1.removeAll(Mail1); 
		  			
		    	  for (String O:Owner) {
				    	
					     String copy1 = Keys.chord(Keys.CONTROL,Keys.chord("c"));
					   wd.findElement(By.id(O)).sendKeys(Keys.CONTROL+"a");
					    wd.findElement(By.id(O)).sendKeys(copy1);
					     Clipboard clipboard3 = Toolkit.getDefaultToolkit().getSystemClipboard();
					     Transferable contents3 = clipboard3.getContents(null);
					     String OwnerData = (String) contents3.getTransferData(DataFlavor.stringFlavor);
					     Own1.add(OwnerData);
				     
				  }
				for (String M:Mail) {
					  
				     String copy1 = Keys.chord(Keys.CONTROL,Keys.chord("c"));
				    wd.findElement(By.id(M)).sendKeys(Keys.CONTROL+"a");
				     wd.findElement(By.id(M)).sendKeys(copy1);
				     Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
				     Transferable contents4 = clipboard4.getContents(null);
				     String MailData = (String) contents4.getTransferData(DataFlavor.stringFlavor);
				     Mail1.add(MailData);
				    
				   	 }
			     
			     if(Own1.equals(Mail1))
			     {
			    	 System.out.println(" Same");
			    	 
			     }
			     else
			     {
			    	 System.out.println("not Same");
			    	 
			     }
		  			Mail1.clear();
		    	  	    	  break;  
		    	  
		    	  
		    	  
		      case "Applicant Information" :
		       	 
		    	  	ArrayList<String> Mail2=new ArrayList<String>();
		    	  
					ArrayList<String> App1=new ArrayList<String>();
				
		    	  for (String A:App) {
				    	
					     String copy_A = Keys.chord(Keys.CONTROL,Keys.chord("c"));
					     wd.findElement(By.id(A)).sendKeys(Keys.CONTROL+"a");
					     wd.findElement(By.id(A)).sendKeys(copy_A);
					     Clipboard clipboard_A = Toolkit.getDefaultToolkit().getSystemClipboard();
					     Transferable contents_A = clipboard_A.getContents(null);
					     String AppData = (String) contents_A.getTransferData(DataFlavor.stringFlavor);
					     App1.add(AppData);
				  }
				for (String M:Mail) {
					  String copy1 = Keys.chord(Keys.CONTROL,Keys.chord("c"));
				     wd.findElement(By.id(M)).sendKeys(Keys.CONTROL+"a");
				     wd.findElement(By.id(M)).sendKeys(copy1);
				     Clipboard clipboard_M = Toolkit.getDefaultToolkit().getSystemClipboard();
				     Transferable contents_M = clipboard_M.getContents(null);
				     String MailData = (String) contents_M.getTransferData(DataFlavor.stringFlavor);
				     Mail2.add(MailData); 
				}
				
				
			    	  if(App1.isEmpty())
			    	  {
			    	     System.out.println("Input field is empty");
			    	  }
			    	  if(App1.equals(Mail2))
				      {
			    		  System.out.println("Same");
	    		  
			    	  }
			    	
			     else
			     {
			    	 System.out.println("not Same");
			    	 if(App1.isEmpty())
			    	  {
			    	     System.out.println("Input field is empty");
			    	  }
			    	  else {
			    		  System.out.println(App1);
					    	System.out.println(Mail2);
			    		  
			    	  }
			    	    	 
			     }
		    	  		      
		      Mail2.clear(); 
		    	  break;    
		      }//end of switch
		      
		    
		}
		  	
		
		public void Owner_info(String OwnerName,String OwnerStNum,String OwnerStName,String OwnerCity,String OwnerState,String OwnerZip,String OwnerPhone,String OwnerEmail) throws InterruptedException  //method for  Owner Information				{
		{
		    Thread.sleep(3000);
		    System.out.println("================Section 2: Owner Information==================");
		    wd.findElement(OwnerName1).clear();
		    wd.findElement(OwnerStNum1).clear();
		    wd.findElement(OwnerStName1).clear();
		    wd.findElement(OwnerCity1).clear();
		    wd.findElement(OwnerState1).clear();
		    wd.findElement(OwnerZip1).clear();
		    wd.findElement(OwnerPhone1).clear();
		    wd.findElement(OwnerEmail1).clear();
		    Thread.sleep(3000);
		    
		    wd.findElement(OwnerName1).sendKeys(OwnerName);
		    wd.findElement(OwnerStNum1).sendKeys(OwnerStNum);
		    wd.findElement(OwnerStName1).sendKeys(OwnerStName);
		    wd.findElement(OwnerCity1).sendKeys(OwnerCity);
		    wd.findElement(OwnerState1).sendKeys(OwnerState);
		    wd.findElement(OwnerZip1).sendKeys(OwnerZip);
		    wd.findElement(OwnerPhone1).sendKeys(OwnerPhone);
		    wd.findElement(OwnerEmail1).sendKeys(OwnerEmail);
		    
			}//end of owner
		      
		public void Applicant_info(String AppName,String AppStNum,String AppStName,String AppCity,String AppState,String AppZip,String AppPhone,String AppEmail) throws InterruptedException  //method for  Owner Information				{
		{
		    Thread.sleep(3000);
		    System.out.println("================Section 3: Applicant Information==================");
		    wd.findElement(AppName1).clear();
		    wd.findElement(AppStNum1).clear();
		    wd.findElement(AppStName1).clear();
		    wd.findElement(AppCity1).clear();
		    wd.findElement(AppState1).clear();
		    wd.findElement(AppZip1).clear();
		    wd.findElement(AppPhone1).clear();
		    wd.findElement(AppEmail1).clear();
		    Thread.sleep(3000);
		    
		    wd.findElement(AppName1).sendKeys(AppName);
		    wd.findElement(AppStNum1).sendKeys(AppStNum);
		    wd.findElement(AppStName1).sendKeys(AppStName);
		    wd.findElement(AppCity1).sendKeys(AppCity);
		    wd.findElement(AppState1).sendKeys(AppState);
		    wd.findElement(AppZip1).sendKeys(AppZip);
		    wd.findElement(AppPhone1).sendKeys(AppPhone);
		    wd.findElement(AppEmail1).sendKeys(AppEmail);
		    
			}//end of owner
		      
		
		public void CI_Sec_5(String Name_of_Premises,String ContactPersonPhone )
		{
				Actions ACT=new Actions(wd);
			 	wd.findElement(Name_of_Premises1).clear();	
			    wd.findElement(Name_of_Premises1).sendKeys(Name_of_Premises);
			    wd.findElement(ContactPersonPhone1).sendKeys(ContactPersonPhone);
		}
		
		public void submit() throws InterruptedException
		{
			Actions ACT=new Actions(wd);
			ACT.click(wd.findElement(Declaration1)).perform();//declaration of forms
			ACT.click(wd.findElement(InstructionDeclaration1)).perform();//declaration of of instruction forms	
			Thread.sleep(3000);
			ACT.click(wd.findElement(submit_form1)).perform();
			Thread.sleep(300);
			System.out.println("Form Submmited");
			
		}
		

		
}//end of class
		
		
		
		
		
		
		
		
		
		




