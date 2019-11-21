package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.url.AllowPageURL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LogoutController {

    private static final Logger LOGGER
            = LogManager.getLogger(LoginController.class);

    @RequestMapping(path = AllowPageURL.LOGOUT)
    public String showPage(final HttpSession session) {
        LOGGER.info(String.format("user \"%s\" is logged out",
                ((User) session.getAttribute("authorizedUser")).getLogin()));
        session.invalidate();
        return "redirect:" + AllowPageURL.LOGIN;
    }
}
