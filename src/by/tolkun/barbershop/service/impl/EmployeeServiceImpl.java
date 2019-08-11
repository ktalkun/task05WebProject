package by.tolkun.barbershop.service.impl;

import by.tolkun.barbershop.dao.DAOFactory;
import by.tolkun.barbershop.dao.EmployeeDao;
import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;
import by.tolkun.barbershop.exception.PersistentException;
import by.tolkun.barbershop.service.EmployeeService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER
            = LogManager.getLogger(EmployeeServiceImpl.class);

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(){
        employeeDao = DAOFactory
                .getInstance()
                .getEmployeeDao();
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
}
