package by.tolkun.barbershop.service;

import by.tolkun.barbershop.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final Logger LOGGER
            = LogManager.getLogger(ServiceFactory.class);

    private final UserService userService;
    private final EmployeeService employeeService;
    private final OfferService offerService;
    private final ReviewService reviewService;
    private final ReservationService reservationService;

    private ServiceFactory() {
        userService = new UserServiceImpl();
        employeeService = new EmployeeServiceImpl();
        offerService = new OfferServiceImpl();
        reviewService = new ReviewServiceImpl();
        reservationService = new ReservationServiceImpl();
//        TODO: NULLPOINTEREXC
//        LOGGER.debug("Object of ServiceFactory created.");
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public EmployeeService getEmployeeService() {return employeeService;}

    public OfferService getOfferService() {
        return offerService;
    }

    public ReviewService getReviewService() {
        return reviewService;
    }

    public ReservationService getReservationService() {
        return reservationService;
    }
}
