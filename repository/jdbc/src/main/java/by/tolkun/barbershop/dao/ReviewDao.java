package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    List<Review> readByCustomer(int customerId);

    List<Review> readByEmployee(int employeeId);
}
