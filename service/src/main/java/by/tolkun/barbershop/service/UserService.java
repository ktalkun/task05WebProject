package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.User;

import java.util.List;

public interface UserService extends Service {
    List<User> findAll();

    User findByIdentity(int identity);

    User findByLogin(String login);

    User findByLoginAndPassword(String login, String password);

    void save(User user);

    void delete(int identity);
}
