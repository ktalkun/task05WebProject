package by.tolkun.barbershop.dao;

import by.tolkun.barbershop.entity.Employee;

import java.util.List;

public interface EmployeeDao extends Dao<Employee> {

    int noteNumber();

    List<Employee> readAll(int offset, int limit);
}
