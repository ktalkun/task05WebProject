package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.User;

public interface UserDao extends Dao<User> {
    User read(String login);
    User read(String login, String password);
}
