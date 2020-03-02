package by.tolkun.barbershop.entity;

import javax.persistence.*;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

/**
 * Class for representation of barbershop employee.
 *
 * @author Kirill Tolkun
 */
@Entity
@Table(name = "employees")
@PrimaryKeyJoinColumn(name = "employee_id", referencedColumnName = "id")
public class Employee extends User {
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
     * Constructor without parameters
     */
    public Employee(){}

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
    @Column(name = "experience")
    @Temporal(TemporalType.DATE)
    public Date getExperience() {
        return experience;
    }

    /**
     * Set experience.
     *
     * @param inputExperience the experience of employee
     */
    public void setExperience(final Date inputExperience) {
        this.experience = inputExperience;
    }

    /**
     * Get social references.
     *
     * @return map of social references
     */
    @Transient
    public Map<String, String> getSocialRef() {
        return socialRef;
    }

    /**
     * Set social references.
     *
     * @param inputSocialRef the new social of references
     */
    public void setSocialRef(final Map<String, String> inputSocialRef) {
        socialRef = inputSocialRef;
    }

    /**
     * Get work week of employee
     *
     * @return work week of employee
     */
    @Transient
    public int[] getWorkWeek() {
        return workWeek;
    }

    /**
     * Set work week.
     *
     * @param inputWorkWeek the new work week
     */
    public void setWorkWeek(final int[] inputWorkWeek) {
        workWeek = Arrays.copyOf(inputWorkWeek, inputWorkWeek.length);
    }

    @Column(name = "work_week")
    public String getWorkWeekAsString(){
        return Arrays.stream(workWeek)
                .mapToObj(String::valueOf)
                .reduce(String::concat)
                .get();
    }

    public void setWorkWeekAsString(final String inputWorkWeek) {
        workWeek = inputWorkWeek.codePoints()
                .map(value ->
                        Character.getNumericValue((char) value))
                .toArray();
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
                && Objects.equals(experience, employee.experience)
                && Objects.equals(socialRef, employee.socialRef)
                && Objects.equals(workWeek, employee.workWeek);
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), experience, socialRef, workWeek);
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
                + "socialReferences=" + socialRef
                + "workWeek=" + workWeek
                + super.toString()
                + "} ";
    }
}
