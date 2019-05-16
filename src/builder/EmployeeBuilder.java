package builder;

import entity.Employee;

import java.sql.Date;

/**
 * Class to build {@code Employee}'s object.
 *
 * @author Kirill Tolkun
 */
public class EmployeeBuilder extends UserBuilder {

    /**
     * Date of career begin.
     */
    private Date experience;


    /**
     * Build experience.
     *
     * @param inputExperience the experience of employee
     * @return builder
     */
    public EmployeeBuilder experience(final Date inputExperience) {
        experience = inputExperience;
        return this;
    }

    /**
     * Build employee.
     *
     * @return built employee
     */
    @Override
    public Employee build() {
        Employee employee = new Employee(id);
        employee.setLogin(login);
        employee.setPassword(password);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPatronymic(patronymic);
        employee.setEmail(email);
        employee.setPhone(phone);
        employee.setRole(role);
        employee.setExperience(experience);
        return employee;
    }
}
