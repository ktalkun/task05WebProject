package by.tolkun.barbershop.builder;

import by.tolkun.barbershop.entity.Employee;

import java.sql.Date;
import java.util.Arrays;
import java.util.Map;

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
     * Social references of employee.
     */
    private Map<String, String> socialRef;


    /**
     * Work week of employee.
     */
    private int[] workWeek;

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
     * Build social references.
     *
     * @param inputSocialRes the new social referances
     * @return builder
     */
    public EmployeeBuilder socialRef(final Map<String, String> inputSocialRes) {
        socialRef = inputSocialRes;
        return this;
    }

    /**
     * Build work week.
     *
     * @param inputWorkWeek the new work week of employee
     * @return builder
     */
    public EmployeeBuilder workWeek(final int[] inputWorkWeek) {
        workWeek = Arrays.copyOf(inputWorkWeek, inputWorkWeek.length);
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
        employee.setImagePath(imagePath);
        employee.setExperience(experience);
        employee.setSocialRef(socialRef);
        employee.setWorkWeek(workWeek);
        return employee;
    }
}
