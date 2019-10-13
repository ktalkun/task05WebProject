package by.tolkun.barbershop.mapper;

import by.tolkun.barbershop.builder.OfferBuilder;
import by.tolkun.barbershop.entity.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferMapper implements RowMapper<Offer> {
    @Override
    public Offer mapRow(ResultSet resultSet, int i) throws SQLException {
        return new OfferBuilder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .description(resultSet.getString("description"))
                .imagePath(resultSet.getString("image_path"))
                .price(resultSet.getFloat("price"))
                .period(resultSet.getInt("period"))
                .main(resultSet.getBoolean("is_main"))
                .show(resultSet.getBoolean("is_show"))
                .build();
    }
}
