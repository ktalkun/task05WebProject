package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(final User user) throws PersistentException {
        final String query = "INSERT INTO `users` (`login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role`) VALUES (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection1 -> {
            PreparedStatement ps = connection1.prepareStatement(query);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setString(4, user.getSurname());
            ps.setString(5, user.getPatronymic());
            ps.setString(6, user.getEmail());
            ps.setLong(7, user.getPhone());
            ps.setString(8, user.getImagePath());
            ps.setInt(9, user.getRole().getIdentity());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public User read(int id) throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role` FROM `users` WHERE `id` = ?";
        return jdbcTemplate.queryForObject(query, new UserMapper(), id);
    }

    @Override
    public List<User> readAll() throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role` FROM `users`";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public User read(String login) throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role` FROM `users` WHERE `login` = ?";
        return jdbcTemplate.queryForObject(query, new UserMapper(), login);
    }

    @Override
    public User read(String login, String password) throws PersistentException {
        final String query = "SELECT `id`, `login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role` FROM `users` WHERE `login` = ? AND password = ?";
        return jdbcTemplate.queryForObject(query, new UserMapper(), login, password);
    }

    @Override
    public void update(User user) throws PersistentException {
        final String query = "UPDATE `users` SET `login` = ?, `password` = ?, `name`  = ?, `surname` = ?, `patronymic` = ?, `email` = ?, `phone` = ?, `image_path` = ?, `role` = ? WHERE `id` = ?";
        jdbcTemplate.update(query, user.getLogin(), user.getPassword(),
                user.getName(), user.getSurname(), user.getPatronymic(),
                user.getEmail(), user.getPhone(), user.getImagePath(),
                user.getRole().getIdentity(), user.getId());
    }

    @Override
    public void delete(int id) throws PersistentException {
        final String query = "DELETE FROM `users` WHERE `id` = ?";
        jdbcTemplate.update(query, id);
    }
}
