package com.tanuja.service.impl;

import com.tanuja.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender mailSender;
	@Override
	public void sendEmailWithToken(String userEmail, String link) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message,"utf-8");
		String subject="Join Project Team invitation";
		String text="Click the link to join the project team:"+link;
		helper.setSubject(subject);
		helper.setText(text,true);
		helper.setTo(userEmail);

		try{
			mailSender.send(message);
		} catch (MailSendException e) {
			throw new MailSendException("failed to send email");
		}

	}
}
