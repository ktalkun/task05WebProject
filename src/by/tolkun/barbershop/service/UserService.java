package by.tolkun.barbershop.service;

import entity.User;
import exception.LogicException;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll() throws LogicException;

    User findByIdentity(int identity) throws LogicException;

    User findByLoginAndPassword(String login, String password)
            throws LogicException;

    void save(User user) throws LogicException;

    void delete(int identity) throws LogicException;
}
