package com.jweb.mails;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

	/**
	 * Action executed when sending a mail
	 * <p>
	 *     Send a mail to the receiver with the given subject and the given text
	 *     This action is done when a new user sign up and when news are added
	 * </p>
     * @param dest String
     *             The receiver of the mail
     * @param subject String
     *                The subject (or object) of the mail to send
     * @param text String
     *             The content of the mail
	 */
	public static void send(String dest, String subject, String text) {

		final String username = "jwebduturfu@gmail.com";
		final String password = "lejwebcestdelicieux";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
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
			message.setFrom(new InternetAddress("jwebduturfu@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(dest));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}