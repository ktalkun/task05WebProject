package tolkun.barbershop.controller;

import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class})
@WebAppConfiguration
public class MessageControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build();
    }

    @Test
    public void showPage_ShouldRenderMessageView() throws Exception {
        mockMvc
                .perform(get(AllowPageURL.MESSAGE)
                        .flashAttr("message", "TestMessage")
                        .flashAttr("redirectUrl", "TestUrl")
                )
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.MESSAGE))
                .andExpect(model()
                        .attribute("message", "TestMessage"))
                .andExpect(model()
                        .attribute("redirectUrl", "TestUrl"));
    }
}
