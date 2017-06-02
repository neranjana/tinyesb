package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class ConfirmationException extends Exception {

    public ConfirmationException() {
    }

    public ConfirmationException(String message) {
        super(message);
    }

    public ConfirmationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfirmationException(Throwable cause) {
        super(cause);
    }

    public ConfirmationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
