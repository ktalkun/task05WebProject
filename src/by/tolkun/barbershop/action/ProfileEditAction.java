package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProfileEditAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(ProfileEditAction.class);

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {
//        TODO: lack of session (may be null)
        User user = (User) request
                .getSession(false)
                .getAttribute("authorizedUser");
        ReservationService reservationService = ServiceFactory
                .getInstance()
                .getReservationService();
        String reservationDeleteParam
                = request.getParameter("reservation-delete");
        if (reservationDeleteParam != null) {
            int reservationId = Integer.parseInt(reservationDeleteParam);
            try {
                reservationService.delete(reservationId);
                LOGGER.info("Reservation with id = {} deleted.", reservationId);
            } catch (LogicException e) {
                LOGGER.error("Wrong reservation id \"{}\"", e);
            }
        }
        try {
            List<Reservation> reservations
                    = reservationService.findByCustomer(user.getId());
            request.setAttribute("userReservations", reservations);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return new Forward("/profile/edit.jsp", false);
    }
}
