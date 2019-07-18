package by.tolkun.barbershop.controller;

import action.Action;
import action.MainAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActionFromURIFilter implements Filter {
    private static final  Logger LOGGER = LogManager.getLogger(ActionFromURIFilter.class);
    private static Map<String, Class<? extends Action>> actions
            = new ConcurrentHashMap<>();

//    TODO: use objects instead of classes like DAOFactory
    static {
        actions.put("/", MainAction.class);
    }

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest
                = (HttpServletRequest) servletRequest;
        String contextPath = httpServletRequest.getContextPath();
        String URI = httpServletRequest.getRequestURI();
        int beginAction = contextPath.length();
        int endAction = URI.lastIndexOf(".");
        String actionName;
        if (endAction >= 0) {
            actionName = URI.substring(beginAction, endAction);
        } else {
            actionName = URI.substring(beginAction);
        }
        Class<? extends Action> actionClass = actions.get(actionName);
        try {
            Action action = actionClass.newInstance();
            action.setName(actionName);
            httpServletRequest.setAttribute("by/tolkun/barbershop/action", action);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (InstantiationException
                | IllegalAccessException
                | NullPointerException e) {
            LOGGER.error("Imposible to create action object ", e);
//            TODO: use multilanguages
            httpServletRequest.setAttribute("error", String.format(
                    "Запрощенный адрес %s не может быть обработан сервером.",
                    URI
                    )
            );
//            TODO: make more transparent url
            httpServletRequest
                    .getRequestDispatcher("WEB-INF/jsp/error.jsp")
                    .forward(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
