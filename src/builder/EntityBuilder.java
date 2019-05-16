package builder;

import entity.Entity;

/**
 * Abstract class to build entity classes.
 *
 * @author Kirill Tolkun
 */
public abstract class EntityBuilder {

    /**
     * Id of entity.
     */
    protected int id;

    /**
     * Build id of entity.
     *
     * @param inputId the id of entity
     * @return builder
     */
    public abstract EntityBuilder id(int inputId);

    /**
     * Build entity.
     *
     * @return built entity
     */
    public abstract Entity build();
}
