package by.tolkun.barbershop.dao;

import entity.Reservation;
import entity.User;
import exception.PersistentException;

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws PersistentException;
}
