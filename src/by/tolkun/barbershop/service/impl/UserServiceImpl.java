package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.DAOFactory;
import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.password.HashGenerator;
import by.tolkun.barbershop.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final Logger LOGGER
            = LogManager.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    public UserServiceImpl() {
        userDao = DAOFactory
                .getInstance()
                .getUserDao();
    }

    @Override
    public List<User> findAll() throws LogicException {
        try {
            return userDao.readAll();
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public User findByIdentity(final int identity) throws LogicException {
        try {
            return userDao.read(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public User findByLogin(String login) throws LogicException {
        try {
            return userDao.read(login);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public User findByLoginAndPassword(final String login,
                                       final String password)
            throws LogicException {
        try {
            String hashPassword
                    = HashGenerator.hashPassword(password, login).get();
            return userDao.read(login, hashPassword);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void save(final User user) throws LogicException {
        try {
            if (user.getId() != 0) {
                if (user.getPassword() != null) {
                    user.setPassword(
                            HashGenerator
                                    .hashPassword(
                                            user.getPassword(),
                                            user.getLogin()
                                    ).get()
                    );
                } else {
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
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void delete(final int identity) throws LogicException {
        try {
            userDao.delete(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }
}
