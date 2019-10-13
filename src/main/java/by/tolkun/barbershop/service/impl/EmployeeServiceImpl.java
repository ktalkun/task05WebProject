package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER
            = LogManager.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(final EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> findAll() throws LogicException {
        try {
            return employeeDao.readAll();
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public List<Employee> findAll(int offset, int limit) throws LogicException {
        try {
            return employeeDao.readAll(offset, limit);
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }

    @Override
    public Employee findByIdentity(int identity) throws LogicException {
        try {
            return employeeDao.read(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void save(Employee employee) throws LogicException {
        try {
            if (employee.getId() != 0) {
                if (employee.getPassword() == null) {
                    User oldUser = employeeDao.read(employee.getId());
                    employee.setPassword(oldUser.getPassword());
                }
                employeeDao.update(employee);
            } else {
                employee.setId(employeeDao.create(employee));
            }
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public void delete(int identity) throws LogicException {
        try {
            employeeDao.delete(identity);
        } catch (PersistentException e) {
            throw new LogicException((e));
        }
    }

    @Override
    public int noteNumber() throws LogicException {
        try {
            return employeeDao.noteNumber();
        } catch (PersistentException e) {
            throw new LogicException(e);
        }
    }
}
