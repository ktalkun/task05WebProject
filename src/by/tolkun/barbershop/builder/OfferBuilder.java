package by.tolkun.barbershop.builder;


import by.tolkun.barbershop.entity.Offer;

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
     * Image preview path of offer
     */
    private String imagePath;

    /**
     * Price of offer.
     */
    private float price;

    /**
     * Time period occupied by the offer.
     */
    private int period;

    /**
     * State of offer for position on page.
     */
    private boolean isMain;

    /**
     * State of offer to show on page.
     */
    private boolean isShow;

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
     * Build image path of offer.
     *
     * @param inputImagePath the image path of offer
     * @return builder
     */
    public OfferBuilder imagePath(final String inputImagePath) {
        imagePath = inputImagePath;
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
     * Build state of offer for position.
     *
     * @param inputIsMain the state of offer for position on page
     * @return builder
     */
    public OfferBuilder main(final boolean inputIsMain) {
        isMain = inputIsMain;
        return this;
    }

    /**
     * Build state of offer to show.
     *
     * @param inputIsShow the state of offer to show on page
     * @return builder
     */
    public OfferBuilder show(final boolean inputIsShow) {
        isShow = inputIsShow;
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
        offer.setImagePath(imagePath);
        offer.setPrice(price);
        offer.setPeriod(period);
        offer.setMain(isMain);
        offer.setShow(isShow);
        return offer;
    }
}
