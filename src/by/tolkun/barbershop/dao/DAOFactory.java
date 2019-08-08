package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.dao.mysql.*;
import by.tolkun.barbershop.dao.pool.ConnectionPool;
import by.tolkun.barbershop.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;


public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);

    private final UserDao userDao;
    private final OfferDao offerDao;
    private final ReviewDao reviewDao;
    private final ReservationDao reservationDao;

    private DAOFactory() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        userDao = new UserDaoImpl();
        offerDao = new OfferDaoImpl();
        reviewDao = new ReviewDaoImpl();
        reservationDao = new ReservationDaoImpl();
        try {
            Connection connection = connectionPool.getConnection();
            ((BaseDaoImpl) userDao)
                    .setConnection(connection);
            ((BaseDaoImpl) offerDao)
                    .setConnection(connectionPool.getConnection());
            ((BaseDaoImpl) reviewDao)
                    .setConnection(connectionPool.getConnection());
            ((BaseDaoImpl) reservationDao)
                    .setConnection(connectionPool.getConnection());
        } catch (PersistentException e) {
            e.printStackTrace();
        }
//        TODO: NULLPOINTEREXC
//        LOGGER.debug("Object of DAOFactory created.");
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public OfferDao getOfferDao() {
        return offerDao;
    }

    public ReviewDao getReviewDao() {
        return reviewDao;
    }

    public ReservationDao getReservationDao() {
        return reservationDao;
    }


}
