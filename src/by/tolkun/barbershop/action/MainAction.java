package by.tolkun.barbershop.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends Action {
    private static final Logger LOGGER
            = LogManager.getLogger(MainAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/index.jsp", false);
    }
}
