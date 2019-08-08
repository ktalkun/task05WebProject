package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public int create(final User user) throws PersistentException {
        final String query = "INSERT INTO `users` (`login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role`) VALUES (?,?,?,?,?,?,?,?,?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPatronymic());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getPhone());
            statement.setString(8, user.getImagePath());
            statement.setInt(9, user.getRole().getIdentity());
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
    public User read(int id) throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users` WHERE `id` = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            UserBuilder userBuilder = new UserBuilder();
            if (resultSet.next()) {
                return userBuilder
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("imagePath"))
                        .role(Role.getByIdentity(resultSet.getInt("role")))
                        .build();

            }
            LOGGER.warn("No note with id {}", id);
            return null;
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
    public List<User> readAll() throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users`";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            UserBuilder userBuilder = new UserBuilder();
            List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                userBuilder
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("imagePath"))
                        .role(Role.getByIdentity(resultSet.getInt("role")));
                users.add(userBuilder.build());
            }
            return users;
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
    public User read(String login, String password) throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `imagePath`, `role` FROM `users` WHERE `login` = ? AND password = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            UserBuilder userBuilder = new UserBuilder();
            if (resultSet.next()) {
                return userBuilder
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("imagePath"))
                        .role(Role.getByIdentity(resultSet.getInt("role")))
                        .build();

            }
            LOGGER.warn("No note with login={} and password={}", login, password);
            return null;
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
    public void update(User user) throws PersistentException {
        final String query = "UPDATE `users` SET `login` = ?, `password` = ?, `name`  = ?, `surname` = ?, `patronymic` = ?, `email` = ?, `phone` = ?, `imagePath` = ?, `role` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getPatronymic());
            statement.setString(6, user.getEmail());
            statement.setLong(7, user.getPhone());
            statement.setString(8, user.getImagePath());
            statement.setInt(9, user.getRole().getIdentity());
            statement.setInt(10, user.getId());
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

    @Override
    public void delete(int id) throws PersistentException {
        final String query = "DELETE FROM `users` WHERE `id` = ?";
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
