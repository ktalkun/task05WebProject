package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.config.SecurityConfig;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.BookController;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.service.ReservationService;
import by.tolkun.barbershop.service.UserService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.security.Principal;
import java.sql.Date;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class BookControllerTest {


    private MockMvc mockMvc;

    @Mock
    private OfferService offerServiceMock;

    @Mock
    private Principal principalMock;

    @Mock
    private UserService userServiceMock;

    @Mock
    private EmployeeService employeeServiceMock;

    @Mock
    private ReservationService reservationServiceMock;


    @InjectMocks
    BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(bookController)
                .build();
    }

    @Test
    public void showPage_ShouldAddOffersAndEmployeesToModelAndRenderBookView()
            throws Exception {
        List<Offer> offers = Collections.singletonList(
                new OfferBuilder()
                        .id(1)
                        .name("TestName")
                        .description("TestDescription")
                        .imagePath("TestImagePath")
                        .price(10f)
                        .period(10)
                        .main(true)
                        .show(true)
                        .build()
        );
        List<Employee> employees = Collections.singletonList(
                ((EmployeeBuilder) new EmployeeBuilder()
                        .id(1)
                        .login("TestLogin")
                        .password("TestPassword")
                        .name("TestName")
                        .surname("TestSurname")
                        .patronymic("TestPatronymic")
                        .email("testemail@email.com")
                        .phone(123456789)
                        .imagePath("TestImagePath")
                        .role(Role.ROLE_EMPLOYEE))
                        .experience(new Date(0))
                        .socialRef(Stream.of(
                                new AbstractMap.SimpleEntry<>("im", "testIm"),
                                new AbstractMap.SimpleEntry<>("fb", "testFb"),
                                new AbstractMap.SimpleEntry<>("vk", "TestVk")
                        ).collect(Collectors.toMap(Map.Entry::getKey,
                                Map.Entry::getValue)))
                        .workWeek(new int[]{0, 0, 1, 1, 0, 0, 1})
                        .build()
        );
        when(offerServiceMock.findAll()).thenReturn(offers);
        when(employeeServiceMock.findAll()).thenReturn(employees);
        mockMvc
                .perform(get(AllowPageURL.BOOK, 1))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.BOOK))
                .andExpect(model().attribute("offers", offers))
                .andExpect(model().attribute("employees", employees));
        verify(offerServiceMock, times(1)).findAll();
        verify(employeeServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(offerServiceMock, employeeServiceMock);
    }

    @Test
    public void makeReservation_ShouldAddMessageAndRedirectUrlToRedirectAttrsAndRedirectAndRenderMessageView()
            throws Exception {
        when(offerServiceMock.findByIdentity(1))
                .thenReturn(new Offer(1));
        when(principalMock.getName()).thenReturn("login");
        when(userServiceMock.findByLogin(anyString()))
                .thenReturn(new User(1));
        when(employeeServiceMock.findByIdentity(1))
                .thenReturn(new Employee(1));
        doNothing().when(reservationServiceMock)
                .save(ArgumentMatchers.any(Reservation.class));
        mockMvc
                .perform(post(AllowPageURL.BOOK)
                        .param("offer", "1")
                        .param("employee", "1")
                        .param("date", "01/01/2001")
                        .param("time", "00:00 AM")
                        .principal(principalMock)
                )
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.MESSAGE))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.MESSAGE))
                .andExpect(flash().attribute("message",
                        "Reservation was saved."))
                .andExpect(flash().attribute("redirectUrl",
                        AllowPageURL.ROOT));
        verify(offerServiceMock, times(1))
                .findByIdentity(anyInt());
        verify(principalMock, times(2))
                .getName();
        verify(userServiceMock, times(1))
                .findByLogin(anyString());
        verify(employeeServiceMock, times(1))
                .findByIdentity(anyInt());
        verify(reservationServiceMock, times(1))
                .save(ArgumentMatchers.any(Reservation.class));
        verifyNoMoreInteractions(offerServiceMock, userServiceMock,
                principalMock, employeeServiceMock, reservationServiceMock);
    }
}
