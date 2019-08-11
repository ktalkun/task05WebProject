package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.ServiceFactory;
import by.tolkun.barbershop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class BarberAction extends Action {

    private static final Logger LOGGER = LogManager.getLogger(BarberAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeService employeeService = ServiceFactory
                .getInstance()
                .getEmployeeService();
        try {
            List<Employee> employees = employeeService.findAll();
            request.setAttribute("employees", employees);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return new Forward("/barber.jsp", false);
    }
}
