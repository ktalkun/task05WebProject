package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping(path = AllowPageURL.LOGIN)
    public String showPage() {
        return AllowView.LOGIN;
    }

}
