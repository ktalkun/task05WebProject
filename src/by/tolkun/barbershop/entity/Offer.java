package by.tolkun.barbershop.entity;

import java.util.Objects;

/**
 * Class for representation of barbershop offer.
 *
 * @author Kirill Tolkun
 */
public final class Offer extends Entity {

    /**
     * Name of offer.
     */
    private String name;

    /**
     * Description of offer.
     */
    private String description;

    /**
     * Price of offer.
     */
    private float price;

    /**
     * Time period occupied by the offer.
     */
    private int period;

    /**
     * Constructor with parameters.
     *
     * @param inputId of the offer
     */
    public Offer(final int inputId) {
        super(inputId);
    }

    /**
     * Get name.
     *
     * @return name of offer
     */
    public String getName() {
        return name;
    }

    /**
     * Set name.
     *
     * @param inputName the new name of offer
     */
    public void setName(final String inputName) {
        name = inputName;
    }

    /**
     * Get description.
     *
     * @return description of offer
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description.
     *
     * @param inputDescription the new description of offer
     */
    public void setDescription(final String inputDescription) {
        description = inputDescription;
    }

    /**
     * Get price.
     *
     * @return price of offer
     */
    public float getPrice() {
        return price;
    }

    /**
     * Set price.
     *
     * @param inputPrice the new price of offer
     */
    public void setPrice(final float inputPrice) {
        price = inputPrice;
    }

    /**
     * Get period.
     *
     * @return period of offer
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Set period.
     *
     * @param inputPeriod the new period of offer
     */
    public void setPeriod(final int inputPeriod) {
        period = inputPeriod;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Offer} object that
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
        Offer offer = (Offer) o;
        return getId() == offer.getId()
                && Float.compare(offer.price, price) == 0
                && period == offer.period
                && Objects.equals(name, offer.name)
                && Objects.equals(description, offer.description);
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
     * @return string representation of offer
     */
    @Override
    public String toString() {
        return "Offer{"
                + "name='" + name + '\''
                + ", description='" + description + '\''
                + ", price=" + price
                + ", period=" + period
                + super.toString()
                + "} ";
    }
}
