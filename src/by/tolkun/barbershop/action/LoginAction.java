package by.tolkun.barbershop.action;

import entity.User;
import exception.LogicException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ServiceFactory;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(LoginAction.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        if (login != null && password != null) {
            UserService userService = ServiceFactory
                    .getInstance()
                    .getUserService();
            User user = null;
            try {
                user = userService.findByLoginAndPassword(login, password);
            } catch (LogicException e) {
                LOGGER.error(e);
            }
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                LOGGER.info(
                        "User {} logged in from {} ({}:{})",
                        login,
                        request.getRemoteAddr(),
                        request.getRemoteHost(),
                        request.getRemotePort()
                );
                return new Forward("/index.html");
            }
//                TODO: replace to resource bundle
            request.setAttribute(
                    "loginMessage",
                    "Имя пользователя и пароль не совпадают."
            );
            LOGGER.info(
                    "User {} unsuccessfully tried to log in from {} ({}:{})",
                    login,
                    request.getRemoteAddr(),
                    request.getRemoteHost(),
                    request.getRemotePort()
            );
        }
        return new Forward(getName() + ".html");
    }
}
