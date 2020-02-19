package by.tolkun.barbershop.exception;

/**
 * Class of {@code Exception} reflection wrong behavior in controllers.
 *
 * @author Kirill Tolkun
 */
public class ControllerException extends Exception {

    /**
     * Default constructor.
     */
    public ControllerException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public ControllerException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public ControllerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public ControllerException(final Throwable cause) {
        super(cause);
    }
}
