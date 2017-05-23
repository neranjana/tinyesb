package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class CompensationException extends Exception {
    public CompensationException() {
    }

    public CompensationException(String message) {
        super(message);
    }

    public CompensationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompensationException(Throwable cause) {
        super(cause);
    }

    public CompensationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
