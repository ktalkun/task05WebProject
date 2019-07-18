package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OfferDaoImpl extends BaseDaoImpl implements OfferDao {
    private static final Logger LOGGER
            = LogManager.getLogger(OfferDaoImpl.class);

    @Override
    public int create(final Offer offer) throws PersistentException {
        final String query = "INSERT INTO `offers` (`name`, `description`, `price`, `period`) VALUES (?, ?, ?, ?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, offer.getName());
            statement.setString(2, offer.getDescription());
            statement.setFloat(3, offer.getPrice());
            statement.setInt(4, offer.getPeriod());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("No index autoincrement after insert into `offers`");
                throw new PersistentException("No index autoincrement after insert into `offers`");
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
    public Offer read(final int id) throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `price`, `period` FROM `offers` WHERE `id` = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            OfferBuilder offerBuilder = new OfferBuilder();
            if (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getFloat("price"))
                        .period(resultSet.getInt("period"));
            } else {
                LOGGER.warn("No note with id {}", id);
            }
            return offerBuilder.build();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);;
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<Offer> readAll() throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `price`, `period` FROM `offers`";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            OfferBuilder offerBuilder = new OfferBuilder();
            List<Offer> offers = new ArrayList<>();
            while (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getFloat("price"))
                        .period(resultSet.getInt("period"));
                offerBuilder.build();
            }
            return offers;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);;
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(final Offer offer) throws PersistentException {
        final String query = "UPDATE `offers` SET `name` = ?, `description` = ?, `price` = ?, `period` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setString(1, offer.getName());
            statement.setString(2, offer.getDescription());
            statement.setFloat(3, offer.getPrice());
            statement.setInt(4, offer.getPeriod());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try{
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void delete(final int id) throws PersistentException {
        final String query = "DELETE FROM `offers` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }
}
