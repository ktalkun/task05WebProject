package by.tolkun.barbershop.mapper;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EmployeeMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
        return ((EmployeeBuilder) new EmployeeBuilder()
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
                .instagramReference(resultSet.getString("im"))
                .facebookReference(resultSet.getString("fb"))
                .vkontakteReference(resultSet.getString("vk"))
                .workWeek(resultSet
                        .getString("work_week")
                        .codePoints()
                        .map(value ->
                                Character.getNumericValue((char) value))
                        .toArray())
                .build();
    }
}
