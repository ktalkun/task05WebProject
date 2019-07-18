package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.dao.mysql.OfferDaoImpl;
import by.tolkun.barbershop.dao.mysql.ReservationDaoImpl;
import by.tolkun.barbershop.dao.mysql.ReviewDaoImpl;
import by.tolkun.barbershop.dao.mysql.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);

    private final UserDao userDao;
    private final OfferDao offerDao;
    private final ReviewDao reviewDao;
    private final ReservationDao reservationDao;

    private DAOFactory(){
        userDao = new UserDaoImpl();
        offerDao = new OfferDaoImpl();
        reviewDao = new ReviewDaoImpl();
        reservationDao = new ReservationDaoImpl();
        LOGGER.debug("Object of DAOFactory created.");
    }

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public OfferDao getOfferDao(){
        return offerDao;
    }

    public ReviewDao getReviewDao(){
        return reviewDao;
    }

    public ReservationDao getReservationDao(){
        return reservationDao;
    }


}
