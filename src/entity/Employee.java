package entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Class for representation of barbershop employee.
 *
 * @author Kirill Tolkun
 */
public class Employee extends User {
    /**
     * Date of career begin.
     */
    private Date experience;

    /**
     * Constructor with parameters.
     *
     * @param inputId of the user
     */
    public Employee(final int inputId) {
        super(inputId);
    }

    /**
     * Get experience.
     *
     * @return experience of employee
     */
    public Date getExperience() {
        return experience;
    }

    /**
     * Set experience.
     *
     * @param inputExperience the experience of employee
     */
    public void setExperience(final Date inputExperience) {
        this.experience = experience;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Employee} object that
     * contains the same fields values as this object.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are the same;
     * {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Employee employee = (Employee) o;
        return getId() == employee.getId()
                && Objects.equals(experience, employee.experience);
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience);
    }

    /**
     * Create string representation.
     *
     * @return string representation of employee
     */
    @Override
    public String
    toString() {
        return "Employee{"
                + "experience=" + experience
                + super.toString()
                + "} ";
    }
}
