package entity;

import javafx.beans.binding.ObjectExpression;

import java.util.Objects;

/**
 * Class for review representation of web application.
 *
 * @author Kirill Tolkun
 */
public class Review extends Entity {

    /**
     * User with {@code Role.CUSTOMER}.
     */
    private User customer;

    /**
     * User with {@code Role.EMPLOYEE}.
     */
    private Employee employee;

    /**
     * Description of review.
     */
    private String description;

    /**
     * Constructor with parameters.
     *
     * @param inputId the id of the review
     */
    public Review(final int inputId) {
        super(inputId);
    }

    /**
     * Get customer.
     *
     * @return customer of review
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Set customer.
     *
     * @param inputCustomer the customer of review
     */
    public void setCustomer(final User inputCustomer) {
        customer = inputCustomer;
    }

    /**
     * Get employee.
     *
     * @return employee of review
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set employee.
     *
     * @param inputEmployee the employee of review
     */
    public void setEmployee(final Employee inputEmployee) {
        employee = inputEmployee;
    }

    /**
     * Get description.
     *
     * @return description of review
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set employee.
     *
     * @param inputDescription the description of review
     */
    public void setDescription(final String inputDescription) {
        this.description = inputDescription;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Review} object that
     * contains the same fields values as this object.
     *
     * @param o the object to compare with
     * @return {@code true} if the objects are the same;
     * {@code false} otherwiseУ
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
        Review review = (Review) o;
        return getId() == review.getId()
                && Objects.equals(customer, review.customer)
                && Objects.equals(employee, review.employee)
                && Objects.equals(description, review.description);
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, employee, description);
    }

    /**
     * Create string representation.
     *
     * @return string representation of review
     */
    @Override
    public String toString() {
        return "Review{"
                + "customer=" + customer
                + ", employee=" + employee
                + ", description=" + description
                + super.toString()
                + '}';
    }
}
