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
     * Social reference for instagram
     */
    private String instagramReferece;

    /**
     * Social reference for facebook
     */
    private String facebookReference;

    /**
     * Social reference for vkontakte
     */
    private String vkontakteReference;

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
     * Build social references of facebook.
     *
     * @param inputFacebookReference the new social referances of facebook
     * @return builder
     */
    public EmployeeBuilder facebookReference(final String inputFacebookReference) {
        facebookReference = inputFacebookReference;
        return this;
    }

    /**
     * Build social references of vkontakte.
     *
     * @param inputVkontakteReference the new social referances of vkontakte
     * @return builder
     */
    public EmployeeBuilder vkontakteReference(final String inputVkontakteReference) {
        vkontakteReference = inputVkontakteReference;
        return this;
    }

    /**
     * Build social references of instagram.
     *
     * @param inputInstagramReference the new social referances of instagram
     * @return builder
     */
    public EmployeeBuilder instagramReference(final String inputInstagramReference) {
        instagramReferece = inputInstagramReference;
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
        employee.setInstagramReferece(instagramReferece);
        employee.setFacebookReference(facebookReference);
        employee.setVkontakteReference(vkontakteReference);
        employee.setWorkWeek(workWeek);
        return employee;
    }
}
