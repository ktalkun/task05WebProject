package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.ReservationDao;
import by.tolkun.barbershop.entity.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDaoImpl implements ReservationDao {


    @Autowired
    public ReservationDaoImpl(final JdbcTemplate jdbcTemplate) {
    }

    @Override
    public int create(final Reservation reservation) {
        return 0;
    }

    @Override
    public Reservation read(final int id) {
        return null;
    }

    @Override
    public List<Reservation> readAll() {
        return null;
    }

    @Override
    public List<Reservation> readByCustomer(final int customerId) {
        return null;
    }

    @Override
    public List<Reservation> readByEmployee(final int employeeId) {
        return null;
    }

    @Override
    public void update(final Reservation reservation) {

    }

    @Override
    public void delete(final int id) {
    }
}
