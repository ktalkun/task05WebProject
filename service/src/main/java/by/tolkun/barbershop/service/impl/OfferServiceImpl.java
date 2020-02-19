package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {

    private OfferDao offerDao;

    @Autowired
    public OfferServiceImpl(final OfferDao offerDao) {
        this.offerDao = offerDao;
    }

    @Override
    public List<Offer> findAll() {
        return offerDao.readAll();
    }

    @Override
    public Offer findByIdentity(final int identity) {
        return offerDao.read(identity);
    }

    @Override
    public void save(final Offer user) {
        if (user.getId() != 0) {
            offerDao.update(user);
        } else {
            user.setId(offerDao.create(user));
        }
    }

    @Override
    public void delete(final int identity) {
        offerDao.delete(identity);
    }
}
