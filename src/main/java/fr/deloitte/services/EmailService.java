package fr.deloitte.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class EmailService {

    private JavaMailSender emailSender;

    public EmailService() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.office365.com");
        sender.setPort(587);
        sender.setUsername("zouizza.mohamed@emsi-edu.ma");
        sender.setPassword("Ozulush_4812_U");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        sender.setJavaMailProperties(props);

        this.emailSender = sender;
    }

    public void sendEmail(String recipientEmail, String subject, String text) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(text, true);

        emailSender.send(message);
    }
}
