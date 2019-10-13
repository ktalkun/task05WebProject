package by.tolkun.barbershop.dao.mysql;

import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.mapper.OfferMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class OfferDaoImpl extends BaseDaoImpl implements OfferDao {

    private static final Logger LOGGER
            = LogManager.getLogger(OfferDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public OfferDaoImpl(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(final Offer offer) throws PersistentException {
        final String query = "INSERT INTO `offers` (`name`, `description`, `image_path`, `price`, `period`, `is_main`, `is_show`) VALUES (?, ?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection1 -> {
            PreparedStatement ps = connection1.prepareStatement(query);
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
    public Offer read(final int id) throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `image_path`, `price`, `period`, `is_main`, `is_show` FROM `offers` WHERE `id` = ?";
        return jdbcTemplate.queryForObject(query, new OfferMapper(), id);
    }

    @Override
    public List<Offer> readAll() throws PersistentException {
        final String query = "SELECT `id`, `name`, `description`, `image_path`, `price`, `period`, `is_main`, `is_show` FROM `offers`";
        return jdbcTemplate.query(query, new OfferMapper());
    }

    @Override
    public void update(final Offer offer) throws PersistentException {
        final String query = "UPDATE `offers` SET `name` = ?, `description` = ?, `image_path` = ?, `price` = ?, `period` = ?, `is_main` = ?, `is_show` = ? WHERE `id` = ?";
        jdbcTemplate.update(query, offer.getName(), offer.getDescription(),
                offer.getImagePath(), offer.getPrice(), offer.getPeriod(),
                offer.isMain(), offer.isShow(), offer.getId());
    }

    @Override
    public void delete(final int id) throws PersistentException {
        final String query = "DELETE FROM `offers` WHERE `id` = ?";
        jdbcTemplate.update(query, id);
    }
}
