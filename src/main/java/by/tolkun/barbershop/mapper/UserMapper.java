package by.tolkun.barbershop.mapper;

import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserBuilder()
                .id(resultSet.getInt("id"))
                .login(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .name(resultSet.getString("name"))
                .surname(resultSet.getString("surname"))
                .patronymic(resultSet.getString("patronymic"))
                .email(resultSet.getString("email"))
                .phone(resultSet.getLong("phone"))
                .imagePath(resultSet.getString("image_path"))
                .role(Role.getByIdentity(resultSet.getInt("role")))
                .build();
    }
}
