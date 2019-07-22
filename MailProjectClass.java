package MAIL;

import java.util.Date;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailProjectClass {

public   void test() {
	
    final String username = "ahire.psa@gmail.com";
    final String password = "aaradhya13";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("ahire.psa@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("ahire.psa@gmail.com"));
        message.setSubject("Failed Town list ");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = "C:\\Users\\admin\\Desktop\\PHP_Sites.xlsx";
        String fileName = "Test Result Sheet";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);
        
        
        
        message.setSentDate(new Date());
        
        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    }
    catch (MessagingException e) {
        e.printStackTrace();
    }
}

}
  
