package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.password.HashGenerator;
import by.tolkun.barbershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAll() {
        return userDao.readAll();
    }

    @Override
    public User findByIdentity(final int identity) {
        return userDao.read(identity);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.read(login);
    }

    @Override
    public User findByLoginAndPassword(final String login,
                                       final String password) {
        String hashPassword
                = HashGenerator.hashPassword(password, login).get();
        return userDao.read(login, hashPassword);
    }

    @Override
    public void save(final User user) {
        if (user.getId() != 0) {
            if (user.getPassword() == null) {
                User oldUser = userDao.read(user.getId());
                user.setPassword(oldUser.getPassword());
            }
            userDao.update(user);
        } else {
            user.setPassword(HashGenerator
                    .hashPassword(
                            user.getPassword(),
                            user.getLogin()
                    ).get());
            user.setId(userDao.create(user));
        }
    }

    @Override
    public void delete(final int identity) {
        userDao.delete(identity);
    }
}
