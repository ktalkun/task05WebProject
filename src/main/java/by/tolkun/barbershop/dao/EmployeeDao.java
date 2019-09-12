package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.exception.PersistentException;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {
    int noteNumber() throws PersistentException;
    List<Employee> readAll(int offset, int limit) throws PersistentException;
}
