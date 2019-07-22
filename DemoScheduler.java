package URL;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DemoScheduler implements Job {
 

	
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		WebDriver wd = null;
		try {
			System.out.println("INSIDE URL");
			Repo wds=new Repo();
		
		 FileInputStream  fis=new 	FileInputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
	        //====Find work book=============================
	        HSSFWorkbook wb=new HSSFWorkbook(fis);
	        //===Find Sheet==================================
	        HSSFSheet s=wb.getSheetAt(1);//0 user side URL //1 staff side URL
	        
	        List<String> F_Town = new ArrayList<>();
	        List<String> P_Town = new ArrayList<>();
	        
	        
	        
	        String LINK;
	        String TOWN=null;
	        String UN=null;
	        String PW=null;

	        HSSFCell Username=null;
	        HSSFCell Password=null;
	        HSSFCell Link = null;
			HSSFCell result=null;
			String msg = null;
			HSSFCell Town=null;
			
			
	        int no =s.getLastRowNum();
	        System.out.println(no);		
	        
	       
	        for(int i=1 ; i<=no ; i++)  // i move for row 
	        {
	        	int j=0 ; // j move for cell;
	        	
	        	try
	        	{
	        		//to get link from Column
	        		Link=s.getRow(i).getCell(j+2);
	        		LINK=Link.getStringCellValue();
	        		
	        		//to get town Name from Column
	        		Town=s.getRow(i).getCell(j+1);
	        		TOWN=Town.getStringCellValue();
	        		
	        		
	        		wds.browser_url_set("chrome",LINK);
	        			Thread.sleep(3000);
	        			
	        			wds.wd.navigate().refresh();   
	        			
	        		msg=HTTPLinkstatusChecker(LINK);//URL TEST FUNCTION
	        		
//	        		System.out.println(LINK);
//	        		System.out.println(Town);
	        		
	        		Username=s.getRow(i).getCell(j+3);
	        		UN=Username.getStringCellValue();
		        	
//	        		System.out.println(UN);
	        		
//	        		 Cell celldata=(Cell) s.getRow(i).getCell(j+3);     
//		              switch(celldata.getCellType())
//		              {
//		              case Cell.CELL_TYPE_STRING:
//		            	  Username.add(celldata.getStringCellValue());
//		                  break;
//		              case Cell.CELL_TYPE_NUMERIC:
//		            	  Double d=celldata.getNumericCellValue();//to convert double to integer
//		            	  int i = d.intValue();
//		            	  String str1 = Integer.toString(i); 
//		            	  Username.add(str1);
//		            	  	
		            	  
	        		Password=s.getRow(i).getCell(j+4);
	        		PW=Password.getStringCellValue();
		        	
		        	
	        	    wds.LoginUS(UN,PW);//Valid Data
	        	    
	        	    //==========write result in excel===================
	            	result=s.getRow(i).createCell(j+5);
	            	result.setCellValue("Logged in SucessFully.");
	            	
	             	result=s.getRow(i).createCell(j+6);
	            	result.setCellValue(msg);			//to write status of the link
	            	
	        	
	            	result=s.getRow(i).createCell(j+7);
	            	result.setCellValue("WORKING");
	                       	
	        	    wds.LogoutUS();
	        	    
	        	    P_Town.add(TOWN);
	        	    wds.wd.quit();
	        		
	        
	        		
	        	}
	        
	        	
	        	catch(Exception e)
	        	{
	        		
//	        		System.out.println(e.getMessage());
	        		
	        		if(e.getMessage().startsWith("no such element:"))
	        		{
	        			if(TOWN.equals("bts")) {
	        				
	        				wds.Logoutbts();
	        				P_Town.add(TOWN);
	        				 //==========write result in excel===================
	    	            	result=s.getRow(i).createCell(j+5);
	    	            	result.setCellValue("Logged in SucessFully.");
	    	            	
	    	             	result=s.getRow(i).createCell(j+6);
	    	            	result.setCellValue(msg);			//to write status of the link
	    	            	
	    	        	
	    	            	result=s.getRow(i).createCell(j+7);
	    	            	result.setCellValue("WORKING");
	    	                   
	        			}
	        			else
		        		{
		        			F_Town.add(TOWN);
		        		}
	        			
	        			
	        		}
	        		else
	        		{
	        			//==========write result in excel===================
		        		result=s.getRow(i).createCell(j+5);
		        		result.setCellValue("Login Failed");
					   	   
			        	result=s.getRow(i).createCell(j+6);
			        	result.setCellValue(msg);			//to write status of the link
			        	
		        		result=s.getRow(i).createCell(j+7);
			        	result.setCellValue("NOT WORKING");
			        	
	        			F_Town.add(TOWN);
	        		}
	        		
	        		
		        	
		        	wds.wd.quit();
		        	
	        	}
	        	
	        	
	        	}
	        System.out.println(P_Town);     
	        System.out.println(F_Town);
	        send_email("ahire.psa@gmail.com", "C:\\Users\\admin\\Desktop\\URL.xls", "URL Testing of PHP Sites ", F_Town,P_Town);
	        //===File write mode==============================
	        FileOutputStream  os=new 	FileOutputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
	        wb.write(os);
	        wb.close();
	        
	        System.out.println("ALL Test executed Successfully");
	        P_Town.clear();
	        F_Town.clear();
		}
		catch(Exception e1)
		{
//			System.out.println(e1.getMessage());
    		
		}

		
	}//end of main
	
		
		

	public static String  HTTPLinkstatusChecker(String linkUrl)
	{
		String Msg = null;
	try
	{
	URL url = new URL(linkUrl);
	
	HttpURLConnection objhttpURLConnection=(HttpURLConnection)url.openConnection();

	objhttpURLConnection.setConnectTimeout(3000);

	objhttpURLConnection.connect();


	if(objhttpURLConnection.getResponseCode()==200)
		{
		//This syntax will print http response code.
		Msg=objhttpURLConnection.getResponseMessage();
		System.out.println("Valid URL : "+objhttpURLConnection.getResponseCode());
		return Msg;
		}
	else if(objhttpURLConnection.getResponseCode()==404)
	{
		Msg=objhttpURLConnection.getResponseMessage();
		//This syntax will print http response code.
		System.out.println("Object not found! :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==400)
	{
		Msg=objhttpURLConnection.getResponseMessage();
		//This syntax will print http response code.
		System.out.println("Bad Request :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==401)
	{
		Msg=objhttpURLConnection.getResponseMessage();
		//This syntax will print http response code.
		System.out.println("Un-Authorized Request :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==500)
	{
		Msg=objhttpURLConnection.getResponseMessage();
		//This syntax will print http response code.
		System.out.println("Internal Error :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}else if(objhttpURLConnection.getResponseCode()==301)
	{
		 Msg=objhttpURLConnection.getResponseMessage();
		
		//This syntax will print http response code.
		System.out.println("Moved Permanently :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else
	{
		 Msg=objhttpURLConnection.getResponseMessage();
		 
		//This syntax will print http response code.
		System.out.println("Error :"+objhttpURLConnection.getResponseCode());
		System.out.println("URL :"+objhttpURLConnection.getURL());
		System.out.println("Status :"+objhttpURLConnection.getResponseMessage());
		
		return Msg;
	}
	
	
	} catch (Exception e) {
		
//		System.out.println(e.getMessage());

	}
	
	return Msg;
	

	}//end of link check status
	
	
	
	
	   public static void send_email(String to, String filepath, String subject, List<String> FAILD,List<String> PASS)
	   
	   {
	      String from = "megavision.alerts@gmail.com";

	      final String username = "megavision.alerts@gmail.com";//change accordingly
	      final String password = "dipesh@6990";//change accordingly

	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props, new javax.mail.Authenticator()       
	      {
	            protected PasswordAuthentication getPasswordAuthentication()             
	            {
	               return new PasswordAuthentication(username, password);
	            }
	      }      
	    );

	      try      
	      {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));         
	         
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("desai.megtech@gmail.com"));
// 	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("fullcircle.permit@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("engg.ajaymane@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("binod.maurya@gmail.com"));
		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("rathodankisha@gmail.com"));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("fernandeslovi@gmail.com "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("mvishakha123@gmail.com "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("kumbhar_mahesh@yahoo.co.in "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("nisha_kotian490@yahoo.com "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("veena.phatak130@gmail.com "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("tapasyaganekar@gmail.com "));
//		     message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("ajaymcool@gmail.com "));
//		        
	         
	         

	         

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();
	         StringBuilder b = new StringBuilder();
	         for(String P_town : PASS) 
	             b.append(P_town).append("\n").append("  : Working   |  ");
	             String p_town = b.toString();
	         
	         // Now set the actual message
	      
	         StringBuilder b1 = new StringBuilder();
	         for(String F_town : FAILD) 
	             b1.append(F_town).append("\n").append("  :Not Working   |  ");
	             String f_town = b1.toString();
	             
	             
	         if(FAILD.isEmpty()==true) {
	        	 message.setSubject(subject+ " : " +new Date()+ " : Passed");
	        	 messageBodyPart.setContent("Hi Team,<br><br> No issues found while test execution of following Towns URL:<br><font color=\"Green\">"+p_town.toString()+"</font><br><br> <br>Regards,<br>Megavision Team.", "text/html");
	    	 	         }
	         else  if(PASS.isEmpty()==true) { 
	        	 message.setSubject(subject+ " : " +new Date()+ " : Failed");
	        	 messageBodyPart.setContent("Hi Team, <br><br>Test execution failed for following Towns URL:<br><font color=\"Red\"+\">"+f_town.toString()+"</font><br><br><br> <br>Regards,<br>Megavision Team.", "text/html");
	    	 }
	         else {
	        	 message.setSubject(subject+ " : " +new Date()+ " : Failed");
	        	  messageBodyPart.setContent("Hi Team, <br><br>Test execution failed for following Town URL:<br><font color=\"Red\"+\">"+f_town.toString()+"</font><br><br> No issues found while test execution of following Town URL:<br><font color=\"Green\">"+p_town.toString()+"</font><br><br> <br>Regards,<br>Megavision Team.", "text/html");
	    	 }
	       
	         

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	   

	         message.setContent(multipart);

	         Transport.send(message);

	         System.out.println("Sent message successfully....");  
	      } 
	      
	      catch (MessagingException e)       
	      {
	         throw new RuntimeException(e);
	      }
	   }//end of send_email class

//	 private static void addAttachment(Multipart multipart, String filename) throws MessagingException
//	   {
//	       DataSource source = new FileDataSource(filename);
//	       BodyPart messageBodyPart = new MimeBodyPart();        
//	       messageBodyPart.setDataHandler(new DataHandler(source));
//	       messageBodyPart.setFileName(filename);
//	       multipart.addBodyPart(messageBodyPart);
//	   }//end of addAttachment
//	
	    public  void Scheduler_Mrng() {
	    	 
	        // Create a calendar instance
	        Calendar calendar = Calendar.getInstance();
	 
	        // Set time of execution. Here, we have to run every day10:35:10 AM; so,
	        // setting all parameters.
	        calendar.set(Calendar.HOUR, 3);
	        calendar.set(Calendar.MINUTE,26);
	        calendar.set(Calendar.SECOND, 00);
	        calendar.set(Calendar.AM_PM, Calendar.PM);
	 
	        Long currentTime = new Date().getTime();
	// 
//	        // Check if current time is greater than our calendar's time. If So,
//	        // then change date to one day plus. As the time already pass for
//	        // execution.
	        if (calendar.getTime().getTime() < currentTime) {
	            calendar.add(Calendar.DATE, 1);
	        }
	// 
//	        // Calendar is scheduled for future; so, it's time is higher than
//	        // current time.
	        long startScheduler = calendar.getTime().getTime() - currentTime;
	 
//	         Setting stop scheduler at 4:21 PM. Over here, we are using current
//	         calendar's object; so, date and AM_PM is not needed to set
//	        calendar.set(Calendar.HOUR, 11);
//	        calendar.set(Calendar.MINUTE,15);
//	        calendar.set(Calendar.AM_PM, Calendar.AM);
	 
	        // Calculation stop scheduler
	        long stopScheduler = calendar.getTime().getTime() - currentTime;
	 
	        // Executor is Runnable. The code which you want to run periodically.
	        Runnable task = new Runnable() {
	 
	            @Override
	            public void run() {
	            	
	            	URL_TEST URL=new URL_TEST();
	            	URL.url();
	            	
	                System.out.println("scheduler EXECUTED");
	            }
	        };
	 
	        // Get an instance of scheduler
	        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        // execute scheduler at fixed time.
	     
	        scheduler.scheduleAtFixedRate(task, startScheduler, currentTime, MILLISECONDS);
	    }
	    
	    
	    public  void Scheduler_Evening() {
	    	 
	        // Create a calendar instance
	        Calendar calendar = Calendar.getInstance();
	 
	        // Set time of execution. Here, we have to run every day10:35:10 AM; so,
	        // setting all parameters.
	        calendar.set(Calendar.HOUR, 3);
	        calendar.set(Calendar.MINUTE,35);
	        calendar.set(Calendar.SECOND, 00);
	        calendar.set(Calendar.AM_PM, Calendar.PM);
	 
	        Long currentTime = new Date().getTime();
	// 
//	        // Check if current time is greater than our calendar's time. If So,
//	        // then change date to one day plus. As the time already pass for
//	        // execution.
	        if (calendar.getTime().getTime() < currentTime) {
	            calendar.add(Calendar.DATE, 1);
	        }
	// 
//	        // Calendar is scheduled for future; so, it's time is higher than
//	        // current time.
	        long startScheduler = calendar.getTime().getTime() - currentTime;
	 
//	         Setting stop scheduler at 4:21 PM. Over here, we are using current
//	         calendar's object; so, date and AM_PM is not needed to set
//	        calendar.set(Calendar.HOUR, 11);
//	        calendar.set(Calendar.MINUTE,15);
//	        calendar.set(Calendar.AM_PM, Calendar.AM);
	 
	        // Calculation stop scheduler
	        long stopScheduler = calendar.getTime().getTime() - currentTime;
	 
	        // Executor is Runnable. The code which you want to run periodically.
	        Runnable task = new Runnable() {
	 
	            @Override
	            public void run() {
	            	
	            	URL_TEST URL=new URL_TEST();
	            	URL.url();
	            	
	                System.out.println("scheduler EXECUTED");
	            }
	        };
	 
	        // Get an instance of scheduler
	        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	        // execute scheduler at fixed time.
	     
	        scheduler.scheduleAtFixedRate(task, startScheduler, currentTime, MILLISECONDS);
	    }
	

}
