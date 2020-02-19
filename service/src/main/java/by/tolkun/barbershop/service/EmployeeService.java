package by.tolkun.barbershop.service;

//import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Employee;

import java.util.List;

public interface EmployeeService extends Service {
    List<Employee> findAll();

    List<Employee> findAll(int offset, int limit);

    Employee findByIdentity(int identity);

    void save(Employee employee);

    void delete(int identity);

    int noteNumber();
}
