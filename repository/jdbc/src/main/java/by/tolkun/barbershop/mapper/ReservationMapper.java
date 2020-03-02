package by.tolkun.barbershop.mapper;

import by.tolkun.barbershop.builder.EmployeeBuilder;
import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.builder.ReservationBuilder;
import by.tolkun.barbershop.builder.UserBuilder;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReservationMapper implements RowMapper<Reservation> {
    @Override
    public Reservation mapRow(ResultSet resultSet, int i) throws SQLException {
        OfferBuilder offerBuilder = new OfferBuilder()
                .id(resultSet.getInt("offer_id"))
                .name(resultSet.getString("offer_name"))
                .description(resultSet.getString("offer_description"))
                .imagePath(resultSet.getString("offer_image_path"))
                .price(resultSet.getFloat("offer_price"))
                .period(resultSet.getInt("offer_period"))
                .main(resultSet.getBoolean("offer_is_main"))
                .show(resultSet.getBoolean("offer_is_show"));
        UserBuilder customerBuilder = new UserBuilder()
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
        EmployeeBuilder employeeBuilder = (EmployeeBuilder)new EmployeeBuilder()
                .experience(resultSet.getDate("employee_experience"))
                .instagramReference(resultSet.getString("employee_im"))
                .facebookReference(resultSet.getString("employee_fb"))
                .vkontakteReference(resultSet.getString("employee_vk"))
                .workWeek(resultSet
                        .getString("employee_work_week")
                        .codePoints()
                        .map(value ->
                                Character.getNumericValue((char) value))
                        .toArray())
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

        return new ReservationBuilder()
                .id(resultSet.getInt("id"))
                .offer(offerBuilder.build())
                .customer(customerBuilder.build())
                .employee(employeeBuilder.build())
                .date(new Date(resultSet.getTimestamp("date").getTime()))
                .build();
    }
}
