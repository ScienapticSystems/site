package com.scienaptic.web.mail.impl;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.scienaptic.web.form.Resume;
import com.scienaptic.web.mail.MailService;

@Service
public class GmailService implements MailService {
	@Value("${mail.from}")
	private String from;

	@Value("${mail.password}")
	private String password;

	@Value("${mail.to}")
	private String to;

	public void sendSecureMail(com.scienaptic.web.form.Message form) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			if (!StringUtils.isEmpty(form.getEmail())) {
				try {
					Address[] addresses = InternetAddress.parse(form.getEmail());
					message.setReplyTo(addresses);
				} catch (AddressException e) {
				}
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Ask Us/Tell Us");
			message.setContent(getHtml(form.getName(), form.getEmail(), form.getPhone(), form.getMessage()),
					"text/html; charset=utf-8");

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	private String getHtml(final String name, final String email, final String phone, final String message) {
		String msg = "<html><head><title> Scienaptic Feedback </title></head><body>" + "<table><tr><td>Name:</td><td>"
				+ name + "</td></tr><tr><td>Email:</td><td>" + email + "</td></tr><tr><td>Phone:</td><td>" + phone
				+ "</td></tr><tr><td>Message:</td><td>" + message + "</td></tr></table></body></html>";
		return msg;
	}

	public void sendSecureMail(Resume resume) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			if (!StringUtils.isEmpty(resume.getEmail())) {
				try {
					Address[] addresses = InternetAddress.parse(resume.getEmail());
					message.setReplyTo(addresses);
				} catch (AddressException e) {
				}
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject("Resume Submission");

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(
					getHtml(resume.getName(), resume.getEmail(), resume.getPhone(), resume.getMessage()),
					"text/html; charset=utf-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);

			BodyPart attachmentBodyPart = new MimeBodyPart();
			attachmentBodyPart.setFileName(resume.getResume().getName());
			DataHandler fileReader = new DataHandler(fileSource);
			attachmentBodyPart.setDataHandler(fileReader);

			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}