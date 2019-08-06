package by.tolkun.barbershop.builder;

import by.tolkun.barbershop.entity.Role;
import by.tolkun.barbershop.entity.User;

/**
 * Class to build {@code User}'s object.
 *
 * @author Kirill Tolkun
 */
public class UserBuilder extends EntityBuilder {

    /**
     * Login of user.
     */
    protected String login;

    /**
     * Password of user.
     */
    protected String password;

    /**
     * Name of user.
     */
    protected String name;

    /**
     * Surname of user.
     */
    protected String surname;

    /**
     * Patronymic of user.
     */
    protected String patronymic;

    /**
     * Email of user.
     */
    protected String email;

    /**
     * Phone of user.
     */
    protected long phone;

    /**
     * Role of user.
     */
    protected Role role;

    /**
     * Path of user's image.
     */
    protected String imagePath;

    /**
     * Build id of user.
     *
     * @param inputId of building user
     * @return builder
     */
    @Override
    public UserBuilder id(final int inputId) {
        id = inputId;
        return this;
    }

    /**
     * Build login of user.
     *
     * @param inputLogin of building user
     * @return builder
     */
    public UserBuilder login(final String inputLogin) {
        login = inputLogin;
        return this;
    }

    /**
     * Build password of user.
     *
     * @param inputPassword of building user
     * @return builder
     */
    public UserBuilder password(final String inputPassword) {
        password = inputPassword;
        return this;
    }

    /**
     * Build name of user.
     *
     * @param inputName of building user
     * @return builder
     */
    public UserBuilder name(final String inputName) {
        name = inputName;
        return this;
    }

    /**
     * Build surname of user.
     *
     * @param inputSurname of building user
     * @return builder
     */
    public UserBuilder surname(final String inputSurname) {
        surname = inputSurname;
        return this;
    }

    /**
     * Build patronymic of user.
     *
     * @param inputPatronymic of building user
     * @return builder
     */
    public UserBuilder patronymic(final String inputPatronymic) {
        patronymic = inputPatronymic;
        return this;
    }

    /**
     * Build email of user.
     *
     * @param inputEmail of building user
     * @return builder
     */
    public UserBuilder email(final String inputEmail) {
        email = inputEmail;
        return this;
    }

    /**
     * Build phone of user.
     *
     * @param inputPhone of building user
     * @return builder
     */
    public UserBuilder phone(final long inputPhone) {
        phone = inputPhone;
        return this;
    }

    /**
     * Build role of user.
     *
     * @param inputRole of building user
     * @return builder
     */
    public UserBuilder role(final Role inputRole) {
        role = inputRole;
        return this;
    }

    /**
     * Build image path of user.
     *
     * @param inputImagePath of building user
     * @return builder
     */
    public UserBuilder imagePath(final String inputImagePath) {
        imagePath = inputImagePath;
        return this;
    }

    /**
     * Build user.
     *
     * @return built user
     */
    @Override
    public User build() {
        User user = new User(id);
        user.setLogin(login);
        user.setPassword(password);
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setEmail(email);
        user.setPhone(phone);
        user.setRole(role);
        user.setImagePath(imagePath);
        return user;
    }
}
