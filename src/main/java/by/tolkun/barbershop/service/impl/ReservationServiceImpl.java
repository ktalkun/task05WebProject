package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.ReservationDao;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationDao reservationDao;

    @Autowired
    public ReservationServiceImpl(final ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    public List<Reservation> findAll() {
        return reservationDao.readAll();
    }

    @Override
    public Reservation findByIdentity(final int identity) {
        return reservationDao.read(identity);
    }

    @Override
    public List<Reservation> findByCustomer(final int identity) {
        return reservationDao.readByCustomer(identity);
    }

    @Override
    public List<Reservation> findByEmployee(final int identity) {
        return reservationDao.readByEmployee(identity);
    }

    @Override
    public void save(final Reservation reservation) {
        if (reservation.getId() != 0) {
            reservationDao.update(reservation);
        } else {
            reservation.setId(reservationDao.create(reservation));
        }
    }

    @Override
    public void delete(final int identity) {
        reservationDao.delete(identity);
    }
}
