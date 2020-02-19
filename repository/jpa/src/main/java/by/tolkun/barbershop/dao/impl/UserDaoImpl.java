package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    public UserDaoImpl(final JdbcTemplate jdbcTemplate) {

    }

    @Override
    public int create(final User user) {
        return 0;
    }

    @Override
    public User read(int id) {
        return null;
    }

    @Override
    public List<User> readAll() {
        return null;
    }

    @Override
    public User read(String login) {
        return null;
    }

    @Override
    public User read(String login, String password) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(int id) {
    }
}
