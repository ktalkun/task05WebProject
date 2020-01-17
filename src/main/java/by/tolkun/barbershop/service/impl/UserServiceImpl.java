package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String SALT = "barber_salt";

    private UserDao userDao;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(final UserDao userDao,
                           final PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
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
        return userDao.read(login, passwordEncoder.encode(password));
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
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setId(userDao.create(user));
        }
    }

    @Override
    public void delete(final int identity) {
        userDao.delete(identity);
    }
}
