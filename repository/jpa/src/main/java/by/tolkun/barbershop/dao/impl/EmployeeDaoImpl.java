package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
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
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDaoImpl(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public int create(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        int generatedId = (int) session.save(employee);
        session.getTransaction().commit();
        return generatedId;
    }

    @Override
    public Employee read(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), id));
        Query<Employee> query = session.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    @Override
    public List<Employee> readAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        CriteriaQuery<Employee> all = criteriaQuery.select(root);
        return session.createQuery(all).getResultList();
    }

    @Override
    public void update(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaUpdate<User> criteriaUpdate = criteriaBuilder.createCriteriaUpdate(User.class);
        Root<User> root = criteriaUpdate.from(User.class);
        criteriaUpdate
                .set("login", employee.getLogin())
                .set("password", employee.getPassword())
                .set("name", employee.getName())
                .set("surname", employee.getSurname())
                .set("patronymic", employee.getPatronymic())
                .set("email", employee.getEmail())
                .set("phone", employee.getPhone())
                .set("image_path", employee.getImagePath())
                .set("role", employee.getRole().getIdentity())
                .set("experience", employee.getExperience())
                .set("im", employee.getInstagramReferece())
                .set("fb", employee.getFacebookReference())
                .set("vk", employee.getVkontakteReference())
                .set("work_week", employee.getWorkWeekAsString());
        criteriaUpdate.where(criteriaBuilder.equal(root.get("id"), employee.getId()));

        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaUpdate).executeUpdate();
        transaction.commit();
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaDelete<Employee> criteriaDelete = criteriaBuilder.createCriteriaDelete(Employee.class);
        Root<Employee> root = criteriaDelete.from(Employee.class);
        criteriaDelete.where(criteriaBuilder.equal(root.get("id"), id));
        Transaction transaction = session.beginTransaction();
        session.createQuery(criteriaDelete).executeUpdate();
        transaction.commit();
    }

    @Override
    public int noteNumber() {
        Session session = sessionFactory.getCurrentSession();
        String countQ = "Select count (employee.id) from Employee employee";
        Query countQuery = session.createQuery(countQ);
        return ((Long) countQuery.uniqueResult()).intValue();
    }

    @Override
    public List<Employee> readAll(int offset, int limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("From Employee");
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.list();
    }
}
