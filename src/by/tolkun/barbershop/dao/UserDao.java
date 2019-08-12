package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.PersistentException;

public interface UserDao extends Dao<User> {
    User read(String login) throws PersistentException;
    User read(String login, String password) throws PersistentException;
}
