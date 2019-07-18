package by.tolkun.barbershop.service;

import dao.ReservationDao;
import entity.Reservation;
import entity.User;
import exception.LogicException;

import java.util.List;

public interface ReservationService extends Service{
    List<Reservation> findAll() throws LogicException;

    Reservation findByIdentity(int identity) throws LogicException;

    List<Reservation> findByCustomer(int identity) throws LogicException;

    List<Reservation> findByEmployee (int identity) throws LogicException;

    void save(Reservation reservation) throws LogicException;

    void delete(int identity) throws LogicException;
}
