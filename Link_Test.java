package MAIL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class Link_Test {

	public static void main(String[] args) throws IOException {
				
				WebDrivers wds=new WebDrivers();  	
				
				
		        FileInputStream  fis=new 	FileInputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
		        //====Find work book=============================
		        HSSFWorkbook wb=new HSSFWorkbook(fis);
		        //===Find Sheet==================================
		        HSSFSheet s=wb.getSheetAt(0);//0 user side URL //1 staff side URL
		        
		        String LINK;
		        String TOWN;
		        String UN=null;
		        String PW=null;

		        HSSFCell Username=null;
		        HSSFCell Password=null;
		        HSSFCell Link = null;
		        HSSFCell town = null;
				
				HSSFCell result=null;
				String msg = null;
				
				
		        int no =s.getLastRowNum();
//		        System.out.println(no);		
		        
		       
		        for(int i=1 ; i<=no ; i++)  // i move for row 
		        {
		        	int j=0 ; // j move for cell;
		        	
		        	try
		        	{
		        		Link=s.getRow(i).getCell(j);//to get link from Column
		        		
		        		LINK=Link.getStringCellValue();
		        		
		        		wds.browser_url_set("chrome",LINK);
		        		
		        		System.out.println(LINK);
		        		
			        	msg=HTTPLinkstatusChecker(LINK);	//to check status of link
			        	
			        	
			        	
			        	Username=s.getRow(i).getCell(j+3);
			        	UN=Username.getStringCellValue();
			        	//Username.setCellValue(UN);
		        	
			        	Password=s.getRow(i).getCell(j+4);
			        	PW=Password.getStringCellValue();
//			        	Password.setCellValue(PW);
		        	
			        	
			        	
		        	    wds.LoginUS(UN,PW);//Valid Data
		        	    
		        	  //==========write result in excel===================
		        	    
		        	    town=s.getRow(i).getCell(j+1);//to get link from Column
		        		
		        		TOWN=town.getStringCellValue();
		        		
		        	    
		        	    result=s.getRow(i).createCell(j+5);
			        	result.setCellValue("Logged in SucessFully.");
			        	
			        	

			        	result=s.getRow(i).createCell(j+6);
			        	result.setCellValue(msg);			//to write status of the link
			        	
			        	result=s.getRow(i).createCell(j+7);
			        	result.setCellValue("Link Tested SucessFully.");
			        	send_email("ahire.psa@gmail.com", "C:\\Users\\admin\\Desktop\\PHP_Sites.xlsx", "URL TESTING ALERT:PASSED", TOWN);
			        	
			        	
			        	Thread.sleep(1000);
		        		wds.LogoutUS();
		        		
		        		Thread.sleep(3000);
		        		wds.wd.close();

		        	}
		        	
		        	catch(Exception e)
		        	{
		        		
		        	//==========write result in excel===================
		        		result=s.getRow(i).createCell(j+5);
		        		result.setCellValue("Login Failed");
		        		
		        		send_email("ahire.psa@gmail.com", "C:\\Users\\admin\\Desktop\\PHP_Sites.xlsx", "URL TESTING ALERT:FAILED", "");
			        	

			        	result=s.getRow(i).createCell(j+6);
			        	result.setCellValue(msg);			//to write status of the link
			        	
		        		result=s.getRow(i).createCell(j+7);
			        	result.setCellValue("Link Tested ");
			        	
		        	
			        	
//		        		System.out.println(e.getMessage());
//		        		wds.wd.close();
		        	}
		        	}
		        	

		        		//===File write mode==============================
		        FileOutputStream  os=new 	FileOutputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
		        wb.write(os);
		        wb.close();
		        
		        System.out.println("Test executed Successfully");
		        

		  
	

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
		Msg="Valid URL";
		System.out.println(" Valid URL : "+objhttpURLConnection.getResponseCode());
		return Msg;
		}
	else if(objhttpURLConnection.getResponseCode()==404)
	{
		 Msg="Link Not Found";
		//This syntax will print http response code.
		System.out.println("Link Not Found :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==400)
	{
		 Msg="Bad Request";
		//This syntax will print http response code.
		System.out.println("Bad Request :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==401)
	{
		 Msg="Un-Authorized Request";
		//This syntax will print http response code.
		System.out.println("Un-Authorized Request :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else if(objhttpURLConnection.getResponseCode()==500)
	{
		 Msg="Internal Error";
		//This syntax will print http response code.
		System.out.println("Internal Error :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	else
	{
		 Msg="Error";
		//This syntax will print http response code.
		System.out.println("Error :"+objhttpURLConnection.getResponseCode());
		return Msg;
	}
	
	
	} catch (Exception e) {
		
		System.out.println(e.getMessage());

	}
	
	return Msg;
	

	}//end of link check status
	
	
	 public static  void send_email(String to, String filepath, String subject, String body)
	   
	   {
	      // Sender's email ID needs to be mentioned
	      String from = "megavision.alerts@gmail.com";

	      final String username = "megavision.alerts@gmail.com";//change accordingly
	      final String password = "dipesh@6990";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
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
	         

	       message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("ahire.psa@gmail.com"));
	         
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("desai.megtech@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("binod.maurya@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("veena.phatak130@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("mvishakha123@gmail.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("nisha_kotian490@yahoo.com"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("kumbhar_mahesh@yahoo.co.in"));
//	         message.addRecipients(Message.RecipientType.CC, InternetAddress.parse("fernandeslovi@gmail.com"));
	         
	         
	         // Set Subject: header field
	         message.setSubject(subject);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setContent(body, "text/html");

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = filepath;
	         
	         addAttachment(multipart, filename);
	         
//	         to send log file
	         addAttachment(multipart, "C:\\Users\\admin\\Desktop\\URL.xls");

//	          Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");
	  
	      } 
	      
	      catch (MessagingException e) 
	      
	      {
	    	  System.out.println("Issue occured while sending report using email facility...");         
	      }
	   }
	
	
	 private static void addAttachment(Multipart multipart, String filename) throws MessagingException
	   {
	       DataSource source = new FileDataSource(filename);
	       BodyPart messageBodyPart = new MimeBodyPart();        
	       messageBodyPart.setDataHandler(new DataHandler(source));
	       messageBodyPart.setFileName(filename);
	       multipart.addBodyPart(messageBodyPart);
	   }
	 
}//end of class

