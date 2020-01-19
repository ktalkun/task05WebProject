package by.tolkun.barbershop.filter;

import by.tolkun.barbershop.entity.MenuItem;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.url.AllowPageURL;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class MenuInitFilter extends GenericFilterBean {

    private static Map<Role, List<MenuItem>> profileMenu
            = new ConcurrentHashMap<>();

    static {
//        TODO: user resources
        profileMenu.put(Role.ROLE_ADMINISTRATOR, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.html",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.html",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.ROLE_CUSTOMER, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.html",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.html",
                        "History",
                        "fal fa-history"
                )
        )));
        profileMenu.put(Role.ROLE_EMPLOYEE, new ArrayList<>(Arrays.asList(
                new MenuItem(
                        AllowPageURL.PROFILE_EDIT,
                        "Profile",
                        "fal fa-shopping-bag"
                ),
                new MenuItem(
                        "profile/reviews.html",
                        "Reviews",
                        "fal fa-comment-alt"
                ),
                new MenuItem(
                        "profile/history.html",
                        "History",
                        "fal fa-history"
                )
        )));
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {
        Authentication auth = SecurityContextHolder
                .getContext()
                .getAuthentication();
        if (auth != null && auth.getPrincipal() != null) {
            User authenticatedUser = (User) auth.getPrincipal();
            request.setAttribute("profileMenu", profileMenu
                    .get(authenticatedUser.getRole()));
        }
        filterChain.doFilter(request, response);
    }
}
