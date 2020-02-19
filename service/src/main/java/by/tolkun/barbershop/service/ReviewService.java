package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Review;

import java.util.List;

public interface ReviewService extends Service {
    List<Review> findAll();

    Review findByIdentity(int identity);

    List<Review> findByCustomer(int identity);

    List<Review> findByEmployee(int identity);

    void save(Review review);

    void delete(int identity);
}
