package by.tolkun.barbershop.filter;

import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.role.AllowRole;
import by.tolkun.barbershop.url.AllowPageURL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

public class SecurityFilter extends GenericFilterBean {
    private static final String MESSAGE_PAGE_URL = "/message.html";
    private static Logger LOGGER = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain chain)
            throws IOException, ServletException {
        LOGGER.debug("Security filter");
        if (request instanceof HttpServletRequest
                && response instanceof HttpServletResponse) {
            String message = null;
            String redirectUrl = AllowPageURL.LOGIN;

            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpRequest.setAttribute("message",
                    httpRequest.getSession().getAttribute("message"));
            httpRequest.setAttribute("redirectUrl",
                    httpRequest.getSession().getAttribute("redirectUrl"));

            String currentPageURL
                    = getCurrentRelativePageURL(httpRequest);
            Set<Role> allowRoles = AllowRole.getAllowRolesByURL(currentPageURL);
            String userName = "unauthorized user";
            HttpSession session = httpRequest.getSession(false);
            User user = null;
            if (session != null) {
                user = (User) session.getAttribute("authorizedUser");
            }
            LOGGER.debug("Current page url: {}", currentPageURL);
            LOGGER.debug("Allow roles: {}", allowRoles);
            boolean canExecute = allowRoles == null
                    || allowRoles.contains(Role.GUEST);
            if (user != null) {
                userName = user.getLogin();
                canExecute = canExecute || allowRoles.contains(user.getRole());
            }
            LOGGER.debug("Can execute: {}", canExecute);
            if (canExecute) {
                chain.doFilter(request, response);
                LOGGER.debug("After chain dofilter");
            } else {
                LOGGER.info("Trying of \"{}\" access to forbidden resource \"{}\"",
                        userName, currentPageURL);
                message = "You can't get access to this page as unauthorized user. Log in or sign in please.";
                httpRequest
                        .getSession()
                        .setAttribute("message", message);
                httpRequest
                        .getSession()
                        .setAttribute("redirectUrl", redirectUrl);
                httpResponse.sendRedirect(httpRequest.getContextPath()
                        + MESSAGE_PAGE_URL);
            }
        } else {
            LOGGER.error("It is impossible to use HTTP filter");
            request
                    .getServletContext()
                    .getRequestDispatcher("/jsp/error.jsp")
                    .forward(request, response);
        }
    }

    private String getCurrentRelativePageURL(final HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String URI = request.getRequestURI();
        int beginAction = contextPath.length();
        return URI.substring(beginAction);
    }
}
