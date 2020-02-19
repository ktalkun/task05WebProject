package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.config.SecurityConfig;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.BarberController;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.service.EmployeeService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Date;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class BarberControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeServiceMock;

    @InjectMocks
    private BarberController barberController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(barberController)
                .build();
    }

    @Test
    public void showPage_ShouldAddBarbersAndPageNumberAndPageToModelAndRenderAboutView() throws Exception {
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
        when(employeeServiceMock.findAll(0, 10))
                .thenReturn(employees);
        when(employeeServiceMock.noteNumber()).thenReturn(90);
        mockMvc
                .perform(get(AllowPageURL.BARBER))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.BARBER))
                .andExpect(model().attribute("employees", employees))
                .andExpect(model().attribute("pageNumber", is(9)))
                .andExpect(model().attribute("currentPage", is(1)));
        verify(employeeServiceMock, times(1))
                .findAll(0, 10);
        verify(employeeServiceMock, times(1))
                .noteNumber();
        verifyNoMoreInteractions(employeeServiceMock);
    }
}
