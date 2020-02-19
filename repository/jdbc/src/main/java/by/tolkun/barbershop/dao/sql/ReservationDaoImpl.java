package by.tolkun.barbershop.dao.sql;

import by.tolkun.barbershop.dao.ReservationDao;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Repository
public class ReservationDaoImpl implements ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ReservationDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(final Reservation reservation) {
        final String query = "INSERT INTO reservations (offer_id, customer_id, employee_id, date) VALUES (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, reservation.getOffer().getId());
            ps.setInt(2, reservation.getCustomer().getId());
            ps.setInt(3, reservation.getEmployee().getId());
            ps.setTimestamp(4, new Timestamp(reservation.getDate().getTime()));
            return ps;
        }, keyHolder);

        int id;
        if (!Objects.requireNonNull(keyHolder.getKeys()).isEmpty()) {
            id = (Integer) keyHolder.getKeys().get("id");
        } else {
            id = Objects.requireNonNull(keyHolder.getKey()).intValue();
        }

        return id;
    }

    @Override
    public Reservation read(final int id) {
        final String query = "SELECT " +
                "reservations.id, " +
                "offers.id AS offer_id, " +
                "offers.name AS offer_name, " +
                "offers.description AS offer_description, " +
                "offers.image_path AS offer_image_path, " +
                "offers.price AS offer_price, " +
                "offers.period AS offer_period, " +
                "offers.is_main AS offer_is_main, " +
                "offers.is_show AS offer_is_show, " +
                "customers.id AS customer_id, " +
                "customers.login AS customer_login, " +
                "customers.password AS customer_password, " +
                "customers.name AS customer_name, " +
                "customers.surname AS customer_surname, " +
                "customers.patronymic AS customer_patronymic, " +
                "customers.email AS customer_email, " +
                "customers.phone AS customer_phone, " +
                "customers.image_path AS customer_image_path, " +
                "customers.role AS customer_role, " +
                "employees.id AS employee_id, " +
                "employees.login AS employee_login, " +
                "employees.password AS employee_password, " +
                "employees.name AS employee_name, " +
                "employees.surname AS employee_surname, " +
                "employees.patronymic AS employee_patronymic, " +
                "employees.email AS employee_email, " +
                "employees.phone AS employee_phone, " +
                "employees.image_path AS employee_image_path, " +
                "employees.role AS employee_role, " +
                "employees_info.experience AS employee_experience, " +
                "employees_info.im AS employee_im, " +
                "employees_info.fb AS employee_fb, " +
                "employees_info.vk AS employee_vk, " +
                "employees_info.work_week AS employee_work_week, " +
                "reservations.date " +
                "FROM reservations " +
                "JOIN offers ON offers.id = reservations.offer_id " +
                "JOIN users AS customers ON customers.id = reservations.customer_id " +
                "JOIN users AS employees ON employees.id = reservations.employee_id " +
                "JOIN employees AS employees_info ON employees.id = employees.id " +
                "WHERE reservations.id = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new ReservationMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Reservation> readAll() {
        final String query = "SELECT " +
                "reservations.id, " +
                "offers.id AS offer_id, " +
                "offers.name AS offer_name, " +
                "offers.description AS offer_description, " +
                "offers.image_path AS offer_image_path, " +
                "offers.price AS offer_price, " +
                "offers.period AS offer_period, " +
                "offers.is_main AS offer_is_main, " +
                "offers.is_show AS offer_is_show, " +
                "customers.id AS customer_id, " +
                "customers.login AS customer_login, " +
                "customers.password AS customer_password, " +
                "customers.name AS customer_name, " +
                "customers.surname AS customer_surname, " +
                "customers.patronymic AS customer_patronymic, " +
                "customers.email AS customer_email, " +
                "customers.phone AS customer_phone, " +
                "customers.image_path AS customer_image_path, " +
                "customers.role AS customer_role, " +
                "employees.id AS employee_id, " +
                "employees.login AS employee_login, " +
                "employees.password AS employee_password, " +
                "employees.name AS employee_name, " +
                "employees.surname AS employee_surname, " +
                "employees.patronymic AS employee_patronymic, " +
                "employees.email AS employee_email, " +
                "employees.phone AS employee_phone, " +
                "employees.image_path AS employee_image_path, " +
                "employees.role AS employee_role, " +
                "employees_info.experience AS employee_experience, " +
                "employees_info.im AS employee_im, " +
                "employees_info.fb AS employee_fb, " +
                "employees_info.vk AS employee_vk, " +
                "employees_info.work_week AS employee_work_week, " +
                "reservations.date " +
                "FROM reservations " +
                "JOIN offers ON offers.id = reservations.offer_id " +
                "JOIN users AS customers ON customers.id = reservations.customer_id " +
                "JOIN users AS employees ON employees.id = reservations.employee_id " +
                "JOIN employees AS employees_info ON employees.id = employees.id " +
                "GROUP BY reservations.id;";
        return jdbcTemplate.query(query, new ReservationMapper());
    }

    @Override
    public List<Reservation> readByCustomer(final int customerId) {
        final String query = "SELECT " +
                "DISTINCT ON (reservations.id) " +
                "reservations.id, " +
                "offers.id AS offer_id, " +
                "offers.name AS offer_name, " +
                "offers.description AS offer_description, " +
                "offers.image_path AS offer_image_path, " +
                "offers.price AS offer_price, " +
                "offers.period AS offer_period, " +
                "offers.is_main AS offer_is_main, " +
                "offers.is_show AS offer_is_show, " +
                "customers.id AS customer_id, " +
                "customers.login AS customer_login, " +
                "customers.password AS customer_password, " +
                "customers.name AS customer_name, " +
                "customers.surname AS customer_surname, " +
                "customers.patronymic AS customer_patronymic, " +
                "customers.email AS customer_email, " +
                "customers.phone AS customer_phone, " +
                "customers.image_path AS customer_image_path, " +
                "customers.role AS customer_role, " +
                "employees.id AS employee_id, " +
                "employees.login AS employee_login, " +
                "employees.password AS employee_password, " +
                "employees.name AS employee_name, " +
                "employees.surname AS employee_surname, " +
                "employees.patronymic AS employee_patronymic, " +
                "employees.email AS employee_email, " +
                "employees.phone AS employee_phone, " +
                "employees.image_path AS employee_image_path, " +
                "employees.role AS employee_role, " +
                "employees_info.experience AS employee_experience, " +
                "employees_info.im AS employee_im, " +
                "employees_info.fb AS employee_fb, " +
                "employees_info.vk AS employee_vk, " +
                "employees_info.work_week AS employee_work_week, " +
                "reservations.date " +
                "FROM reservations " +
                "JOIN offers ON offers.id = reservations.offer_id " +
                "JOIN users AS customers ON customers.id = reservations.customer_id " +
                "JOIN users AS employees ON employees.id = reservations.employee_id " +
                "JOIN employees AS employees_info ON employees.id = employees.id " +
                "WHERE reservations.customer_id = ? " +
                "GROUP BY reservations.id, offers.id, customers.id, employees.id, employees_info.experience, employees_info.im, employees_info.fb, employees_info.vk, employees_info.work_week;";
        try {
            return jdbcTemplate.query(query, new ReservationMapper(), customerId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Reservation> readByEmployee(final int employeeId) {
        final String query = "SELECT " +
                "reservations.id, " +
                "offers.id AS offer_id, " +
                "offers.name AS offer_name, " +
                "offers.description AS offer_description, " +
                "offers.image_path AS offer_image_path, " +
                "offers.price AS offer_price, " +
                "offers.period AS offer_period, " +
                "offers.is_main AS offer_is_main, " +
                "offers.is_show AS offer_is_show, " +
                "customers.id AS customer_id, " +
                "customers.login AS customer_login, " +
                "customers.password AS customer_password, " +
                "customers.name AS customer_name, " +
                "customers.surname AS customer_surname, " +
                "customers.patronymic AS customer_patronymic, " +
                "customers.email AS customer_email, " +
                "customers.phone AS customer_phone, " +
                "customers.image_path AS customer_image_path, " +
                "customers.role AS customer_role, " +
                "employees.id AS employee_id, " +
                "employees.login AS employee_login, " +
                "employees.password AS employee_password, " +
                "employees.name AS employee_name, " +
                "employees.surname AS employee_surname, " +
                "employees.patronymic AS employee_patronymic, " +
                "employees.email AS employee_email, " +
                "employees.phone AS employee_phone, " +
                "employees.image_path AS employee_image_path, " +
                "employees.role AS employee_role, " +
                "employees_info.experience AS employee_experience, " +
                "employees_info.im AS employee_im, " +
                "employees_info.fb AS employee_fb, " +
                "employees_info.vk AS employee_vk, " +
                "employees_info.work_week AS employee_work_week, " +
                "reservations.date " +
                "FROM reservations " +
                "JOIN offers ON offers.id = reservations.offer_id " +
                "JOIN users AS customers ON customers.id = reservations.customer_id " +
                "JOIN users AS employees ON employees.id = reservations.employee_id " +
                "JOIN employees AS employees_info ON employees.id = employees.id " +
                "WHERE reservations.employee_id = ? " +
                "GROUP BY reservation.id;";
        try {
            return jdbcTemplate.query(query, new ReservationMapper(), employeeId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void update(final Reservation reservation) {
        final String query = "UPDATE reservations SET offer_id = ?, customer_id = ?, employee_id = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(query, reservation.getOffer().getId(),
                reservation.getCustomer().getId(),
                reservation.getEmployee().getId(),
                new Timestamp(reservation.getDate().getTime()),
                reservation.getId());
    }

    @Override
    public void delete(final int id) {
        final String query = "DELETE FROM reservations WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
