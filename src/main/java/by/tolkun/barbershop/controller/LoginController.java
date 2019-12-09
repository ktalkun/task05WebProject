package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.entity.MenuItem;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.UserService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {

    private static Map<Role, List<MenuItem>> profileMenu = new ConcurrentHashMap<>();

    static {
//        TODO: user resources
        profileMenu.put(Role.ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.CUSTOMER, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.EMPLOYEE, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.jsp",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.jsp",
                        "History",
                        "fal fa-history"
                )
        )));
    }

    private UserService userService;

    @Autowired
    public LoginController(final UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = AllowPageURL.LOGIN)
    public String showPage() {
        return AllowView.LOGIN;
    }

    @PostMapping(path = AllowPageURL.LOGIN, params = {"login", "password"})
    public String login(@RequestParam Map<String, String> allParams,
                        final HttpServletRequest request,
                        final RedirectAttributes attributes) {
        String login = allParams.get("login");
        String password = allParams.get("password");
        User user = null;
        user = userService.findByLoginAndPassword(login, password);
        String redirectURL;
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("authorizedUser", user);
            session.setAttribute("profileMenu",
                    profileMenu.get(user.getRole()));
            redirectURL = AllowPageURL.INDEX;
        } else {
            attributes.addFlashAttribute("message", "Имя пользователя и пароль не совпадают.");
            attributes.addFlashAttribute("redirectUrl", AllowPageURL.LOGIN);
            redirectURL = AllowPageURL.MESSAGE;
        }
        return "redirect:" + redirectURL;
    }
}
