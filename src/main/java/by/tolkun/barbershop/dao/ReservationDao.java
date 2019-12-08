package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Reservation;

import java.util.List;

public interface ReservationDao extends Dao<Reservation> {
    List<Reservation> readByCustomer(int customerId);

    List<Reservation> readByEmployee(int employeeId);
}
