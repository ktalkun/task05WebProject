package by.tolkun.barbershop.mail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
    private static final Logger LOGGER = LogManager.getLogger(Mail.class);

    private static final String PORT = "587";   // gmail's smtp port
    private static final String MAIL_HOST = "smtp.gmail.com";
    private static final String FROM_USER = "tolkun1436";    // just the id alone without @gmail.com
    private static final String FROM_USER_PASSWORD = "hofesa123/";

    private Properties mailProperties;
    private Session mailSession;
    private MimeMessage mailMessage;

    public Mail() {
        mailProperties = System.getProperties();
        mailProperties.put("mail.smtp.port", PORT);
        mailProperties.put("mail.smtp.auth", "true");
        mailProperties.put("mail.smtp.starttls.enable", "true");
    }

    public void createMessage(final String to,
                              final String name,
                              final String email,
                              final String title,
                              final String description)
            throws MessagingException {
        mailSession
                = Session.getDefaultInstance(mailProperties, null);
        mailMessage = new MimeMessage(mailSession);
        mailMessage.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(to)
        );
        mailMessage.setSubject(title);
        String mailBody =
                "Name: " + name + " " +
                "Email: " + email + "   " +
                "Description: " + description;
        mailMessage.setContent(mailBody, "text/html; charset=UTF-8");  // for a html email
    }

    public void sendEmail() throws MessagingException {
        Transport transport = mailSession.getTransport("smtp");
        transport.connect(MAIL_HOST, FROM_USER, FROM_USER_PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
        LOGGER.debug("Email sent successfully.");
    }

}