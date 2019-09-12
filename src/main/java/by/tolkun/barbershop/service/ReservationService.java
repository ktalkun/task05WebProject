package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.exception.LogicException;

import java.util.List;

public interface ReservationService extends Service{
    List<Reservation> findAll() throws LogicException;

    Reservation findByIdentity(int identity) throws LogicException;

    List<Reservation> findByCustomer(int identity) throws LogicException;

    List<Reservation> findByEmployee (int identity) throws LogicException;

    void save(Reservation reservation) throws LogicException;

    void delete(int identity) throws LogicException;
}
