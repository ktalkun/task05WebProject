package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BarberController {

    private static final Logger LOGGER
            = LogManager.getLogger(BarberController.class);
    private static final int NOTE_NUMBER_ON_PAGE = 10;
    private EmployeeService employeeService;

    @Autowired
    public BarberController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(path = AllowPageURL.BARBER)
    public String showPage(@RequestParam(name = "page", required = false)
                                       Integer page, Model model) {
        if (page == null) {
            page = 1;
        }
        List<Employee> employees;
        try {
            employees = employeeService
                    .findAll((page - 1) * NOTE_NUMBER_ON_PAGE,
                            NOTE_NUMBER_ON_PAGE);

            int noteNumber = employeeService.noteNumber();
            int pageNumber
                    = (int) Math.ceil(noteNumber * 1.0 / NOTE_NUMBER_ON_PAGE);
            model.addAttribute("employees", employees);
            model.addAttribute("pageNumber", pageNumber);
            model.addAttribute("currentPage", page);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return AllowView.BARBER;
    }
}
