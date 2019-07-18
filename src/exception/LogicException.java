package exception;

/**
 * Class of {@code Exception} reflection wrong behavior in service layer.
 *
 * @author Kirill Tolkun
 */
public class LogicException extends Exception {

    /**
     * Default constructor.
     */
    public LogicException() {
    }

    /**
     * Constructor with parameters.
     *
     * @param messsage of exception
     */
    public LogicException(final String messsage) {
        super(messsage);
    }

    /**
     * Constructor with parameters.
     *
     * @param message of exception
     * @param cause   the cause (which is saved for later retrieval by the
     *                {@link #getCause()} method)
     */
    public LogicException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with parameters (with the specified cause).
     *
     * @param cause the cause (which is saved for later retrieval by the
     *              {@link #getCause()} method)
     */
    public LogicException(final Throwable cause) {
        super(cause);
    }
}
