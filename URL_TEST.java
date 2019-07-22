package MAIL;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class URL_TEST {

	public static void main(String args[]) throws IOException {
		
	DriverRepo wds=new DriverRepo();
	
    FileInputStream  fis=new 	FileInputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
    //====Find work book=============================
    HSSFWorkbook wb=new HSSFWorkbook(fis);
    //===Find Sheet==================================
    HSSFSheet s=wb.getSheetAt(0);//0 user side URL //1 staff side URL
    
    String LINK;
    String UN=null;
    String PW=null;

    HSSFCell Username=null;
    HSSFCell Password=null;
    HSSFCell Link = null;
	HSSFCell result=null;
	String msg = null;
	
	
    int no =s.getLastRowNum();
//    System.out.println(no);		
    
   
    for(int i=1 ; i<=no ; i++)  // i move for row 
    {
    	int j=0 ; // j move for cell;
    	
    	try
    	{
    		Link=s.getRow(i).getCell(j);//to get link from Column
    		
    		LINK=Link.getStringCellValue();
    		
    		wds.browser_url_set("chrome",LINK);
    		
    		System.out.println(LINK);
    		
//        	msg=HTTPLinkstatusChecker(LINK);	//to check status of link
        	
        	UN="test";//Username
        	PW="123";//Password
        	
        	
        	Username=s.getRow(i).createCell(j+1);
        	Username.setCellValue(UN);
    	
        	Password=s.getRow(i).createCell(j+2);
        	Password.setCellValue(PW);
    	
        	
        	
//    	    wds.LoginUS(UN,PW);//Valid Data
    	    
    	  //==========write result in excel===================
        	result=s.getRow(i).createCell(j+3);
        	result.setCellValue("Logged in SucessFully.");
    	
        	result=s.getRow(i).createCell(j+5);
        	result.setCellValue("Link Tested SucessFully.");
//        	send_email("ahire.psa@gmail.com", "C:\\Users\\admin\\Desktop\\PHP_Sites.xlsx", "TEST Subject", "THIS IS THE BODy");
        	
        	result=s.getRow(i).createCell(j+4);
        	result.setCellValue(msg);			//to write status of the link
        	
        	Thread.sleep(1000);
    		wds.LogoutUS();
    		
    		Thread.sleep(3000);
    		wds.wd.close();

    	}
    	
    	catch(Exception e)
    	{
    		
    	//==========write result in excel===================
    		result=s.getRow(i).createCell(j+3);
    		result.setCellValue("Login Failed");
    		
    		result=s.getRow(i).createCell(j+5);
        	result.setCellValue("Link Tested ");
        	
        	result=s.getRow(i).createCell(j+4);
        	result.setCellValue(msg);			//to write status of the link
    	
        	
//    		System.out.println(e.getMessage());
//    		wds.wd.close();
    	}
    	}
    	

    		//===File write mode==============================
    FileOutputStream  os=new 	FileOutputStream("C:\\Users\\admin\\Desktop\\URL.xls");	
    wb.write(os);
    wb.close();
    
    System.out.println("Test executed Successfully");
    




}//end of main

}
