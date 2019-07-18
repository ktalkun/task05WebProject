package by.tolkun.barbershop.exception;

/**
 * Class of {@code Exception} reflection wrong input data.
 *
 * @author Kirill Tolkun
 */
public class PersistentException extends Exception {

    /**
     * Default constructor.
     */
    public PersistentException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public PersistentException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public PersistentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public PersistentException(final Throwable cause) {
        super(cause);
    }
}
