package dao.mysql;

import builder.ServiceBuilder;
import dao.ServiceDao;
import entity.Service;
import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceDaoImpl extends BaseDaoImpl implements ServiceDao {
    private static final Logger LOGGER
            = LogManager.getLogger(ServiceDaoImpl.class);

    @Override
    public int create(final Service service) throws PersistentException {
        final String query = "INSERT INTO `services` (`name`, `description`, `price`, `period`) VALUES (?, ?, ?, ?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setFloat(3, service.getPrice());
            statement.setInt(4, service.getPeriod());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("No index autoincrement after insert into `services`");
                throw new PersistentException("No index autoincrement after insert into `services`");
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
    public Service read(final int id) throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `price`, `period` FROM `services` WHERE `id` = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ServiceBuilder serviceBuilder = new ServiceBuilder();
            if (resultSet.next()) {
                serviceBuilder
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getFloat("price"))
                        .period(resultSet.getInt("period"));
            } else {
                LOGGER.warn("No note with id {}", id);
            }
            return serviceBuilder.build();
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
    public List<Service> readAll() throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `price`, `period` FROM `services`";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ServiceBuilder serviceBuilder = new ServiceBuilder();
            List<Service> services = new ArrayList<>();
            while (resultSet.next()) {
                serviceBuilder
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .description(resultSet.getString("description"))
                        .price(resultSet.getFloat("price"))
                        .period(resultSet.getInt("period"));
                serviceBuilder.build();
            }
            return services;
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
    public void update(final Service service) throws PersistentException {
        final String query = "UPDATE `services` SET `name` = ?, `description` = ?, `price` = ?, `period` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setString(1, service.getName());
            statement.setString(2, service.getDescription());
            statement.setFloat(3, service.getPrice());
            statement.setInt(4, service.getPeriod());
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
        final String query = "DELETE FROM `services` WHERE `id` = ?";
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
