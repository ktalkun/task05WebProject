package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
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
    public List<Offer> findAll() throws LogicException {
        try {
            return offerDao.readAll();
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public Offer findByIdentity(final int identity) throws LogicException {
        try {
            return offerDao.read(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void save(final Offer user) throws LogicException {
        try {
            if (user.getId() != 0) {
                offerDao.update(user);
            } else {
                user.setId(offerDao.create(user));
            }
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void delete(final int identity) throws LogicException {
        try {
            offerDao.delete(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }
}
