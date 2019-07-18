package by.tolkun.barbershop.service.impl;

import dao.DAOFactory;
import dao.OfferDao;
import entity.Offer;
import exception.LogicException;
import exception.PersistentException;
import service.OfferService;

import java.util.List;

public class OfferServiceImpl implements OfferService {

    private OfferDao offerDao;

    public OfferServiceImpl(){
        offerDao = DAOFactory.getInstance().getOfferDao();
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
