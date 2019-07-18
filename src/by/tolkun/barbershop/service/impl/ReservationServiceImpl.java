package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.DAOFactory;
import by.tolkun.barbershop.dao.ReservationDao;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.service.ReservationService;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;

    public ReservationServiceImpl(){
        reservationDao = DAOFactory.getInstance().getReservationDao();
    }

    @Override
    public List<Reservation> findAll() throws LogicException {
        try {
            return reservationDao.readAll();
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public Reservation findByIdentity(final int identity) throws LogicException {
        try {
            return reservationDao.read(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public List<Reservation> findByCustomer(final int identity) throws LogicException {
        try {
            return reservationDao.readByCustomer(identity);
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public List<Reservation> findByEmployee(final int identity) throws LogicException {
        try {
            return reservationDao.readByEmployee(identity);
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void save(final Reservation reservation) throws LogicException {
        try {
            if (reservation.getId() != 0) {
                reservationDao.update(reservation);
            } else {
                reservation.setId(reservationDao.create(reservation));
            }
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void delete(final int identity) throws LogicException {
        try {
            reservationDao.delete(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }
}
