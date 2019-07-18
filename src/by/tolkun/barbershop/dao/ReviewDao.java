package by.tolkun.barbershop.dao;

import entity.Review;
import exception.PersistentException;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    List<Review> readByCustomer(int customerId) throws PersistentException;
    List<Review> readByEmployee(int employeeId) throws PersistentException;
}
