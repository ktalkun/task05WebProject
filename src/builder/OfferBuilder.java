package builder;


import entity.Offer;

/**
 * Class to build {@code Offer}'s object.
 *
 * @author Kirill Tolkun
 */
public class OfferBuilder extends EntityBuilder {

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
     * Build id of offer.
     *
     * @param inputId the id of offer
     * @return builder
     */
    @Override
    public OfferBuilder id(final int inputId) {
        id = inputId;
        return this;
    }

    /**
     * Build name of offer.
     *
     * @param inputName the offer name
     * @return builder
     */
    public OfferBuilder name(final String inputName) {
        name = inputName;
        return this;
    }

    /**
     * Build description of offer.
     *
     * @param inputDescription the offer description
     * @return builder
     */
    public OfferBuilder description(final String inputDescription) {
        description = inputDescription;
        return this;
    }

    /**
     * Build price of offer.
     *
     * @param inputPrice the offer price
     * @return builder
     */
    public OfferBuilder price(final float inputPrice) {
        price = inputPrice;
        return this;
    }

    /**
     * Build period of offer.
     *
     * @param inputPeriod the offer period
     * @return builder
     */
    public OfferBuilder period(final int inputPeriod) {
        period = inputPeriod;
        return this;
    }

    /**
     * Build offer.
     *
     * @return built offer
     */
    @Override
    public Offer build() {
        Offer offer = new Offer(id);
        offer.setName(name);
        offer.setDescription(description);
        offer.setPrice(price);
        offer.setPeriod(period);
        return offer;
    }
}
