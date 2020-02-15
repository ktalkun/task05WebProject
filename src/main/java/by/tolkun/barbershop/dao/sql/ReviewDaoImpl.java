package by.tolkun.barbershop.dao.sql;

import by.tolkun.barbershop.dao.ReviewDao;
import by.tolkun.barbershop.entity.Review;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewDaoImpl implements ReviewDao {

    @Override
    public int create(final Review review) {
        return 0;
    }

    @Override
    public Review read(final int id) {
        return null;
    }

    @Override
    public List<Review> readByCustomer(final int customerId) {
        return null;
    }

    @Override
    public List<Review> readByEmployee(final int employeeId) {
        return null;
    }

    @Override
    public List<Review> readAll() {
        return null;
    }

    @Override
    public void update(Review entity) {

    }

    @Override
    public void delete(int id) {

    }
}
