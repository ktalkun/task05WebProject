package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.UserDao;
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
public class UserDaoImpl implements UserDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int create(final User user) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        int generatedId = (int) session.save(user);
        session.getTransaction().commit();
        return generatedId;
    }

    @Override
    public User read(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<User> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        CriteriaQuery<User> all = criteriaQuery.select(root);
        return session.createQuery(all).getResultList();
    }

    @Override
    public User read(String login) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login));
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public User read(String login, String password) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("login"), login),
                criteriaBuilder.equal(root.get("password"), password));
        Query<User> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate
                .set("login", user.getLogin())
                .set("password", user.getPassword())
                .set("name", user.getName())
                .set("surname", user.getSurname())
                .set("patronymic", user.getPatronymic())
                .set("email", user.getEmail())
                .set("phone", user.getPhone())
                .set("image_path", user.getImagePath())
                .set("role", user.getRole().getIdentity());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), user.getId()));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<User> criteriaDelete = criteriaBuilder.createCriteriaDelete(User.class);
        Root<User> root = criteriaDelete.from(User.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }
}
