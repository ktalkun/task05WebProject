package dao.mysql;

import builder.EmployeeBuilder;
import builder.ReservationBuilder;
import builder.OfferBuilder;
import builder.UserBuilder;
import dao.ReservationDao;
import entity.Reservation;
import entity.Role;
import exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl extends BaseDaoImpl implements ReservationDao {

    /**
     * Logger of class {@code ReservationDaoImpl}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoImpl.class);


    @Override
    public int create(final Reservation reservation) throws PersistentException {
        final String query = "INSERT INTO `reservations` (`offer_id`, `customer_id`, `employee_id`, `date`) VALUES (?,?,?,?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, reservation.getOffer().getId());
            statement.setInt(2, reservation.getCustomer().getId());
            statement.setInt(3, reservation.getEmployee().getId());
            statement.setDate(4, reservation.getDate());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("No index autoincrement after insert into `reservations`");
                throw new PersistentException("No index autoincrement after insert into `reservations`");
            }
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public Reservation read(final int id) throws PersistentException {
        final String query = "SELECT "
                + "reservations.id,"
                + "offers.id AS offer_id,"
                + "offers.name AS offer_name,"
                + "offers.description AS offer_description,"
                + "offers.price AS offer_price,"
                + "offers.period AS offer_period,"
                + "customers.id AS customer_id,"
                + "customers.login AS customer_login,"
                + "customers.password AS customer_password,"
                + "customers.name AS customer_name,"
                + "customers.surname AS customer_surname,"
                + "customers.patronymic AS customer_patronymic,"
                + "customers.email AS customer_email,"
                + "customers.phone AS customer_phone,"
                + "customers.image_path AS customer_image_path,"
                + "customers.role AS customer_role,"
                + "employees.id AS employee_id,"
                + "employees.login AS employee_login,"
                + "employees.password AS employee_password,"
                + "employees.name AS employee_name,"
                + "employees.surname AS employee_surname,"
                + "employees.patronymic AS employee_patronymic,"
                + "employees.email AS employee_email,"
                + "employees.phone AS employee_phone,"
                + "employees.image_path AS employee_image_path,"
                + "employees.role AS employee_role,"
                + "employees_info.experience AS employee_experience, "
                + "reservations.date "
                + "FROM reservations "
                + "JOIN offers ON offers.id = reservations.offer_id "
                + "JOIN users AS customers ON customers.id = reservations.customer_id "
                + "JOIN users AS employees ON employees.id = reservations.employee_id "
                + "JOIN employees AS employees_info ON employees.id = reservations.employee_id "
                + "WHERE `id` = ?;";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            OfferBuilder offerBuilder = new OfferBuilder();
            UserBuilder customerBuilder = new UserBuilder();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            if (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("offer_id"))
                        .name(resultSet.getString("offer_name"))
                        .description(resultSet.getString("offer_description"))
                        .price(resultSet.getFloat("offer_price"))
                        .period(resultSet.getInt("offer_period"));
                customerBuilder
                        .id(resultSet.getInt("customer_id"))
                        .login(resultSet.getString("customer_login"))
                        .password(resultSet.getString("customer_password"))
                        .name(resultSet.getString("customer_name"))
                        .surname(resultSet.getString("customer_surname"))
                        .patronymic(resultSet.getString("customer_patronymic"))
                        .email(resultSet.getString("customer_email"))
                        .phone(resultSet.getLong("customer_phone"))
                        .imagePath(resultSet.getString("customer_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("customer_role")));
                employeeBuilder
                        .id(resultSet.getInt("employee_id"))
                        .login(resultSet.getString("employee_login"))
                        .password(resultSet.getString("employee_password"))
                        .name(resultSet.getString("employee_name"))
                        .surname(resultSet.getString("employee_surname"))
                        .patronymic(resultSet.getString("employee_patronymic"))
                        .email(resultSet.getString("employee_email"))
                        .phone(resultSet.getLong("employee_phone"))
                        .imagePath(resultSet.getString("employee_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("employee_role")));
                ((EmployeeBuilder)employeeBuilder)
                        .experience(resultSet.getDate("employee_experience"));

                reservationBuilder
                        .id(resultSet.getInt("id"))
                        .offer(offerBuilder.build())
                        .customer(customerBuilder.build())
                        .employee(employeeBuilder.build())
                        .date(resultSet.getDate("date"));

            } else {
                LOGGER.warn("No note with id {}", id);
            }
            return reservationBuilder.build();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<Reservation> readAll() throws PersistentException {
        final String query = "SELECT "
                + "reservations.id,"
                + "offers.id AS offer_id,"
                + "offers.name AS offer_name,"
                + "offers.description AS offer_description,"
                + "offers.price AS offer_price,"
                + "offers.period AS offer_period,"
                + "customers.id AS customer_id,"
                + "customers.login AS customer_login,"
                + "customers.password AS customer_password,"
                + "customers.name AS customer_name,"
                + "customers.surname AS customer_surname,"
                + "customers.patronymic AS customer_patronymic,"
                + "customers.email AS customer_email,"
                + "customers.phone AS customer_phone,"
                + "customers.image_path AS customer_image_path,"
                + "customers.role AS customer_role,"
                + "employees.id AS employee_id,"
                + "employees.login AS employee_login,"
                + "employees.password AS employee_password,"
                + "employees.name AS employee_name,"
                + "employees.surname AS employee_surname,"
                + "employees.patronymic AS employee_patronymic,"
                + "employees.email AS employee_email,"
                + "employees.phone AS employee_phone,"
                + "employees.image_path AS employee_image_path,"
                + "employees.role AS employee_role,"
                + "employees_info.experience AS employee_experience, "
                + "reservations.date "
                + "FROM reservations "
                + "JOIN offers ON offers.id = reservations.offer_id "
                + "JOIN users AS customers ON customers.id = reservations.customer_id "
                + "JOIN users AS employees ON employees.id = reservations.employee_id "
                + "JOIN employees AS employees_info ON employees.id = employees.id;";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            OfferBuilder offerBuilder = new OfferBuilder();
            UserBuilder customerBuilder = new UserBuilder();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("offer_id"))
                        .name(resultSet.getString("offer_name"))
                        .description(resultSet.getString("offer_description"))
                        .price(resultSet.getFloat("offer_price"))
                        .period(resultSet.getInt("offer_period"));
                customerBuilder
                        .id(resultSet.getInt("customer_id"))
                        .login(resultSet.getString("customer_login"))
                        .password(resultSet.getString("customer_password"))
                        .name(resultSet.getString("customer_name"))
                        .surname(resultSet.getString("customer_surname"))
                        .patronymic(resultSet.getString("customer_patronymic"))
                        .email(resultSet.getString("customer_email"))
                        .phone(resultSet.getLong("customer_phone"))
                        .imagePath(resultSet.getString("customer_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("customer_role")));
                employeeBuilder
                        .id(resultSet.getInt("employee_id"))
                        .login(resultSet.getString("employee_login"))
                        .password(resultSet.getString("employee_password"))
                        .name(resultSet.getString("employee_name"))
                        .surname(resultSet.getString("employee_surname"))
                        .patronymic(resultSet.getString("employee_patronymic"))
                        .email(resultSet.getString("employee_email"))
                        .phone(resultSet.getLong("employee_phone"))
                        .imagePath(resultSet.getString("employee_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("employee_role")));
                ((EmployeeBuilder)employeeBuilder)
                        .experience(resultSet.getDate("employee_experience"));

                reservationBuilder
                        .id(resultSet.getInt("id"))
                        .offer(offerBuilder.build())
                        .customer(customerBuilder.build())
                        .employee(employeeBuilder.build())
                        .date(resultSet.getDate("date"));
                reservations.add(reservationBuilder.build());
            }
            return reservations;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<Reservation> readByCustomer(final int customerId) throws PersistentException {
        final String query = "SELECT "
                + "reservations.id,"
                + "offers.id AS offer_id,"
                + "offers.name AS offer_name,"
                + "offers.description AS offer_description,"
                + "offers.price AS offer_price,"
                + "offers.period AS offer_period,"
                + "customers.id AS customer_id,"
                + "customers.login AS customer_login,"
                + "customers.password AS customer_password,"
                + "customers.name AS customer_name,"
                + "customers.surname AS customer_surname,"
                + "customers.patronymic AS customer_patronymic,"
                + "customers.email AS customer_email,"
                + "customers.phone AS customer_phone,"
                + "customers.image_path AS customer_image_path,"
                + "customers.role AS customer_role,"
                + "employees.id AS employee_id,"
                + "employees.login AS employee_login,"
                + "employees.password AS employee_password,"
                + "employees.name AS employee_name,"
                + "employees.surname AS employee_surname,"
                + "employees.patronymic AS employee_patronymic,"
                + "employees.email AS employee_email,"
                + "employees.phone AS employee_phone,"
                + "employees.image_path AS employee_image_path,"
                + "employees.role AS employee_role,"
                + "employees_info.experience AS employee_experience, "
                + "reservations.date "
                + "FROM reservations "
                + "JOIN offers ON offers.id = reservations.offer_id "
                + "JOIN users AS customers ON customers.id = reservations.customer_id "
                + "JOIN users AS employees ON employees.id = reservations.employee_id "
                + "JOIN employees AS employees_info ON employees.id = employees.id "
                + "WHERE reservations.customer_id = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            OfferBuilder offerBuilder = new OfferBuilder();
            UserBuilder customerBuilder = new UserBuilder();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("offer_id"))
                        .name(resultSet.getString("offer_name"))
                        .description(resultSet.getString("offer_description"))
                        .price(resultSet.getFloat("offer_price"))
                        .period(resultSet.getInt("offer_period"));
                customerBuilder
                        .id(resultSet.getInt("customer_id"))
                        .login(resultSet.getString("customer_login"))
                        .password(resultSet.getString("customer_password"))
                        .name(resultSet.getString("customer_name"))
                        .surname(resultSet.getString("customer_surname"))
                        .patronymic(resultSet.getString("customer_patronymic"))
                        .email(resultSet.getString("customer_email"))
                        .phone(resultSet.getLong("customer_phone"))
                        .imagePath(resultSet.getString("customer_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("customer_role")));
                employeeBuilder
                        .id(resultSet.getInt("employee_id"))
                        .login(resultSet.getString("employee_login"))
                        .password(resultSet.getString("employee_password"))
                        .name(resultSet.getString("employee_name"))
                        .surname(resultSet.getString("employee_surname"))
                        .patronymic(resultSet.getString("employee_patronymic"))
                        .email(resultSet.getString("employee_email"))
                        .phone(resultSet.getLong("employee_phone"))
                        .imagePath(resultSet.getString("employee_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("employee_role")));
                ((EmployeeBuilder)employeeBuilder)
                        .experience(resultSet.getDate("employee_experience"));

                reservationBuilder
                        .id(resultSet.getInt("id"))
                        .offer(offerBuilder.build())
                        .customer(customerBuilder.build())
                        .employee(employeeBuilder.build())
                        .date(resultSet.getDate("date"));
                reservations.add(reservationBuilder.build());
            }
            return reservations;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public List<Reservation> readByEmployee(final int employeeId) throws PersistentException {
        final String query = "SELECT "
                + "reservations.id,"
                + "offers.id AS offer_id,"
                + "offers.name AS offer_name,"
                + "offers.description AS offer_description,"
                + "offers.price AS offer_price,"
                + "offers.period AS offer_period,"
                + "customers.id AS customer_id,"
                + "customers.login AS customer_login,"
                + "customers.password AS customer_password,"
                + "customers.name AS customer_name,"
                + "customers.surname AS customer_surname,"
                + "customers.patronymic AS customer_patronymic,"
                + "customers.email AS customer_email,"
                + "customers.phone AS customer_phone,"
                + "customers.image_path AS customer_image_path,"
                + "customers.role AS customer_role,"
                + "employees.id AS employee_id,"
                + "employees.login AS employee_login,"
                + "employees.password AS employee_password,"
                + "employees.name AS employee_name,"
                + "employees.surname AS employee_surname,"
                + "employees.patronymic AS employee_patronymic,"
                + "employees.email AS employee_email,"
                + "employees.phone AS employee_phone,"
                + "employees.image_path AS employee_image_path,"
                + "employees.role AS employee_role,"
                + "employees_info.experience AS employee_experience, "
                + "reservations.date "
                + "FROM reservations "
                + "JOIN offers ON offers.id = reservations.offer_id "
                + "JOIN users AS customers ON customers.id = reservations.customer_id "
                + "JOIN users AS employees ON employees.id = reservations.employee_id "
                + "JOIN employees AS employees_info ON employees.id = employees.id "
                + "WHERE reservations.employee_id = ?";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            ReservationBuilder reservationBuilder = new ReservationBuilder();
            OfferBuilder offerBuilder = new OfferBuilder();
            UserBuilder customerBuilder = new UserBuilder();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            List<Reservation> reservations = new ArrayList<>();
            while (resultSet.next()) {
                offerBuilder
                        .id(resultSet.getInt("offer_id"))
                        .name(resultSet.getString("offer_name"))
                        .description(resultSet.getString("offer_description"))
                        .price(resultSet.getFloat("offer_price"))
                        .period(resultSet.getInt("offer_period"));
                customerBuilder
                        .id(resultSet.getInt("customer_id"))
                        .login(resultSet.getString("customer_login"))
                        .password(resultSet.getString("customer_password"))
                        .name(resultSet.getString("customer_name"))
                        .surname(resultSet.getString("customer_surname"))
                        .patronymic(resultSet.getString("customer_patronymic"))
                        .email(resultSet.getString("customer_email"))
                        .phone(resultSet.getLong("customer_phone"))
                        .imagePath(resultSet.getString("customer_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("customer_role")));
                employeeBuilder
                        .id(resultSet.getInt("employee_id"))
                        .login(resultSet.getString("employee_login"))
                        .password(resultSet.getString("employee_password"))
                        .name(resultSet.getString("employee_name"))
                        .surname(resultSet.getString("employee_surname"))
                        .patronymic(resultSet.getString("employee_patronymic"))
                        .email(resultSet.getString("employee_email"))
                        .phone(resultSet.getLong("employee_phone"))
                        .imagePath(resultSet.getString("employee_image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("employee_role")));
                ((EmployeeBuilder)employeeBuilder)
                        .experience(resultSet.getDate("employee_experience"));

                reservationBuilder
                        .id(resultSet.getInt("id"))
                        .offer(offerBuilder.build())
                        .customer(customerBuilder.build())
                        .employee(employeeBuilder.build())
                        .date(resultSet.getDate("date"));
                reservations.add(reservationBuilder.build());
            }
            return reservations;
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void update(final Reservation reservation) throws PersistentException {
        final String query = "UPDATE `reservations` SET `offer_id` = ?, `customer_id` = ?, `employee_id` = ?, `date` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try{
            statement = connection.prepareStatement(query);
            statement.setInt(1, reservation.getOffer().getId());
            statement.setInt(2, reservation.getCustomer().getId());
            statement.setInt(3, reservation.getEmployee().getId());
            statement.setDate(4, reservation.getDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);
            }
        }
    }

    @Override
    public void delete(final int id) throws PersistentException {
        final String query = "DELETE FROM `reservations` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(e);;
            }
        }
    }
}
