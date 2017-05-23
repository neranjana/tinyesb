package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class OrchestrationConfigurationException extends Exception {

    public OrchestrationConfigurationException() {
    }

    public OrchestrationConfigurationException(String message) {
        super(message);
    }

    public OrchestrationConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrchestrationConfigurationException(Throwable cause) {
        super(cause);
    }

    public OrchestrationConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
