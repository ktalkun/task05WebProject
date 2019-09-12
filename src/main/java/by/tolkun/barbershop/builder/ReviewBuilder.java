package by.tolkun.barbershop.builder;

import by.tolkun.barbershop.entity.Employee;
import by.tolkun.barbershop.entity.Review;
import by.tolkun.barbershop.entity.User;

/**
 * Class to build {@code Review}'s object.
 *
 * @author Kirill Tolkun
 */
public class ReviewBuilder extends EntityBuilder {

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
     * Build id of review.
     *
     * @param inputId the id of building review
     * @return builder
     */
    @Override
    public ReviewBuilder id(final int inputId) {
        id = inputId;
        return this;
    }

    /**
     * Build customer of review.
     *
     * @param inputCustomer the customer of building review
     * @return builder
     */
    public ReviewBuilder customer(final User inputCustomer) {
        customer = inputCustomer;
        return this;
    }

    /**
     * Build employee of review.
     *
     * @param inputEmployee the employee of building review
     * @return builder
     */
    public ReviewBuilder employee(final Employee inputEmployee) {
        employee = inputEmployee;
        return this;
    }

    /**
     * Build description of review.
     *
     * @param inputDescription the description of building review
     * @return builder
     */
    public ReviewBuilder description(final String inputDescription) {
        description = inputDescription;
        return this;
    }

    /**
     * Build review.
     *
     * @return built review
     */
    @Override
    public Review build() {
        Review review = new Review(id);
        review.setCustomer(customer);
        review.setEmployee(employee);
        review.setDescription(description);
        return review;
    }
}
