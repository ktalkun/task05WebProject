package by.tolkun.barbershop.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffAction extends Action {
    private static Logger logger = LogManager.getLogger(StaffAction.class);
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/staff.jsp", false);
    }
}
