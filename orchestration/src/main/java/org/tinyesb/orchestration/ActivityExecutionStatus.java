package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class ActivityExecutionStatus extends ExecutionStatus {

    private boolean isCompensationComplete;

    private boolean isConfirmationComplete;

    private boolean isExecutionSuccess;

    public ActivityExecutionStatus(String executableId) {

        super(executableId);
    }

    public boolean isCompensationComplete() {
        return isCompensationComplete;
    }

    public void setCompensationComplete(boolean compensationComplete) {
        isCompensationComplete = compensationComplete;
    }

    public boolean isConfirmationComplete() {
        return isConfirmationComplete;
    }

    public void setConfirmationComplete(boolean confirmationComplete) {
        isConfirmationComplete = confirmationComplete;
    }

    public boolean isExecutionSuccess() {
        return isExecutionSuccess;
    }

    public void setExecutionSuccess() {
        isExecutionSuccess = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityExecutionStatus)) return false;

        ActivityExecutionStatus that = (ActivityExecutionStatus) o;

        if (isCompensationComplete != that.isCompensationComplete) return false;
        return isConfirmationComplete == that.isConfirmationComplete;
    }

    @Override
    public int hashCode() {
        int result = (isCompensationComplete ? 1 : 0);
        result = 31 * result + (isConfirmationComplete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActivityExecutionStatus{" +
                "isCompensationComplete=" + isCompensationComplete +
                ", isConfirmationComplete=" + isConfirmationComplete +
                '}';
    }
}
