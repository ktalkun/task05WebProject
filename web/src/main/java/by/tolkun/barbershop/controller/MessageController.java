package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MessageController {

    @RequestMapping(path = AllowPageURL.MESSAGE)
    public String shopPage(@ModelAttribute("message") String message,
                           @ModelAttribute("redirectUrl") String redirectUrl,
                           Model model, HttpServletRequest request) {
        if (message != null && !message.isEmpty()
                && redirectUrl != null && !redirectUrl.isEmpty()) {
            model.addAttribute("message", message);
            model.addAttribute("redirectUrl", redirectUrl);
        } else {
            model.addAttribute("message", request.getAttribute("message"));
            model.addAttribute("redirectUrl", request.getAttribute("redirectUrl"));
        }
        return AllowView.MESSAGE;
    }
}
