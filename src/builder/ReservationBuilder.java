package builder;

import entity.Employee;
import entity.Reservation;
import entity.Service;
import entity.User;

import java.sql.Date;

/**
 * Class to build {@code Reservation}'s object.
 *
 * @author Kirill Tolkun
 */
public class ReservationBuilder extends EntityBuilder {

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
     * Build id of reservation.
     *
     * @param inputId of building reservation
     * @return builder
     */
    @Override
    public ReservationBuilder id(final int inputId) {
        id = inputId;
        return this;
    }

    /**
     * Build service of reservation.
     *
     * @param inputService of building reservation
     * @return builder
     */
    public ReservationBuilder service(final Service inputService) {
        service = inputService;
        return this;
    }

    /**
     * Build customer of reservation.
     *
     * @param inputCustomer of building reservation
     * @return builder
     */
    public ReservationBuilder customer(final User inputCustomer) {
        customer = inputCustomer;
        return this;
    }

    /**
     * Build employee of reservation.
     *
     * @param inputEmployee of building reservation
     * @return builder
     */
    public ReservationBuilder employee(final Employee inputEmployee) {
        employee = inputEmployee;
        return this;
    }

    /**
     * Build date of reservation.
     *
     * @param inputDate of building reservation
     * @return builder
     */
    public ReservationBuilder date(final Date inputDate) {
        date = inputDate;
        return this;
    }

    /**
     * Build reservation.
     *
     * @return built reservation
     */
    @Override
    public Reservation build() {
        Reservation reservation = new Reservation(id);
        reservation.setService(service);
        reservation.setCustomer(customer);
        reservation.setEmployee(employee);
        reservation.setDate(date);
        return reservation;
    }
}
