package dao.mysql;

import builder.ReviewBuilder;
import dao.ReviewDao;
import entity.Review;
import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ReviewsDaoImpl extends BaseDaoImpl implements ReviewDao {

    private static final Logger LOGGER
            = LogManager.getLogger(ReviewsDaoImpl.class);

    @Override
    public int create(final Review review) throws PersistentException {
        final String query = "INSERT INTO `reviews` (`customer_id`, `employee_id`, `decription`) VALUES (?,?,?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, review.getCustomer().getId());
            statement.setInt(2, review.getEmployee().getId());
            statement.setString(3,review.getDescription());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("No index autoincrement after insert into `users`");
                throw new PersistentException("No index autoincrement after insert into `users`");
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public Review read(final int id) throws PersistentException {
        final String query = "SELECT `id`, `customer_id`, `employee_id`, `description` role` FROM `users` WHERE `id` = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ReviewBuilder reviewBuilder = new ReviewBuilder();
            if (resultSet.next()) {
                userBuilder
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("role")));

            } else {
                LOGGER.warn("No note with id {}", id);
            }
            return userBuilder.build();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
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
    public List<Review> readAll() throws PersistentException {
        return null;
    }

    @Override
    public void update(Review entity) throws PersistentException {

    }

    @Override
    public void delete(int id) throws PersistentException {

    }
}
