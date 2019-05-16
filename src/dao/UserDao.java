package dao;

import entity.Reservation;
import entity.User;
import exception.PersistentException;

import java.util.List;

public interface UserDao extends Dao<User> {
    User read(String login, String password) throws PersistentException;
}
