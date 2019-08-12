package by.tolkun.barbershop.action;

import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.ServiceFactory;
import by.tolkun.barbershop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

public class SigninAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(SigninAction.class);

    private final String defaultAvatarPathByAdmin
            = "upload/admin/defaultAvatar.jpg";

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String patronymic = request.getParameter("patronymic");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String message = null;
        Forward forward = new Forward("/signin.jsp", false);
        request
                .getSession()
                .removeAttribute("message");
        if (surname != null && !surname.isEmpty()
                && name != null && !name.isEmpty()
                && patronymic != null && !patronymic.isEmpty()
                && email != null && !email.isEmpty()
                && phone != null && !phone.isEmpty()
                && login != null && !login.isEmpty()
                && password != null && !password.isEmpty()
                && repeatPassword != null && !repeatPassword.isEmpty()) {
            if (password.equals(repeatPassword)) {
                UserService userService = ServiceFactory
                        .getInstance()
                        .getUserService();

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
                                + defaultAvatarPathByAdmin);
                        String defaultAvatarPath;
                        if (file.exists()) {
                            defaultAvatarPath = defaultAvatarPathByAdmin;
                        } else {
                            defaultAvatarPath = "img/defaultAvatar.jpg";
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
                        LOGGER.info("New user={} was saved.", user);
                    } catch (LogicException e) {
                        LOGGER.error(
                                "User with such email or phone exists: {}",
                                e
                        );
                        message = "User with such email or phone exists.";
                    }
                    message = "Registration successful.";
                    forward = new Forward("/login.jsp");
                    request
                            .getSession()
                            .setAttribute("message", message);
                }
            } else {
                message = "Passwords are not match, try again.";
            }
        } else if (request.getParameter("isSent") != null) {
            message = "There are blank required fields";
        }
        request.setAttribute("message", message);
        return forward;
    }
}
