package tolkun.barbershop.controller;

import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.config.SecurityConfig;
import by.tolkun.barbershop.config.SpringConfig;
import by.tolkun.barbershop.config.WebConfig;
import by.tolkun.barbershop.controller.OfferController;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.service.OfferService;
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

import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, WebConfig.class, SecurityConfig.class})
@WebAppConfiguration
public class OfferControllerTest {

    private MockMvc mockMvc;

    @Mock
    private OfferService offerServiceMock;

    @InjectMocks
    private OfferController offerController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(offerController)
                .build();
    }

    @Test
    public void showPage_ShouldAddOffersToModelAndRenderServiceView()
            throws Exception {
        Offer mainOfferTest = new OfferBuilder()
                .id(1)
                .name("TestName1")
                .description("TestDescription1")
                .imagePath("TestImagePath1")
                .period(1)
                .price(1f)
                .main(true)
                .show(true)
                .build();
        Offer additionalOfferTest = new OfferBuilder()
                .id(2)
                .name("TestName2")
                .description("TestDescription2")
                .imagePath("TestImagePath2")
                .period(2)
                .price(2f)
                .main(false)
                .show(true)
                .build();
        when(offerServiceMock.findAll()).thenReturn(Arrays.asList(
                mainOfferTest, additionalOfferTest
        ));
        mockMvc
                .perform(get(AllowPageURL.SERVICE))
                .andExpect(status().isOk())
                .andExpect(view().name(AllowView.SERVICE))
                .andExpect(model().attribute("mainOffers",
                        Collections.singletonList(mainOfferTest)))
                .andExpect(model().attribute("additionalOffers",
                        Collections.singletonList(additionalOfferTest)));
        verify(offerServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(offerServiceMock);
    }
}
