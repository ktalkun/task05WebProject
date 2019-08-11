package by.tolkun.barbershop.service;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.User;
import by.tolkun.barbershop.exception.LogicException;

import java.util.List;

public interface EmployeeService extends Service {
    List<Employee> findAll() throws LogicException;

    Employee findByIdentity(int identity) throws LogicException;

    void save(Employee employee) throws LogicException;

    void delete(int identity) throws LogicException;
}
