package com.VotingManagementSystem.services;
import java.util.List;
import java.util.Set;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.mail.internet.MimeMessage;
import utils.HtmlTemplateEmail;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailSendService {
private static final Logger logger = LogManager.getLogger(MailSendService.class);


@Value("${spring.mail.username}")
private String sender;

@Autowired
private JavaMailSender mailSender;





public String sendMsg(String emailTo, String sendMsg , String ObjectEmail) {

    try {

      String Resultat =
          sendSimpleMailHtml(emailTo, sendMsg, ObjectEmail);
    return Resultat;
    } catch (Exception e) {
      // TODO Auto-generated catch block
    	
      e.printStackTrace();
      return "Error while Sending Mail";
    }

  }
public String testemail(String recipient, String msgBody, String subject) {
	String to = recipient;
    //provide sender's email ID
    String from = "jakartafrom@example.com";
    //provide Mailtrap's username
    final String username = "543534849ff63d";
    //provide Mailtrap's password
    final String password = "d4617a3c45ead0";
    //provide Mailtrap's host address
    String host = "sandbox.smtp.mailtrap.io";
    //configure Mailtrap's SMTP server details
    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "587");
    //create the Session object
    Session session = Session.getInstance(props,
       new jakarta.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
  }
       });
    try {
  //create a MimeMessage object
  Message message = new MimeMessage(session);
  //set From email field
  message.setFrom(new InternetAddress(from));
  //set To email field
  message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
  //set email subject field
  message.setSubject(subject);
  //set the content of the email message
  message.setText(msgBody);
  //send the email message
  Transport.send(message);
  System.out.println("Email Message Sent Successfully");
  return "Mail Sent Successfully...";
    } catch (MessagingException e) {
    
        System.out.println("Error sending email: " + e.getMessage());
        return "Error while Sending Mail";
    }
}


public String sendSimpleMail(String recipient, String msgBody, String subject) {

    try {

      // Creating a simple mail message
      SimpleMailMessage mailMessage = new SimpleMailMessage();

      // Setting up necessary details
      mailMessage.setFrom(sender);
      mailMessage.setTo(recipient);
      mailMessage.setText(msgBody);
      mailMessage.setSubject(subject);

      mailSender.send(mailMessage);
      return "Mail Sent Successfully...";
    }

    // Catch block to handle the exceptions
    catch (Exception e) {
      logger.info("status" + e);
      return "Error while Sending Mail";
    }

  }
public String sendSimpleMailHtml(String recipient, String template, String subject) {
// Try block to check for exceptions
try {
  
    MimeMessage message = mailSender.createMimeMessage();


  message.setSubject(subject);
  MimeMessageHelper helper;
  helper = new MimeMessageHelper(message, true);
  helper.setFrom(sender);
  helper.setTo(recipient);
  helper.setText(template, true);
  logger.info("message" + message);
  // Sending the mail
  mailSender.send(message);
  return "Mail Sent Successfully";
}

// Catch block to handle the exceptions
catch (Exception e) {
  logger.info("status" + e);
  return "Error while Sending Mail";
}
}
}