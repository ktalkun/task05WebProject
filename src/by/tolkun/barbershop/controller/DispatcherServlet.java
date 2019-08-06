package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.action.Action;
import by.tolkun.barbershop.dao.pool.ConnectionPool;
import by.tolkun.barbershop.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;

public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(DispatcherServlet.class);
    //    TODO: replace to resource file
    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://db:3306/barbershop_db?useUnicode=true&characterEncoding=UTF-8";
    public static final String DB_USER = "barbershop_user";
    public static final String DB_PASSWORD = "barber";
    public static final int DB_POOL_START_SIZE = 10;
    public static final int DB_POOL_MAX_SIZE = 1000;
    public static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public void init() {
        try {
//            TODO: delete generator test data
//            Gener.main(null);
            ConnectionPool.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);
        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
            destroy();
        }
    }

    public void doGet(final HttpServletRequest request,
                      final HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(final HttpServletRequest request,
                       final HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

//    TODO: divide to two methods
    private void process(
            final HttpServletRequest request,
            final HttpServletResponse response)
            throws IOException, ServletException {
        Action action = (Action) request.getAttribute("action");
        HttpSession session = request.getSession(false);
//        TODO: goal of this code ?
//        if(session != null){
//            Map<String, Object> attributes = (Map<String, Object>) session.getAttribute("redirectedData");
//            if(attributes != null){
//                for(String key: attributes.keySet()){
//                    request.setAttribute(key, attributes.get(key));
//                }
//                session.removeAttribute("redirectedData");
//            }
//        }
        Action.Forward forward = action.execute(request, response);
//        if(session != null && forward != null && !forward.getAttributes().isEmpty()){
//            session.setAttribute("redirectedData", forward.getAttributes());
//        }
        if(forward.isRedirect()){
            String redirectURL = request.getContextPath() + forward.getValue();
            response.sendRedirect(redirectURL);
        } else {
            String jspPage = "/jsp" + forward.getValue();
            getServletContext().getRequestDispatcher(jspPage).forward(request, response);
        }

//            Получаем action manager с установленной фабрикой сервисов
//            Action.Forward forward = actionManager.execute(action, request, response);
//            if (session != null && forward != null && !forward.getAttributes().isEmpty()) {
//                session.setAttribute("redirectedData", forward.getAttributes());
//            }
//            String requestedUri = request.getRequestURI();
//            if (forward != null && forward.isRedirect()) {
//                String redirectedUri = request.getContextPath() + forward.getValue();
//                LOGGER.debug(String.format("Request for URI \"%s\" id redirected to URI \"%s\"", requestedUri, redirectedUri));
//                response.sendRedirect(redirectedUri);
//            } else {
//                String jspPage;
//                if (forward != null) {
//                    jspPage = forward.getValue();
//                } else {
//                    jspPage = action.getName() + ".jsp";
//                }
//                jspPage = "/WEB-INF/jsp" + jspPage;
//                LOGGER.debug(String.format("Request for URI \"%s\" is forwarded to JSP \"%s\"", requestedUri, jspPage));
//                getServletContext().getRequestDispatcher(jspPage).forward(request, response);
//            }
//        } catch (PersistentException e) {
//            LOGGER.error("It is impossible to process request", e);
//            request.setAttribute("error", "Ошибка обработки данных");
//            getServletContext().getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
//        }








//        String contextPath = request.getContextPath();
//        String URI = request.getRequestURI();
//        int beginAction = URI.lastIndexOf("/");
//        int endAction = URI.lastIndexOf(".");
//        String actionName;
//        if (endAction >= 0) {
//            actionName = URI.substring(beginAction, endAction);
//        } else {
//            actionName = URI.substring(beginAction);
//        }
//        request.getRequestDispatcher("/jsp/" + actionName + ".jsp").forward(request, response);
//        response.sendRedirect("/jsp/service.jsp");
    }
}
