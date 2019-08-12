package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.ServiceFactory;
import by.tolkun.barbershop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

@MultipartConfig
public class ProfileEditAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(ProfileEditAction.class);

    private final String NAME_UPLOAD_DIRECTORY = "upload/avatars";

    private static final String MESSAGE_PAGE_URL = "/message.jsp";

    @Override
    public Forward execute(final HttpServletRequest request,
                           final HttpServletResponse response) {

        User user = (User) request
                .getSession(false)
                .getAttribute("authorizedUser");

        String message = null;
        String redirectUrl= "/profile/edit.jsp";
        Forward forward = new Forward("/profile/edit.jsp", false);

        Part avatar = null;
        try {
            avatar = request.getPart("avatarImage");
        } catch (IOException | ServletException e) {
            LOGGER.error(e);
        }
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String patronymic = request.getParameter("patronymic");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

//        Handling update user provile event
        UserService userService = ServiceFactory
                .getInstance()
                .getUserService();
        if (name != null && !name.isEmpty()) {
            user.setName(name);
        }
        if (surname != null && !surname.isEmpty()) {
            user.setSurname(surname);
        }
        if (patronymic != null && !patronymic.isEmpty()) {
            user.setPatronymic(patronymic);
        }
        if (phone != null && !phone.isEmpty()) {
            user.setPhone(Integer.parseInt(phone));
        }
        if (email != null && !email.isEmpty()) {
            user.setEmail(email);
        }
        if (avatar != null) {
            uploadAvatar(request, user);
        }
        try {
            userService.save(user);
            message = "Profile data was updated.";

        } catch (LogicException e) {
            LOGGER.error(e);
            message = "Update is not possible with this data.";

        }
        if (request.getParameter("isSent") != null) {
            forward.setValue(MESSAGE_PAGE_URL);
            forward.setRedirect(true);
        }

//        Handling delete reservation event
        ReservationService reservationService = ServiceFactory
                .getInstance()
                .getReservationService();
        String reservationDeleteParam
                = request.getParameter("reservation-delete");
        if (reservationDeleteParam != null) {
            int reservationId = Integer.parseInt(reservationDeleteParam);
            try {
                reservationService.delete(reservationId);
                message = "Reservation was deleted.";
                LOGGER.info("Reservation with id = {} deleted.", reservationId);
            } catch (LogicException e) {
                LOGGER.error("Wrong reservation id \"{}\"", e);
                message = "Reservation cannot be deleted.";
            }
            forward.setValue(MESSAGE_PAGE_URL);
            forward.setRedirect(true);
        }
        try {
            List<Reservation> reservations
                    = reservationService.findByCustomer(user.getId());
            request.setAttribute("userReservations", reservations);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        request
                .getSession()
                .setAttribute("message", message);
        request
                .getSession()
                .setAttribute("redirectUrl", redirectUrl);
        return forward;
    }

    public void uploadAvatar(final HttpServletRequest request,
                             final User user) {
        String uploadPath = request.getServletContext().getRealPath("")
                + NAME_UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        try {
            Part part = request.getPart("avatarImage");
            String submittedFileName = part.getSubmittedFileName();
            if (submittedFileName.equals("")) {
                return;
            }
            String fileName = user.getLogin() +
                    submittedFileName
                            .substring(submittedFileName.indexOf("."));
            File file = new File(uploadPath);
            String filePath = uploadPath + File.separator + fileName;
            part.write(filePath);
            user.setImagePath(File.separator +
                    NAME_UPLOAD_DIRECTORY + File.separator + fileName);
        } catch (IOException | ServletException e) {
            LOGGER.error(e);
        }
    }
}
