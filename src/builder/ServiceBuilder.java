package builder;

import entity.Service;

/**
 * Class to build {@code Service}'s object.
 *
 * @author Kirill Tolkun
 */
public class ServiceBuilder extends EntityBuilder {

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
     * Build id of service.
     *
     * @param inputId the id of service
     * @return builder
     */
    @Override
    public ServiceBuilder id(final int inputId) {
        id = inputId;
        return this;
    }

    /**
     * Build name of service.
     *
     * @param inputName the service name
     * @return builder
     */
    public ServiceBuilder name(final String inputName) {
        name = inputName;
        return this;
    }

    /**
     * Build description of service.
     *
     * @param inputDescription the service description
     * @return builder
     */
    public ServiceBuilder description(final String inputDescription) {
        description = inputDescription;
        return this;
    }

    /**
     * Build price of service.
     *
     * @param inputPrice the service price
     * @return builder
     */
    public ServiceBuilder price(final float inputPrice) {
        price = inputPrice;
        return this;
    }

    /**
     * Build period of service.
     *
     * @param inputPeriod the service period
     * @return builder
     */
    public ServiceBuilder period(final int inputPeriod) {
        period = inputPeriod;
        return this;
    }

    /**
     * Build service.
     *
     * @return built service
     */
    @Override
    public Service build() {
        Service service = new Service(id);
        service.setName(name);
        service.setDescription(description);
        service.setPrice(price);
        service.setPeriod(period);
        return service;
    }
}
