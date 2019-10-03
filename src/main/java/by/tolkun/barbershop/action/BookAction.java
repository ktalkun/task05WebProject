package by.tolkun.barbershop.action;

import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BookAction extends Action {

    private static final Logger LOGGER = LogManager.getLogger(BookAction.class);

    private static final String MESSAGE_PAGE_URL = "/message.jsp";

    public BookAction(){
        allowRoles.add(Role.CUSTOMER);
        allowRoles.add(Role.EMPLOYEE);
        allowRoles.add(Role.ADMINISTRATOR);
    }

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
        String message = null;
        String redirectUrl = "/book.jsp";
        Forward forward = new Forward("/book.jsp", false);

        String offerIdParam = request.getParameter("offer");
        String employeeIdParam = request.getParameter("employee");
        String dateParam = request.getParameter("date");
        String timeParam = request.getParameter("time");

        OfferService offerService = ServiceFactory
                .getInstance()
                .getOfferService();
        EmployeeService employeeService = ServiceFactory
                .getInstance()
                .getEmployeeService();
        ReservationService reservationService = ServiceFactory
                .getInstance()
                .getReservationService();
        if (offerIdParam != null && !offerIdParam.isEmpty() &&
                employeeIdParam != null && !employeeIdParam.isEmpty() &&
                dateParam != null && !dateParam.isEmpty()) {
            int offerId = Integer.parseInt(offerIdParam);
            int employeeId = Integer.parseInt(employeeIdParam);
            Date date = null;
            try {
                date = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                        .parse(dateParam + " " + timeParam).getTime());
                LOGGER.debug("dateParam: {}, timeParam: {}", dateParam, timeParam);
                LOGGER.debug("Java Date representation: {}", date);
            } catch (ParseException e) {
                LOGGER.error(e);
                message = "Wrong date format.";
            }
//            Get entities from database by ids
            User user = ((User) request
                    .getSession()
                    .getAttribute("authorizedUser"));
            Offer offer = null;
            try {
                offer = offerService.findByIdentity(offerId);
            } catch (LogicException e) {
                LOGGER.error(e);
                message = "Selected offer does not exist.";
            }
            Employee employee = null;
            try {
                employee = employeeService.findByIdentity(employeeId);
            } catch (LogicException e) {
                LOGGER.error(e);
                message = "Selected employee does not exist.";
            }

            ReservationBuilder reservationBuilder = new ReservationBuilder();
            reservationBuilder
                    .offer(offer)
                    .customer(user)
                    .employee(employee)
                    .date(date);
            try {
                reservationService.save(reservationBuilder.build());
                LOGGER.debug("Got offerId: {}, customerId: {}, employeeId: {}, date: {}",
                        offer.getId(), user.getId(), employee.getId(), date);
                LOGGER.info("Registration was saved.");
                message = "Registration was saved.";
                redirectUrl="/index.jsp";
            } catch (LogicException e) {
                LOGGER.error(e);
                message = "Registration wasn't saved.";
            }
        }


        List<Offer> offers = new ArrayList<>();
        try {
            offers.addAll(offerService.findAll());
        } catch (LogicException e) {
            LOGGER.error("Offers can't be read: {}", e);
        }
        request.setAttribute("offers", offers);

        List<Employee> employees = new ArrayList<>();
        try {
            employees.addAll(employeeService.findAll());
        } catch (LogicException e) {
            LOGGER.error("Employees can't be read: {}", e);
        }
        request.setAttribute("employees", employees);

        if (request.getParameter("isSent") != null) {
            forward.setValue(MESSAGE_PAGE_URL);
            forward.setRedirect(true);
        }
        request
                .getSession()
                .setAttribute("message", message);
        request
                .getSession()
                .setAttribute("redirectUrl", redirectUrl);
        return forward;
    }
}
