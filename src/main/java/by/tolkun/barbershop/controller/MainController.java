package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.mail.Mail;
import by.tolkun.barbershop.url.AllowPageURL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.Map;

@Controller
public class MainController {

    private static final Logger LOGGER
            = LogManager.getLogger(MainController.class);

    private static final String RECIPIENT = "tolkun_14@inbox.ru";

    @RequestMapping(path = {AllowPageURL.ROOT, AllowPageURL.INDEX})
    public String showPage() {
        return "index";
    }

    @RequestMapping(path = {AllowPageURL.ROOT, AllowPageURL.INDEX},
            params = {"name", "email", "title", "description"})
    public String sendEmail(@RequestParam Map<String,String> allParams,
                            final RedirectAttributes attributes) {
        String name = allParams.get("name");
        String email = allParams.get("email");
        String title = allParams.get("title");
        String description = allParams.get("description");
        Mail mail = new Mail();
        LOGGER.error(name + email + title + description);
        String message;
        String redirectUrl = "index.html";
        try {
            mail.createMessage(RECIPIENT, name, email, title, description);
            mail.sendEmail();
            message = "Email was sent.";
        } catch (MessagingException e) {
            LOGGER.error("Email of \"{}\" wasn't sent.", name);
            message = "Email wasn't sent.";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl", redirectUrl);
        return "redirect:" + AllowPageURL.MESSAGE;
    }
}
