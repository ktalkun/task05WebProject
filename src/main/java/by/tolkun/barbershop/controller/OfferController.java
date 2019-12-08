package by.tolkun.barbershop.controller;

import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.url.AllowPageURL;
import by.tolkun.barbershop.view.AllowView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OfferController {

    private static final Logger LOGGER
            = LogManager.getLogger(OfferController.class);

    private OfferService offerService;

    @Autowired
    public OfferController(final OfferService offerService) {
        this.offerService = offerService;
    }

    @RequestMapping(path = AllowPageURL.SERVICE)
    public String showPage(Model model) {
        List<Offer> availableOffers;
        availableOffers = offerService
                .findAll()
                .stream()
                .filter(Offer::isShow)
                .collect(Collectors.toList());
        model.addAttribute("mainOffers", availableOffers
                .stream()
                .filter(Offer::isMain)
                .collect(Collectors.toList()));
        model.addAttribute("additionalOffers", availableOffers
                .stream()
                .filter(offer -> !offer.isMain())
                .collect(Collectors.toList()));
        return AllowView.SERVICE;
    }
}
