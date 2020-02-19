package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.readAll();
    }

    @Override
    public List<Employee> findAll(int offset, int limit) {
        return employeeDao.readAll(offset, limit);
    }

    @Override
    public Employee findByIdentity(int identity) {
        return employeeDao.read(identity);
    }

    @Override
    public void save(Employee employee) {
        if (employee.getId() != 0) {
            if (employee.getPassword() == null) {
                User oldUser = employeeDao.read(employee.getId());
                employee.setPassword(oldUser.getPassword());
            }
            employeeDao.update(employee);
        } else {
            employee.setId(employeeDao.create(employee));
        }
    }

    @Override
    public void delete(int identity) {
        employeeDao.delete(identity);
    }

    @Override
    public int noteNumber() {
        return employeeDao.noteNumber();
    }
}
