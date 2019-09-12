package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.action.Action;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter implements Filter {
    private static Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    private static final String MESSAGE_PAGE_URL = "/message.jsp";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {
            String message = null;
            String redirectUrl = "/book.jsp";
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            Action action = (Action) httpRequest.getAttribute("action");
            Set<Role> allowRoles = action.getAllowRoles();
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
            }
            LOGGER.debug(allowRoles);
            boolean canExecute = allowRoles.size() == 0;
            if (user != null) {
                userName = user.getLogin();
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }
            LOGGER.debug("after check user {}", canExecute);
            if (canExecute) {
                chain.doFilter(request, response);
                LOGGER.debug("after chain dofilter");
            } else {
                LOGGER.info("Trying of \"{}\" access to forbidden resource \"{}\"",
                        userName, action.getName());
                message = "You can't get access to this page as unauthorized user. Log in or sign in please.";
                ((HttpServletRequest) request).getSession().setAttribute("redirectUrl", "/login.jsp");
                ((HttpServletRequest) request).getSession().setAttribute("message", message);
                httpRequest
                        .getSession()
                        .setAttribute("message", message);
                httpRequest
                        .getSession()
                        .setAttribute("redirectUrl", redirectUrl);
                httpResponse.sendRedirect(httpRequest.getContextPath() + MESSAGE_PAGE_URL);
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request.getServletContext().getRequestDispatcher("/jsp/error.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }
}
