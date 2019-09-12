package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BarberAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(BarberAction.class);

    private int noteNumberOnPage = 10;

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeService employeeService = ServiceFactory
                .getInstance()
                .getEmployeeService();
        try {
            int page = 1;
            if (request.getParameter("page") != null) {
                page = Integer.parseInt(request.getParameter("page"));
            }
            List<Employee> employees = employeeService
                    .findAll((page - 1) * noteNumberOnPage,
                            noteNumberOnPage);
            int noteNumber = employeeService.noteNumber();
            int pageNumber
                    = (int) Math.ceil(noteNumber * 1.0 / noteNumberOnPage);

            request.setAttribute("employees", employees);
            request.setAttribute("pageNumber", pageNumber);
            request.setAttribute("currentPage", page);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return new Forward("/barber.jsp", false);
    }
}
