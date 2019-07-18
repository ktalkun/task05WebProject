package by.tolkun.barbershop.service.impl;

import dao.DAOFactory;
import dao.ReviewDao;
import entity.Review;
import exception.LogicException;
import exception.PersistentException;
import service.ReviewService;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {

    private ReviewDao reviewDao;

    public ReviewServiceImpl(){
        reviewDao = DAOFactory.getInstance().getReviewDao();
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
