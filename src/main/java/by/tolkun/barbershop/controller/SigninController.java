package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.UserService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Map;

@Controller
public class SigninController {
    private static final Logger LOGGER
            = LogManager.getLogger(SigninController.class);

    private static final String DEFAULT_ADMIN_AVATAR_JPG
            = "/upload/admin/defaultAvatar.jpg";

    private static final String DEFAULT_AVATAR_JPG = "resources/img/defaultAvatar.jpg";

    private UserService userService;

    @Autowired
    public SigninController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = AllowPageURL.SIGNIN)
    public String showPage() {
        return AllowView.SIGNIN;
    }

    @PostMapping(path = AllowPageURL.SIGNIN, params = {"surname", "name",
            "patronymic", "email", "phone", "login", "password",
            "repeatPassword"})
    public String signin(@RequestParam Map<String, String> allParams,
                         final HttpServletRequest request,
                         final RedirectAttributes attributes) {
        String surname = allParams.get("surname");
        String name = allParams.get("name");
        String patronymic = allParams.get("patronymic");
        String email = allParams.get("email");
        String phone = allParams.get("phone");
        String login = allParams.get("login");
        String password = allParams.get("password");
        String repeatPassword = allParams.get("repeatPassword");
        LOGGER.debug("Try to register: surname={}, name={}, patronymic={}, email={}, phone={}, login={}",
                surname, name, patronymic, email, phone, login);
        String message;
        String redirectUrl = AllowPageURL.SIGNIN;
        if (password.equals(repeatPassword)) {
            User user = null;
            try {
                user = userService.findByLogin(login);
            } catch (LogicException e) {
                LOGGER.error(e);
            }
            if (user != null) {
                message = "User with such login \"" + login + "\" exists.";
                LOGGER.warn(
                        "Attempt to register a user with an existing login={}",
                        login
                );
            } else {
                try {
                    UserBuilder userBuilder = new UserBuilder();
                    File file = new File(request.getContextPath()
                            + DEFAULT_ADMIN_AVATAR_JPG);
                    String defaultAvatarPath;
                    if (file.exists()) {
                        defaultAvatarPath = DEFAULT_ADMIN_AVATAR_JPG;
                    } else {
                        defaultAvatarPath = DEFAULT_AVATAR_JPG;
                    }
                    userBuilder
                            .surname(surname)
                            .name(name)
                            .patronymic(patronymic)
                            .email(email)
                            .phone(Integer.parseInt(phone))
                            .login(login)
                            .password(password)
                            .imagePath(defaultAvatarPath)
                            .role(Role.CUSTOMER);
                    user = userBuilder.build();
                    userService.save(user);
                    message = "Registration successful.";
                    redirectUrl = AllowPageURL.LOGIN;
                    LOGGER.info("New user={} was saved.", user);
                } catch (LogicException e) {
                    LOGGER.error(
                            "User with such email or phone exists: {}",
                            e
                    );
                    message = "User with such email or phone exists.";
                }
            }
        } else {
            message = "Passwords are not match, try again.";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl", redirectUrl);
        return "redirect:" + AllowPageURL.MESSAGE;
    }
}
