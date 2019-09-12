package by.tolkun.barbershop.action;

import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.service.OfferService;
import by.tolkun.barbershop.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

public class OfferAction extends Action {

    private static final Logger LOGGER
            = LogManager.getLogger(OfferAction.class);

    @Override
    public Forward execute(HttpServletRequest request, HttpServletResponse response) {
        OfferService offerService = ServiceFactory
                .getInstance()
                .getOfferService();
        try {
            List<Offer> availableOffers = offerService
                    .findAll()
                    .stream()
                    .filter(offer -> offer.isShow())
                    .collect(Collectors.toList());
            List<Offer> mainOffers = availableOffers
                    .stream()
                    .filter(offer -> offer.isMain())
                    .collect(Collectors.toList());
            List<Offer> additionalOffers = availableOffers
                    .stream()
                    .filter(offer -> !offer.isMain())
                    .collect(Collectors.toList());
            request.setAttribute("mainOffers", mainOffers);
            request.setAttribute("additionalOffers", additionalOffers);
        } catch (LogicException e) {
            LOGGER.error(e);
        }
        return new Forward("/service.jsp", false);
    }
}
