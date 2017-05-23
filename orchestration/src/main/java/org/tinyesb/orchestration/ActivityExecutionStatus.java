package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class ActivityExecutionStatus extends ExecutionStatus {

    private boolean isCompensationComplete;

    private boolean isFinalyzationComplete;

    public ActivityExecutionStatus(String executableId) {
        super(executableId);
    }

    public boolean isCompensationComplete() {
        return isCompensationComplete;
    }

    public void setCompensationComplete(boolean compensationComplete) {
        isCompensationComplete = compensationComplete;
    }

    public boolean isFinalyzationComplete() {
        return isFinalyzationComplete;
    }

    public void setFinalyzationComplete(boolean finalyzationComplete) {
        isFinalyzationComplete = finalyzationComplete;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActivityExecutionStatus)) return false;

        ActivityExecutionStatus that = (ActivityExecutionStatus) o;

        if (isCompensationComplete != that.isCompensationComplete) return false;
        return isFinalyzationComplete == that.isFinalyzationComplete;
    }

    @Override
    public int hashCode() {
        int result = (isCompensationComplete ? 1 : 0);
        result = 31 * result + (isFinalyzationComplete ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ActivityExecutionStatus{" +
                "isCompensationComplete=" + isCompensationComplete +
                ", isFinalyzationComplete=" + isFinalyzationComplete +
                '}';
    }
}
