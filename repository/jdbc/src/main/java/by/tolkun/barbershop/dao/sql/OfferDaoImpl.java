package by.tolkun.barbershop.dao.sql;

import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.mapper.OfferMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class OfferDaoImpl implements OfferDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OfferDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(final Offer offer) {
        final String query = "INSERT INTO offers (name, description, image_path, price, period, is_main, is_show) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, offer.getName());
            ps.setString(2, offer.getDescription());
            ps.setString(3, offer.getImagePath());
            ps.setFloat(4, offer.getPrice());
            ps.setInt(5, offer.getPeriod());
            ps.setBoolean(6, offer.isMain());
            ps.setBoolean(7, offer.isShow());
            return ps;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public Offer read(final int id) {
        final String query = "SELECT id, name, description, image_path, price, period, is_main, is_show FROM offers WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(query, new OfferMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Offer> readAll() {
        final String query = "SELECT id, name, description, image_path, price, period, is_main, is_show FROM offers";
        return jdbcTemplate.query(query, new OfferMapper());
    }

    @Override
    public void update(final Offer offer) {
        final String query = "UPDATE offers SET name = ?, description = ?, image_path = ?, price = ?, period = ?, is_main = ?, is_show = ? WHERE id = ?";
        jdbcTemplate.update(query, offer.getName(), offer.getDescription(),
                offer.getImagePath(), offer.getPrice(), offer.getPeriod(),
                offer.isMain(), offer.isShow(), offer.getId());
    }

    @Override
    public void delete(final int id) {
        final String query = "DELETE FROM offers WHERE id = ?";
        jdbcTemplate.update(query, id);
    }
}
