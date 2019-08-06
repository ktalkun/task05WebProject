package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.ServiceFactory;
import by.tolkun.barbershop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(LoginAction.class);


    private static Map<Role, List<MenuItem>> profileMenu = new ConcurrentHashMap<>();

    static {
//        TODO: user resources
        profileMenu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        "profile/edit.jsp",
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.CUSTOMER, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        "profile/edit.jsp",
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.EMPLOYEE, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        "profile/edit.jsp",
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
    }

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
                LOGGER.debug(user);
            } catch (LogicException e) {
                LOGGER.error(e);
            }
            if (user != null) {
                HttpSession session = request.getSession();
                session.setAttribute("authorizedUser", user);
                session.setAttribute("profileMenu",
                        profileMenu.get(user.getRole()));
                LOGGER.info(
                        "User {} logged in from {} ({}:{})",
                        login,
                        request.getRemoteAddr(),
                        request.getRemoteHost(),
                        request.getRemotePort()
                );
                return new Forward("/index.jsp");
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
        return new Forward("/login.jsp", false);
    }
}
