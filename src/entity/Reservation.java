package entity;

import java.sql.Date;
import java.util.Objects;

/**
 * Class for reservation representation of web application.
 *
 * @author Kirill Tolkun
 */
public final class Reservation extends Entity {

    /**
     * Service of reservation.
     */
    private Service service;

    /**
     * User with {@code Role.CUSTOMER}.
     */
    private User customer;

    /**
     * User with {@code Role.EMPLOYEE}.
     */
    private Employee employee;

    /**
     * Date of reservation.
     */
    private Date date;

    /**
     * Constructor with parameters.
     *
     * @param inputId the id of the reservation
     */
    public Reservation(final int inputId) {
        super(inputId);
    }

    /**
     * Get service.
     *
     * @return service of reservation
     */
    public Service getService() {
        return service;
    }

    /**
     * Set service.
     *
     * @param inputService the service of reservation
     */
    public void setService(final Service inputService) {
        service = inputService;
    }

    /**
     * Get customer.
     *
     * @return customer of reservation
     */
    public User getCustomer() {
        return customer;
    }

    /**
     * Set customer.
     *
     * @param inputCustomer the customer of reservation
     */
    public void setCustomer(final User inputCustomer) {
        customer = inputCustomer;
    }

    /**
     * Get employee.
     *
     * @return employee of reservation
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     * Set employee.
     *
     * @param inputEmployee the employee of reservation
     */
    public void setEmployee(final Employee inputEmployee) {
        employee = inputEmployee;
    }

    /**
     * Get date.
     *
     * @return date of reservation
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set date.
     *
     * @param inputDate the date of reservation
     */
    public void setDate(final Date inputDate) {
        date = inputDate;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Reservation} object that
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
        Reservation that = (Reservation) o;
        return getId() == that.getId()
                && service == that.service
                && customer == that.customer
                && employee == that.employee
                && Objects.equals(date, that.date);
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(
                super.hashCode(),
                service,
                customer,
                employee,
                date
        );
    }

    /**
     * Create string representation.
     *
     * @return string representation of reservation
     */
    @Override
    public String toString() {
        return "Reservation{"
                + "service=" + service
                + ", customer=" + customer
                + ", employee=" + employee
                + ", date=" + date
                + super.toString()
                + "} ";
    }
}
