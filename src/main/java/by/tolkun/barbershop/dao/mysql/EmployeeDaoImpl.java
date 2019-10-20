package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.mapper.EmployeeMapper;
import by.tolkun.barbershop.mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Repository
public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao {

    private static final Logger LOGGER
            = LogManager.getLogger(EmployeeDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Employee employee) throws PersistentException {
        final String query = "INSERT INTO `employees` (`experience`, `im`, `fb`, `vk`, `work_week`) VALUES (?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, employee.getExperience());
            ps.setString(2, employee.getSocialRef().get("im"));
            ps.setString(3, employee.getSocialRef().get("fb"));
            ps.setString(4, employee.getSocialRef().get("vk"));
            ps.setString(5, Arrays.stream(employee.getWorkWeek())
                    .mapToObj(String::valueOf)
                    .reduce(String::concat)
                    .get());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Employee read(int id) throws PersistentException {
        final String query = "SELECT users.id," +
                "users.login," +
                "users.password," +
                "users.name," +
                "users.surname," +
                "users.patronymic," +
                "users.email," +
                "users.phone," +
                "users.image_path," +
                "users.role," +
                "employees.experience AS experience," +
                "employees.im AS im," +
                "employees.fb AS fb," +
                "employees.vk AS vk," +
                "employees.work_week AS work_week " +
                "FROM users " +
                "JOIN employees AS employees ON employees.employee_id = users.id " +
                "WHERE `role` = " + Role.EMPLOYEE.getIdentity() + "&&" + " `id` = ?;";
        try {
            return jdbcTemplate.queryForObject(query, new EmployeeMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Employee> readAll() throws PersistentException {
        final String query = "SELECT users.id,\n" +
                "users.login," +
                "users.password," +
                "users.name," +
                "users.surname," +
                "users.patronymic," +
                "users.email," +
                "users.phone," +
                "users.image_path," +
                "users.role," +
                "employees.experience AS experience," +
                "employees.im AS im," +
                "employees.fb AS fb," +
                "employees.vk AS vk," +
                "employees.work_week AS work_week " +
                "FROM users " +
                "JOIN employees AS employees ON employees.employee_id = users.id " +
                "WHERE `role` = " + Role.EMPLOYEE.getIdentity();
        return jdbcTemplate.query(query, new EmployeeMapper());
    }

    @Override
    public void update(Employee employee) throws PersistentException {
        final String query = "UPDATE `employees` SET `experience`= ?, `im` = ?, `fb` = ?, `vk` = ?, `work_week` = ? WHERE `id` = ?";
        jdbcTemplate.update(query, employee.getExperience(),
                employee.getSocialRef().get("im"),
                employee.getSocialRef().get("fb"),
                employee.getSocialRef().get("vk"),
                Arrays.stream(employee.getWorkWeek())
                        .mapToObj(String::valueOf)
                        .reduce(String::concat)
                        .get(),
                employee.getId());
    }

    @Override
    public void delete(int id) throws PersistentException {
        final String query = "DELETE FROM `employees` WHERE `employee_id` = ?";
        jdbcTemplate.update(query, id);
    }

    @Override
    public int noteNumber() throws PersistentException {
        final String query = "SELECT COUNT(`employee_id`) AS `count` FROM `employees`;";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        return count != null ? count : 0;
    }

    @Override
    public List<Employee> readAll(int offset, int limit) throws PersistentException {
        final String query = "SELECT users.id,\n" +
                "users.login," +
                "users.password," +
                "users.name," +
                "users.surname," +
                "users.patronymic," +
                "users.email," +
                "users.phone," +
                "users.image_path," +
                "users.role," +
                "employees.experience AS experience," +
                "employees.im AS im," +
                "employees.fb AS fb," +
                "employees.vk AS vk," +
                "employees.work_week AS work_week " +
                "FROM users " +
                "JOIN employees AS employees ON employees.employee_id = users.id " +
                "WHERE `role` = " + Role.EMPLOYEE.getIdentity() + " " +
                "ORDER BY users.id " +
                "LIMIT ?, ?;";
        return jdbcTemplate.query(query, new EmployeeMapper(), offset, limit);
    }
}
