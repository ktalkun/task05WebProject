package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction extends Action {
    private static Logger logger = LogManager.getLogger(LogoutAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request
                .getSession(false)
                .getAttribute("authorizedUser");
        logger.info(String.format("user \"%s\" is logged out", user.getLogin()));
        request.getSession(false).invalidate();
        return new Action.Forward("/login.jsp");
    }
}
