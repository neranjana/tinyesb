package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class FinalyzationException extends Exception {

    public FinalyzationException() {
    }

    public FinalyzationException(String message) {
        super(message);
    }

    public FinalyzationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FinalyzationException(Throwable cause) {
        super(cause);
    }

    public FinalyzationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
