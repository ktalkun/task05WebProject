package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.ProfileController;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.MenuItem;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class})
@WebAppConfiguration
public class ProfileControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private ReservationService reservationServiceMock;

    @Mock
    private UserService userServiceMock;

    @Autowired
    @InjectMocks
    private ProfileController profileController;

    private User authorizedUserTest;

    private Reservation reservationTest;

    private List<MenuItem> profileMenu;

    @Before
    public void setUp() {
        authorizedUserTest = new UserBuilder()
                .id(1)
                .login("TestLogin")
                .password("TestPassword")
                .name("TestName")
                .surname("TestSurname")
                .patronymic("TestPatronymic")
                .email("testemail@email.com")
                .phone(123456789)
                .imagePath("TestImagePath")
                .role(Role.EMPLOYEE)
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
        profileMenu = new ArrayList<>(Collections.singletonList(new MenuItem(
                AllowPageURL.PROFILE_EDIT,
                "Profile",
                "fal fa-shopping-bag"
        )));

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void showEditPage_ShouldAddReservationsToModelAndRenderProfileEditView()
            throws Exception {
        List<Reservation> reservations = Collections
                .singletonList(reservationTest);
        when(reservationServiceMock.findByCustomer(anyInt()))
                .thenReturn(reservations);
        mockMvc
                .perform(get(AllowPageURL.PROFILE_EDIT)
                        .sessionAttr("authorizedUser", authorizedUserTest)
                        .sessionAttr("profileMenu", profileMenu)
                )
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.PROFILE_EDIT))
                .andExpect(model().attribute("userReservations",
                        reservations));
    }

    @Test
    public void updateUser_ShouldUpdateUserInDatabaseAndSessionAndAddMessageAndRedirectUrlToRedirectAttrsAndRedirectAndRenderMessageView()
            throws Exception {
        doNothing().when(userServiceMock).save(any(User.class));
        mockMvc
                .perform(post(AllowPageURL.PROFILE_EDIT)
                        .sessionAttr("authorizedUser", authorizedUserTest)
                        .sessionAttr("profileMenu", profileMenu)
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
                .andExpect(request().sessionAttribute("authorizedUser",
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("login",
                                        is(authorizedUserTest.getLogin())),
                                hasProperty("password",
                                        is(authorizedUserTest.getPassword())),
                                hasProperty("name",
                                        is("NewTestName")),
                                hasProperty("surname",
                                        is("NewTestSurname")),
                                hasProperty("patronymic",
                                        is("NewTestPatronymic")),
                                hasProperty("email",
                                        is("NewTestEmail")),
                                hasProperty("phone",
                                        is(112223344L)),
                                hasProperty("imagePath",
                                        is(authorizedUserTest.getImagePath())),
                                hasProperty("role",
                                        is(authorizedUserTest.getRole()))
                        )
                ))
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
