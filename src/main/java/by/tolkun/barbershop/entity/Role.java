package by.tolkun.barbershop.entity;

/**
 *
 */
public enum Role {
    /**
     * Administrator of the web application.
     */
    ROLE_ADMINISTRATOR("администратор"),
    /**
     * Barbershop's director.
     */
    ROLE_DIRECTOR("директор"),
    /**
     * Barbershop's employee.
     */
    ROLE_EMPLOYEE("сотрудник"),
    /**
     * Barbershop's customer.
     */
    ROLE_CUSTOMER("клиент"),
    /**
     * Barbershop's guest.
     */
    ROLE_GUEST("гость");

    /**
     * Value of enum.
     */
    private String name;

    /**
     * Private constructor with parameters.
     *
     * @param inputName of role
     */
    Role(final String inputName) {
        name = inputName;
    }

    /**
     * Get role.
     *
     * @param identity
     * @return value (name) of role
     */
    public static Role getByIdentity(final int identity) {
        return Role.values()[identity];
    }

    /**
     * Get name of role.
     *
     * @return name of role
     */
    public String getName() {
        return name;
    }

    /**
     * Get identity of role.
     *
     * @return index of role
     */
    public Integer getIdentity() {
        return ordinal();
    }
}
