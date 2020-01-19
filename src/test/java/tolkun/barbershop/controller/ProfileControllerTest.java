package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.config.SecurityConfig;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.ProfileController;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.UserService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class ProfileControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReservationService reservationServiceMock;

    @Mock
    private Principal principalMock;

    @Mock
    private UserService userServiceMock;

    @InjectMocks
    private ProfileController profileController;

    private Authentication authentication;

    private User authenticatedUserTest;

    private Reservation reservationTest;

    @Before
    public void setUp() {
        authenticatedUserTest = new UserBuilder()
                .id(1)
                .login("TestLogin")
                .password("TestPassword")
                .name("TestName")
                .surname("TestSurname")
                .patronymic("TestPatronymic")
                .email("testemail@email.com")
                .phone(123456789)
                .imagePath("TestImagePath")
                .role(Role.ROLE_EMPLOYEE)
                .build();

        reservationTest = new ReservationBuilder()
                .id(1)
                .date(new Date(1L))
                .offer(new OfferBuilder()
                        .id(1)
                        .name("TestOffer")
                        .price(1f)
                        .build()
                )
                .employee((Employee) new EmployeeBuilder()
                        .workWeek(new int[]{1, 1, 1, 1, 1, 1, 1})
                        .name("TestEmployeeName")
                        .surname("TestEmployeeSurname")
                        .build()
                )
                .build();

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority((authenticatedUserTest
                .getRole()
                .toString()
        )));

        authentication = new UsernamePasswordAuthenticationToken(
                authenticatedUserTest,
                "",
                authorities
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(profileController)
                .build();
    }

    @Test
    public void showEditPage_ShouldAddReservationsToModelAndRenderProfileEditView()
            throws Exception {
        List<Reservation> reservations = Collections
                .singletonList(reservationTest);
        when(principalMock.getName()).thenReturn("login");
        when(userServiceMock.findByLogin(anyString()))
                .thenReturn(new User(1));
        when(reservationServiceMock.findByCustomer(anyInt()))
                .thenReturn(reservations);

        mockMvc
                .perform(get(AllowPageURL.PROFILE_EDIT)
                        .principal(principalMock)
                )
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.PROFILE_EDIT))
                .andExpect(model().attribute("userReservations",
                        reservations));

        verify(principalMock, times(2))
                .getName();
        verify(userServiceMock, times(1))
                .findByLogin(anyString());
        verify(reservationServiceMock, times(1))
                .findByCustomer(anyInt());
        verifyNoMoreInteractions(principalMock, userServiceMock,
                reservationServiceMock);
    }

    @Test
    public void updateUser_ShouldUpdateUserInDatabaseAndSessionAndAddMessageAndRedirectUrlToRedirectAttrsAndRedirectAndRenderMessageView()
            throws Exception {
        User updatedAuthenticatedUser = new User(authenticatedUserTest.getId());
        updatedAuthenticatedUser.setName("NewTestName");
        updatedAuthenticatedUser.setSurname("NewTestSurname");
        updatedAuthenticatedUser.setPatronymic("NewTestPatronymic");
        updatedAuthenticatedUser.setPhone(112223344);
        updatedAuthenticatedUser.setEmail("NewTestEmail");
        updatedAuthenticatedUser.setLogin(authenticatedUserTest.getLogin());
        updatedAuthenticatedUser.setPassword(authenticatedUserTest.getPassword());
        updatedAuthenticatedUser.setImagePath(authenticatedUserTest.getImagePath());
        updatedAuthenticatedUser.setRole(authenticatedUserTest.getRole());
        doNothing().when(userServiceMock).save(any(User.class));
        mockMvc
                .perform(post(AllowPageURL.PROFILE_EDIT)
                        .with(user(authenticatedUserTest))                      // add authentication as "SPRING_SECURITY_CONTEXT" attribute of session
                        .principal(authentication)                              // add authentication as method's parameter
                        .param("name", "NewTestName")
                        .param("surname", "NewTestSurname")
                        .param("patronymic", "NewTestPatronymic")
                        .param("phone", "112223344")
                        .param("email", "NewTestEmail")
                )
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.MESSAGE))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.MESSAGE))
                .andExpect(authenticated()
                        .withAuthenticationPrincipal(updatedAuthenticatedUser)) // check authentication principal after updating
                .andExpect(flash().attribute("message",
                        "Profile data was updated."))
                .andExpect(flash().attribute("redirectUrl",
                        AllowPageURL.PROFILE_EDIT));
        verify(userServiceMock, times(1))
                .save(any(User.class));
        verifyNoMoreInteractions(userServiceMock);
    }

    @Test
    public void removeReservation_ShouldAddMessageAndRedirectUrlToRedirectAttrsAndRenderMessageView()
            throws Exception {
        doNothing().when(reservationServiceMock).delete(anyInt());
        mockMvc
                .perform(post(AllowPageURL.PROFILE_EDIT)
                        .param("reservation-id", "1")
                )
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.MESSAGE))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.MESSAGE))
                .andExpect(flash().attribute("message",
                        "Reservation was deleted."))
                .andExpect(flash().attribute("redirectUrl",
                        AllowPageURL.PROFILE_EDIT));
    }
}
