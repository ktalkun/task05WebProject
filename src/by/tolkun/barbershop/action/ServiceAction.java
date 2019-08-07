package by.tolkun.barbershop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServiceAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/service.jsp", false);
    }
}
