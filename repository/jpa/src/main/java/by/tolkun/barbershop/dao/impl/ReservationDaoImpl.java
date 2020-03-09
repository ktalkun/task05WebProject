package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.ReservationDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Offer;
import by.tolkun.barbershop.entity.Reservation;
import by.tolkun.barbershop.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
@Transactional
public class ReservationDaoImpl implements ReservationDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ReservationDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int create(final Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
//        session.beginTransaction();
        int generatedId = (int) session.save(reservation);
//        session.getTransaction().commit();
        return generatedId;
    }

    @Override
    public Reservation read(final int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        Query<Reservation> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<Reservation> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        CriteriaQuery<Reservation> all = criteriaQuery.select(root);
        return session.createQuery(all).getResultList();
    }

    @Override
    public List<Reservation> readByCustomer(final int customerId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
//        Fetch<Reservation, Offer> offerFetch = root.fetch("offers", JoinType.INNER);
//        Fetch<Reservation, User> userFetch = root.fetch("users", JoinType.INNER);
//        Fetch<Reservation, Employee> employeeFetch = root.fetch("employees", JoinType.INNER);
//        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("customer_id"), customerId));
        Query<Reservation> query = session.createQuery(criteriaQuery);
        return query.getResultList();

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
//        Root<UserEntity> user = cq.from(UserEntity.class);
//
//        // Fetch User --> Address (wished relation, without nested @OneToOne relations)
//        Fetch<UserEntity, AddressEntity> addressFetch = user.fetch(UserEntity_.address, JoinType.LEFT);
//        // Fetch User --> Badge (wished relation, without nested @OneToOne relations)
//        Fetch<UserEntity, BadgeEntity> badgeFetch = user.fetch(UserEntity_.badge, JoinType.LEFT);
//
//        List<Predicate> predicates = new ArrayList<Predicate>();
//
//        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()]))).distinct(true);
//        TypedQuery<UserEntity> query = em.createQuery(cq);
//        List<UserEntity> result = query.getResultList();
    }

    @Override
    public List<Reservation> readByEmployee(final int employeeId) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Reservation> criteriaQuery = criteriaBuilder.createQuery(Reservation.class);
        Root<Reservation> root = criteriaQuery.from(Reservation.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("employee_id"), employeeId));
        Query<Reservation> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public void update(final Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<Reservation> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(Reservation.class);
        Root<Reservation> root = criteriaUpdate.from(Reservation.class);
        criteriaUpdate
                .set("reservation_id", reservation.getOffer().getId())
                .set("customer_id", reservation.getCustomer().getId())
                .set("employee_id", reservation.getEmployee().getId())
                .set("date", reservation.getDate());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), reservation.getId()));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

    @Override
    public void delete(final int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Reservation> criteriaDelete = criteriaBuilder.createCriteriaDelete(Reservation.class);
        Root<Reservation> root = criteriaDelete.from(Reservation.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }
}
