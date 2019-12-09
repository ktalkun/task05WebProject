package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.url.AllowPageURL;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    @RequestMapping(path = AllowPageURL.LOGOUT)
    public String showPage(final HttpSession session) {
        session.invalidate();
        return "redirect:" + AllowPageURL.LOGIN;
    }
}
