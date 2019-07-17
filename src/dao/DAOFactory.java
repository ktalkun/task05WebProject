package dao;

import dao.mysql.ReservationDaoImpl;
import dao.mysql.ReviewsDaoImpl;
import dao.mysql.ServiceDaoImpl;
import dao.mysql.UserDaoImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private static final Logger LOGGER = LogManager.getLogger(DAOFactory.class);

    private final UserDao userDao;
    private final ServiceDao serviceDao;
    private final ReviewDao reviewDao;
    private final ReservationDao reservationDao;

    private DAOFactory(){
        userDao = new UserDaoImpl();
        serviceDao = new ServiceDaoImpl();
        reviewDao = new ReviewsDaoImpl();
        reservationDao = new ReservationDaoImpl();
        LOGGER.debug("Object of DAOFactory created");
    }

    public static DAOFactory getInstance(){
        return instance;
    }

    public UserDao getUserDao(){
        return userDao;
    }

    public ServiceDao getServiceDao(){
        return serviceDao;
    }

    public ReviewDao getReviewDao(){
        return reviewDao;
    }

    public ReservationDao getReservationDao(){
        return reservationDao;
    }


}
