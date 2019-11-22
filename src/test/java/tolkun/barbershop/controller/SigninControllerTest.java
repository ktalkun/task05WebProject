package tolkun.barbershop.controller;

import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.SigninController;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class})
@WebAppConfiguration
public class SigninControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private UserService userServiceMock;

    @Autowired
    @InjectMocks
    private SigninController signinController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void showPage_ShouldRenderSigninView() throws Exception {
        mockMvc
                .perform(get(AllowPageURL.SIGNIN))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.SIGNIN));
    }



    @Test
    public void signin_ShouldAndAddMessageAndRedirectUrlToRedirectAttrsAndRedirectAndRenderMessageView()
            throws Exception {
        when(userServiceMock.findByLogin(anyString())).thenReturn(null);
        doNothing().when(userServiceMock).save(any(User.class));
        mockMvc
                .perform(post(AllowPageURL.SIGNIN)
                        .param("surname", "TestSurname")
                        .param("name", "TestName")
                        .param("patronymic", "TestPatronymic")
                        .param("email", "TestEmail")
                        .param("phone", "112223344")
                        .param("login", "TestLogin")
                        .param("password", "TestPassword")
                        .param("repeatPassword", "TestPassword")
                )
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.MESSAGE))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.MESSAGE))
                .andExpect(flash().attribute("message",
                        "Registration successful."))
                .andExpect(flash().attribute("redirectUrl",
                        AllowPageURL.LOGIN));
        verify(userServiceMock, times(1))
                .findByLogin(anyString());
        verify(userServiceMock, times(1))
                .save(any(User.class));
        verifyNoMoreInteractions(userServiceMock);
    }
}
