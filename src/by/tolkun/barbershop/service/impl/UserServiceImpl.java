package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.DAOFactory;
import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.service.UserService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;

public class UserServiceImpl implements UserService {

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
    public User findByLoginAndPassword(final String login,
                                       final String password)
            throws LogicException {
        try {
            return userDao.read(login, md5(password));
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void save(final User user) throws LogicException {
        try {
            if (user.getId() != 0) {
                if (user.getPassword() != null) {
                    user.setPassword(md5(user.getPassword()));
                } else {
                    User oldUser = userDao.read(user.getId());
                    user.setPassword(oldUser.getPassword());
                }
                userDao.update(user);
            } else {
                user.setPassword(md5(new String()));
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

    private String md5(final String string) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            digest.reset();
            digest.update(string.getBytes());
            byte hash[] = digest.digest();
            Formatter formatter = new Formatter();
            for (int i = 0; i < hash.length; i++) {
                formatter.format("%02X", hash[i]);
            }
            String md5summ = formatter.toString();
            formatter.close();
            return md5summ;
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
