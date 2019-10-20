package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.ReviewDao;
import by.tolkun.barbershop.entity.Review;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
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
    public List<Review> findAll() throws LogicException {
        try {
            return reviewDao.readAll();
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public Review findByIdentity(final int identity) throws LogicException {
        try {
            return reviewDao.read(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public List<Review> findByCustomer(final int identity) throws LogicException {
        try {
            return reviewDao.readByCustomer(identity);
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public List<Review> findByEmployee(final int identity) throws LogicException {
        try {
            return reviewDao.readByEmployee(identity);
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public void save(final Review review) throws LogicException {
        try {
            if (review.getId() != 0) {
                reviewDao.update(review);
            } else {
                review.setId(reviewDao.create(review));
            }
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void delete(final int identity) throws LogicException {
        try {
            reviewDao.delete(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }
}
