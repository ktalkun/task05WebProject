package by.tolkun.barbershop.service;

import entity.Reservation;
import entity.Review;
import exception.LogicException;

import java.util.List;

public interface ReviewService extends Service{
    List<Review> findAll() throws LogicException;

    Review findByIdentity(int identity) throws LogicException;

    List<Review> findByCustomer(int identity) throws LogicException;

    List<Review> findByEmployee(int identity) throws LogicException;

    void save(Review review) throws LogicException;

    void delete(int identity) throws LogicException;
}
