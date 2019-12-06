package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class BookController {

    private static final Logger LOGGER
            = LogManager.getLogger(BookController.class);

    private OfferService offerService;

    private EmployeeService employeeService;

    private ReservationService reservationService;

    @Autowired
    public BookController(OfferService offerService,
                          EmployeeService employeeService,
                          ReservationService reservationService) {
        this.offerService = offerService;
        this.employeeService = employeeService;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = AllowPageURL.BOOK)
    public String showPage(Model model) {
        try {
            model.addAttribute("offers", offerService.findAll());
        } catch (LogicException e) {
            LOGGER.error("Offers can't be read: {}", e);
            model.addAttribute("offers", new ArrayList<>());
        }

        try {
            model.addAttribute("employees", employeeService.findAll());
        } catch (LogicException e) {
            LOGGER.error("Employees can't be read: {}", e);
            model.addAttribute("employees", new ArrayList<>());
        }

        return AllowView.BOOK;
    }

    @PostMapping(path = AllowPageURL.BOOK,
            params = {"offer", "employee", "date", "time"})
    public String makeReservation(@RequestParam Map<String, String> allParams,
                                  HttpSession session,
                                  RedirectAttributes attributes) {
        String message;
        int offerId = Integer.parseInt(allParams.get("offer"));
        int employeeId = Integer.parseInt(allParams.get("employee"));
        String dateParam = allParams.get("date");
        String timeParam = allParams.get("time");

        Date date;
        try {
            java.util.Date dateTwelveTime = new java.util.Date(
                    new SimpleDateFormat("dd/MM/yyyy HH:mm a")
                            .parse(dateParam + " " + timeParam)
                            .getTime());
            date = new Date(new java.util.Date(
                    new SimpleDateFormat("dd/MM/yyyy HH:mm")
                            .format(dateTwelveTime)).getTime());

            User user = (User) session.getAttribute("authorizedUser");

            Offer offer;
            offer = offerService.findByIdentity(offerId);

            Employee employee;
            employee = employeeService.findByIdentity(employeeId);

            ReservationBuilder reservationBuilder = new ReservationBuilder();
            reservationBuilder
                    .offer(offer)
                    .customer(user)
                    .employee(employee)
                    .date(date);
            LOGGER.debug(offerId + " " + employeeId + " " + date);
            reservationService.save(reservationBuilder.build());
            LOGGER.debug("Got offerId: {}, customerId: {}, employeeId: {}, date: {}",
                    offer.getId(), user.getId(), employee.getId(), date);
            LOGGER.info("Reservation was saved.");
            message = "Reservation was saved.";
        } catch (LogicException e) {
            LOGGER.error("Reservation wasn't saved.", e);
            message = "Reservation wasn't saved. Selected offer or employee does not exist.";
        } catch (ParseException e) {
            LOGGER.error(e);
            message = "Wrong date format.";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl", AllowPageURL.ROOT);
        return "redirect:" + AllowPageURL.MESSAGE;
    }
}
