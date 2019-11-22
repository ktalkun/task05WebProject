package tolkun.barbershop.controller;

import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.url.AllowPageURL;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class})
@WebAppConfiguration
public class LogoutControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void showPage_ShouldRemoveSessionFromSessionAndRedirectRenderLoginView()
            throws Exception {
        MvcResult mvcResult = mockMvc
                .perform(get(AllowPageURL.LOGOUT)
                .sessionAttr("authorizedUser", new User(1)))
                .andExpect(status().is(302))
                .andExpect(redirectedUrl(AllowPageURL.LOGIN))
                .andExpect(view().name("redirect:"
                        + AllowPageURL.LOGIN))
                .andReturn();
        Assert.assertNull(mvcResult.getRequest().getSession(false));
    }
}
