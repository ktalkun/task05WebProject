package entity;

import java.util.Objects;

/**
 * Abstract class of entities.
 *
 * @author Kirill Tolkun
 */
public abstract class Entity {
    /**
     * Unique identifier of entity.
     */
    private int id;

    /**
     * Constructor with parameters.
     *
     * @param inputId of new entity
     */
    public Entity(final int inputId) {
        id = inputId;
    }

    /**
     * Get id.
     *
     * @return id of entity
     */
    public int getId() {
        return id;
    }

    /**
     * Set new id.
     *
     * @param inputId the new id of entity
     */
    public void setId(final int inputId) {
        id = inputId;
    }

    /**
     * Compares this object to the specified object. The result is
     * {@code true} if the argument is not
     * {@code null} and is an {@code Entity} object that
     * contains the same id as this object.
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
        Entity entity = (Entity) o;
        return id == entity.id;
    }

    /**
     * Returns a hash code.
     *
     * @return a hash code value
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Create string representation.
     *
     * @return string representation of entity
     */
    @Override
    public String toString() {
        return "Entity{"
                + "id=" + id
                + '}';
    }
}
