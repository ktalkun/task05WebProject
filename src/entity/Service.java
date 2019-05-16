package entity;

import java.util.Objects;

/**
 * Class for representation of barbershop service.
 *
 * @author Kirill Tolkun
 */
public final class Service extends Entity {

    /**
     * Name of service.
     */
    private String name;

    /**
     * Description of service.
     */
    private String description;

    /**
     * Price of service.
     */
    private float price;

    /**
     * Time period occupied by the service.
     */
    private int period;

    /**
     * Constructor with parameters.
     *
     * @param inputId of the service
     */
    public Service(final int inputId) {
        super(inputId);
    }

    /**
     * Get name.
     *
     * @return name of service
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param inputName the new name of service
     */
    public void setName(final String inputName) {
        name = inputName;
    }

    /**
     * Get description.
     *
     * @return description of service
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param inputDescription the new description of service
     */
    public void setDescription(final String inputDescription) {
        description = inputDescription;
    }

    /**
     * Get price.
     *
     * @return price of service
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set price.
     *
     * @param inputPrice the new price of service
     */
    public void setPrice(final float inputPrice) {
        price = inputPrice;
    }

    /**
     * Get period.
     *
     * @return period of service
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Set period.
     *
     * @param inputPeriod the new period of service
     */
    public void setPeriod(final int inputPeriod) {
        period = inputPeriod;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Service} object that
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
        Service service = (Service) o;
        return getId() == service.getId()
                && Float.compare(service.price, price) == 0
                && period == service.period
                && Objects.equals(name, service.name)
                && Objects.equals(description, service.description);
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, price, period);
    }

    /**
     * Create string representation.
     *
     * @return string representation of service
     */
    @Override
    public String toString() {
        return "Service{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", period=" + period
                + super.toString()
                + "} ";
    }
}
