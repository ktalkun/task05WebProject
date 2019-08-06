package by.tolkun.barbershop.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction extends Action {
    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        return new Forward("/index.jsp", false);
    }
}
