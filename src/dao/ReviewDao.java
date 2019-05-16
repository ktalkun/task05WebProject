package dao;

import entity.Review;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    List<Review> readByCustomer(int customerId);
    List<Review> readByEmployee(int employeeId);
}
