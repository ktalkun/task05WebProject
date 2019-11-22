package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.UserService;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
public class ProfileController {

    private static final Logger LOGGER
            = LogManager.getLogger(ProfileController.class);

    private static final String NAME_UPLOAD_DIRECTORY = "resources/upload/avatars";

    private UserService userService;

    private ReservationService reservationService;

    @Autowired
    public ProfileController(final UserService userService,
                             final ReservationService reservationService) {
        this.userService = userService;
        this.reservationService = reservationService;
    }

    @RequestMapping(path = AllowPageURL.PROFILE_EDIT)
    public String showEditPage(final Model model,
                               final HttpSession session) {
        try {
            List<Reservation> reservations = reservationService.findByCustomer(
                    ((User) session.getAttribute("authorizedUser")).getId());
            model.addAttribute("userReservations", reservations);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return AllowView.PROFILE_EDIT;
    }

    @PostMapping(path = AllowPageURL.PROFILE_EDIT, params = {"name",
            "surname", "patronymic", "phone", "email"})
    public String updateUser(@RequestParam Map<String, String> allParams,
                             @RequestParam(name = "avatarImage",
                                     required = false) MultipartFile file,
                             final RedirectAttributes attributes,
                             final HttpServletRequest request) {
        String message;
        String name = allParams.get("name");
        String surname = allParams.get("surname");
        String patronymic = allParams.get("patronymic");
        String phone = allParams.get("phone");
        String email = allParams.get("email");
        User user = (User) request
                .getSession(false)
                .getAttribute("authorizedUser");
        LOGGER.debug("Got avatar: {},  name: {}, surname: {}, patronymic: {}, phone: {}, email: {}",
                file != null ? file.getOriginalFilename() : null, name, surname, patronymic, phone,
                email);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPhone(Integer.parseInt(phone));
        user.setEmail(email);
        if (file != null) {
            uploadAvatar(file,
                    new File(request
                            .getServletContext()
                            .getRealPath(NAME_UPLOAD_DIRECTORY)), user);
        }
        try {
            userService.save(user);
            message = "Profile data was updated.";

        } catch (LogicException e) {
            LOGGER.error(e);
            message = "Update is not possible with this data.";

        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl",
                AllowPageURL.PROFILE_EDIT);
        return "redirect:" + AllowPageURL.MESSAGE;
    }

    @PostMapping(path = AllowPageURL.PROFILE_EDIT,
            params = {"reservation-id"})
    public String removeReservation(@RequestParam(name = "reservation-id")
                                            int reservationId,
                                    final RedirectAttributes attributes) {
        String message;
        try {
            reservationService.delete(reservationId);
            message = "Reservation was deleted.";
            LOGGER.info("Reservation with id = {} deleted.",
                    reservationId);
        } catch (LogicException e) {
            LOGGER.error("Wrong reservation id \"{}\"", e);
            message = "Reservation cannot be deleted.";
        }
        attributes.addFlashAttribute("message", message);
        attributes.addFlashAttribute("redirectUrl",
                AllowPageURL.PROFILE_EDIT);
        return "redirect:" + AllowPageURL.MESSAGE;
    }

    private void uploadAvatar(final MultipartFile file,
                              final File uploadDir,
                              final User user) {
        try {
            if (!uploadDir.exists() && !uploadDir.mkdir()) {
                throw new IOException("Directory wasn't created.");
            }
            String submittedFileName = file.getOriginalFilename();
            if (Objects.equals(submittedFileName, "")) {
                return;
            }
            String fileName = user.getLogin() +
                    submittedFileName.substring(Objects
                            .requireNonNull(submittedFileName).indexOf('.'));
            file.transferTo(new File(uploadDir.getPath()
                    + File.separator + fileName));
            user.setImagePath(NAME_UPLOAD_DIRECTORY + File.separator + fileName);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
