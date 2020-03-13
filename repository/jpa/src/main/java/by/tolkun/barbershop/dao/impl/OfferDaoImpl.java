package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.OfferDao;
import by.tolkun.barbershop.entity.Offer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class OfferDaoImpl implements OfferDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public OfferDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int create(final Offer offer) {
        Session session = sessionFactory.getCurrentSession();
        int generatedId = (int) session.save(offer);
        return generatedId;
    }

    @Override
    public Offer read(final int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        Query<Offer> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<Offer> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Offer> criteriaQuery = criteriaBuilder.createQuery(Offer.class);
        Root<Offer> root = criteriaQuery.from(Offer.class);
        CriteriaQuery<Offer> all = criteriaQuery.select(root);
        return session.createQuery(all).getResultList();
    }

    @Override
    public void update(final Offer offer) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<Offer> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Offer.class);
        Root<Offer> root = criteriaUpdate.from(Offer.class);
        criteriaUpdate.set("name", offer.getName())
                .set("description", offer.getDescription())
                .set("image_path", offer.getImagePath())
                .set("price", offer.getPrice())
                .set("period", offer.getPeriod())
                .set("is_main", offer.isMain())
                .set("is_show", offer.isShow());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), offer.getId()));
        session.createQuery(criteriaUpdate).executeUpdate();
    }

    @Override
    public void delete(final int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Offer> criteriaDelete = criteriaBuilder.createCriteriaDelete(Offer.class);
        Root<Offer> root = criteriaDelete.from(Offer.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        session.createQuery(criteriaDelete).executeUpdate();
    }
}
