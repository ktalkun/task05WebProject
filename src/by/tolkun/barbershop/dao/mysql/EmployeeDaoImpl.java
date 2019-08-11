package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.dao.DAOFactory;
import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.dao.UserDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeDaoImpl extends BaseDaoImpl implements EmployeeDao {

    private static final Logger LOGGER
            = LogManager.getLogger(EmployeeDaoImpl.class);

    @Override
    public int create(Employee employee) throws PersistentException {
        UserDao userDao = DAOFactory
                .getInstance()
                .getUserDao();
        userDao.create(employee);
        final String query = "INSERT INTO `employees` (`experience`, `im`, `fb`, `vk`, `work_week`) VALUES (?, ?, ?, ?, ?)";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setDate(1, employee.getExperience());
            statement.setString(2, employee.getSocialRef().get("im"));
            statement.setString(3, employee.getSocialRef().get("fb"));
            statement.setString(4, employee.getSocialRef().get("vk"));
            String workWeek = Arrays.stream(employee.getWorkWeek())
                    .mapToObj(String::valueOf)
                    .reduce(String::concat)
                    .get();
            statement.setString(5, workWeek);
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("No index autoincrement after insert into `offers`");
                throw new PersistentException("No index autoincrement after insert into `offers`");
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
                "WHERE `role` = " + Role.EMPLOYEE.getIdentity() + " `id` = ?;";
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            if (resultSet.next()) {
                return ((EmployeeBuilder) employeeBuilder
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("role"))))
                        .experience(resultSet.getDate("experience"))
                        .socialRef(Stream.of(new String[][]{
                                {"im", resultSet.getString("im")},
                                {"fb", resultSet.getString("fb")},
                                {"vk", resultSet.getString("vk")}
                        }).collect(Collectors.toMap(e -> e[0], e -> e[1])))
                        .workWeek(resultSet
                                .getString("work_week")
                                .codePoints()
                                .map(value ->
                                        Character.getNumericValue((char) value))
                                .toArray())
                        .build();
            } else {
                LOGGER.warn("No employee note with id {}", id);
            }
            return null;
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
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            EmployeeBuilder employeeBuilder = new EmployeeBuilder();
            List<Employee> employees = new ArrayList<>();
            while (resultSet.next()) {
                employeeBuilder
                        .experience(resultSet.getDate("experience"))
                        .socialRef(Stream.of(new String[][]{
                                {"im", resultSet.getString("im")},
                                {"fb", resultSet.getString("fb")},
                                {"vk", resultSet.getString("vk")}
                        }).collect(Collectors.toMap(e -> e[0], e -> e[1])))
                        .workWeek(resultSet
                                .getString("work_week")
                                .codePoints()
                                .map(value ->
                                        Character.getNumericValue((char) value))
                                .toArray())
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .patronymic(resultSet.getString("patronymic"))
                        .email(resultSet.getString("email"))
                        .phone(resultSet.getLong("phone"))
                        .imagePath(resultSet.getString("image_path"))
                        .role(Role.getByIdentity(resultSet.getInt("role")));

                LOGGER.debug(employeeBuilder.build());
                employees.add(employeeBuilder.build());
            }
            return employees;
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
    public void update(Employee employee) throws PersistentException {
        final String query = "UPDATE `employees` SET `experience`= ?, `im` = ?, `fb` = ?, `vk` = ?, `work_week` = ? WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setDate(1, employee.getExperience());
            statement.setString(2, employee.getSocialRef().get("im"));
            statement.setString(3, employee.getSocialRef().get("fb"));
            statement.setString(4, employee.getSocialRef().get("vk"));
            String workWeek = Arrays.stream(employee.getWorkWeek())
                    .mapToObj(String::valueOf)
                    .reduce(String::concat)
                    .get();
            statement.setString(5, workWeek);
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
    public void delete(int id) throws PersistentException {
        final String query = "DELETE FROM `employees` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
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
}
