package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.mail.Mail;
import by.tolkun.barbershop.url.AllowPageURL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import java.util.Map;

@Controller
public class MainController {

    private static final String RECIPIENT = "tolkun_14@inbox.ru";

    @RequestMapping(path = {AllowPageURL.ROOT})
    public String showPage() {
        return "index";
    }

    @RequestMapping(path = {AllowPageURL.ROOT},
            params = {"name", "email", "title", "description"})
    public String sendEmail(@RequestParam Map<String, String> allParams,
                            final RedirectAttributes attributes) throws MessagingException {
        String name = allParams.get("name");
        String email = allParams.get("email");
        String title = allParams.get("title");
        String description = allParams.get("description");
        Mail mail = new Mail();
        String message;
        mail.createMessage(RECIPIENT, name, email, title, description);
        mail.sendEmail();
        message = "Email was sent.";
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl", AllowPageURL.ROOT);
        return "redirect:" + AllowPageURL.MESSAGE;
    }
}
