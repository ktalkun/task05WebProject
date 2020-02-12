package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(final User user) {
        final String query = "INSERT INTO users (login, password, name, surname, patronymic, email, phone, image_path, role) VALUES (?,?,?,?,?,?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
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

        int id;
        if (!Objects.requireNonNull(keyHolder.getKeys()).isEmpty()) {
            id = (Integer) keyHolder.getKeys().get("id");
        } else {
            id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        }

        return id;
    }

    @Override
    public User read(int id) {
        final String query = "SELECT id, login, password, name, surname, patronymic, email, phone, image_path, role FROM users WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> readAll() {
        final String query = "SELECT id, login, password, name, surname, patronymic, email, phone, image_path, role FROM users";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public User read(String login) {
        final String query = "SELECT id, login, password, name, surname, patronymic, email, phone, image_path, role FROM users WHERE login = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserMapper(), login);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User read(String login, String password) {
        final String query = "SELECT id, login, password, name, surname, patronymic, email, phone, image_path, role FROM users WHERE login = ? AND password = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserMapper(), login,
                    password);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(User user) {
        final String query = "UPDATE users SET login = ?, password = ?, name  = ?, surname = ?, patronymic = ?, email = ?, phone = ?, image_path = ?, role = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getLogin(), user.getPassword(),
                user.getName(), user.getSurname(), user.getPatronymic(),
                user.getEmail(), user.getPhone(), user.getImagePath(),
                user.getRole().getIdentity(), user.getId());
    }

    @Override
    public void delete(int id) {
        final String query = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
