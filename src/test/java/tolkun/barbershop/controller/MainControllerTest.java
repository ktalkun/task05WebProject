package tolkun.barbershop.controller;

import by.tolkun.barbershop.config.SecurityConfig;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.MainController;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {WebConfig.class, SpringConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class MainControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private MainController mainController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(mainController)
                .build();
    }

    @Test
    public void showPage_ShouldRenderIndexView() throws Exception {
        mockMvc
                .perform(get(AllowPageURL.ROOT))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.INDEX));
    }
}
