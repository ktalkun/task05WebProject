package by.tolkun.barbershop.dao.impl;

import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    public EmployeeDaoImpl() {
    }

    @Override
    public int create(Employee employee) {
        return 0;
    }

    @Override
    public Employee read(int id) {
        return null;
    }

    @Override
    public List<Employee> readAll() {
        return null;
    }

    @Override
    public void update(Employee employee) {
    }

    @Override
    public void delete(int id) {
    }

    @Override
    public int noteNumber() {
        return 0;
    }

    @Override
    public List<Employee> readAll(int offset, int limit) {
        return null;
    }
}
