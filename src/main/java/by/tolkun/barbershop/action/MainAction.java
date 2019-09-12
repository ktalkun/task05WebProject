package by.tolkun.barbershop.action;

import by.tolkun.barbershop.mail.Mail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends Action {
    private static final Logger LOGGER
            = LogManager.getLogger(MainAction.class);

    private static final String RECIPIENT = "tolkun_14@inbox.ru";

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        String message = null;
        String redirectUrl = "/index.jsp";
        Forward forward = new Forward("/index.jsp", false);
        if(name != null && !name.isEmpty() &&
                email != null && !email.isEmpty() &&
                title != null && !title.isEmpty() &&
                description != null && !description.isEmpty()){
            Mail mail = new Mail();
            try {
                mail.createMessage(RECIPIENT, name, email, title, description);
                mail.sendEmail();
                message = "Email was sent.";
            } catch (MessagingException e) {
                LOGGER.error("Email of \"{}\" wasn't sent.", name);
                message = "Email wasn't sent.";
            }
        }
        if (request.getParameter("isSent") != null) {
            forward.setValue("/message.jsp");
            forward.setRedirect(true);
        }
        request
                .getSession()
                .setAttribute("message", message);
        request
                .getSession()
                .setAttribute("redirectUrl", redirectUrl);
        return forward;
    }


}
