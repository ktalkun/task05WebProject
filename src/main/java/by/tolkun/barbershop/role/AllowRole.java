package by.tolkun.barbershop.role;

import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.url.AllowPageURL;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public final class AllowRole {
    private static final Map<String, Set<Role>> ALLOW_ROLES
            = new HashMap<>();
    private static final Set<Role> EVERYONE = new HashSet<>(Arrays.asList(
            Role.ADMINISTRATOR,
            Role.DIRECTOR,
            Role.EMPLOYEE,
            Role.CUSTOMER,
            Role.GUEST
    ));

    private static final Set<Role> REGISTERED = new HashSet<>(Arrays.asList(
            Role.ADMINISTRATOR,
            Role.DIRECTOR,
            Role.EMPLOYEE,
            Role.CUSTOMER
    ));

    static {
        ALLOW_ROLES.put(AllowPageURL.MESSAGE, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.ERROR, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.LOGIN,
                new HashSet<>(Collections.singletonList(Role.GUEST)));
        ALLOW_ROLES.put(AllowPageURL.LOGOUT, REGISTERED);
        ALLOW_ROLES.put(AllowPageURL.SIGNIN,
                new HashSet<>(Collections.singletonList(Role.GUEST)));
        ALLOW_ROLES.put(AllowPageURL.ROOT, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.INDEX, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.SERVICE, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.BARBER, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.STAFF, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.ABOUT, EVERYONE);
        ALLOW_ROLES.put(AllowPageURL.BOOK, REGISTERED);
        ALLOW_ROLES.put(AllowPageURL.PROFILE_EDIT, REGISTERED);
    }

    private AllowRole(){}

    public static Set<Role> getAllowRolesByURL(final String url) {
        return ALLOW_ROLES.get(url);
    }

    public static boolean hasAccess(final String url, final Role role) {
        return getAllowRolesByURL(url).contains(role);
    }
}
