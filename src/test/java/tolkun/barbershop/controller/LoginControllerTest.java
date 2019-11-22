package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.LoginController;
import by.tolkun.barbershop.entity.MenuItem;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class})
@WebAppConfiguration
public class LoginControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private UserService userServiceMock;

    @Autowired
    @InjectMocks
    private LoginController loginController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void showPage_ShouldRenderLoginView() throws Exception {
        mockMvc
                .perform(get(AllowPageURL.LOGIN))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.LOGIN));
    }

    @Test
    public void login_ShouldAddUserAndAddMenuToSessionAndRedirectAndRenderIndexView()
            throws Exception {
        User testUser = new UserBuilder()
                .login("TestLogin")
                .password("TestPassword")
                .role(Role.EMPLOYEE)
                .build();
        when(userServiceMock.findByLoginAndPassword(testUser.getLogin(),
                testUser.getPassword()))
                .thenReturn(testUser);
        mockMvc
                .perform(post(AllowPageURL.LOGIN)
                        .param("login", testUser.getLogin())
                        .param("password", testUser.getPassword())
                )
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.INDEX))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.INDEX))
                .andExpect(request().sessionAttribute("authorizedUser",
                        testUser))
                .andExpect(request().sessionAttribute("profileMenu",
                        new ArrayList<>(Arrays.asList(
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
                                )))));
        verify(userServiceMock, times(1))
                .findByLoginAndPassword(testUser.getLogin(),
                        testUser.getPassword());
        verifyNoMoreInteractions(userServiceMock);
    }
}
