package mail;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;



public class MailSender {
	
	public MailSender() {}
	
	public static void sendMail(String text, String mail, String subject) throws AddressException, MessagingException {	
		String to = mail;
		String from = "noreply@optimusbus.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);
		message.setText(text);
		Transport.send(message);
	}
}
