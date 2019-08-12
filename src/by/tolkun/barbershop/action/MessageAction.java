package by.tolkun.barbershop.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MessageAction extends Action {
    private static Logger logger = LogManager.getLogger(MessageAction.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("message") != null &
                session.getAttribute("redirectUtl") != null) {
            String message = (String) session.getAttribute("message");
            String redirectUrl = (String) session.getAttribute("redirectUrl");
            request.setAttribute("message", message);
            request.setAttribute("redirectUrl", redirectUrl);
            session.removeAttribute("message");
            session.removeAttribute("redirectUrl");
        }
        return new Forward("/message.jsp", false);
    }
}
