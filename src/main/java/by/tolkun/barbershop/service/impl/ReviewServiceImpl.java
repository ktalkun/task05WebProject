package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.ReviewDao;
import by.tolkun.barbershop.entity.Review;
import by.tolkun.barbershop.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(final ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> findAll() {
        return reviewDao.readAll();
    }

    @Override
    public Review findByIdentity(final int identity) {
        return reviewDao.read(identity);
    }

    @Override
    public List<Review> findByCustomer(final int identity) {
        return reviewDao.readByCustomer(identity);
    }

    @Override
    public List<Review> findByEmployee(final int identity) {
        return reviewDao.readByEmployee(identity);
    }

    @Override
    public void save(final Review review) {
        if (review.getId() != 0) {
            reviewDao.update(review);
        } else {
            review.setId(reviewDao.create(review));
        }
    }

    @Override
    public void delete(final int identity) {
        reviewDao.delete(identity);
    }
}
