package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Reservation;

import java.util.List;

public interface ReservationService extends Service {
    List<Reservation> findAll();

    Reservation findByIdentity(int identity);

    List<Reservation> findByCustomer(int identity);

    List<Reservation> findByEmployee(int identity);

    void save(Reservation reservation);

    void delete(int identity);
}
