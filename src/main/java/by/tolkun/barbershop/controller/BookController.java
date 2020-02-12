package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.UserService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Controller
@Secured({"ROLE_CUSTOMER"})
public class BookController {

    private OfferService offerService;

    private UserService userService;

    private EmployeeService employeeService;

    private ReservationService reservationService;

    @Autowired
    public BookController(OfferService offerService,
                          UserService userService,
                          EmployeeService employeeService,
                          ReservationService reservationService) {
        this.offerService = offerService;
        this.userService = userService;
        this.employeeService = employeeService;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = AllowPageURL.BOOK)
    public String showPage(Model model) {
        model.addAttribute("offers", offerService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        return AllowView.BOOK;
    }

    @PostMapping(path = AllowPageURL.BOOK,
            params = {"offer", "employee", "date", "time"})
    public String makeReservation(@RequestParam Map<String, String> allParams,
                                  Principal principal,
                                  RedirectAttributes attributes) throws ParseException {
        String message;
        int offerId = Integer.parseInt(allParams.get("offer"));
        int employeeId = Integer.parseInt(allParams.get("employee"));
        String dateParam = allParams.get("date");
        String timeParam = allParams.get("time");

//        Format of date gotten from page
        DateFormat inputFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm aa");
//        Format of date for database
        DateFormat outputFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
        java.util.Date dateTwelveTime = inputFormat.parse(dateParam + " " + timeParam);
        java.sql.Date date = new java.sql.Date(outputFormat.parse(outputFormat.format(dateTwelveTime)).getTime());

        User user = userService.findByLogin(principal.getName());

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
        reservationService.save(reservationBuilder.build());
        message = "Reservation was saved.";
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl", AllowPageURL.ROOT);
        return "redirect:" + AllowPageURL.MESSAGE;
    }
}
