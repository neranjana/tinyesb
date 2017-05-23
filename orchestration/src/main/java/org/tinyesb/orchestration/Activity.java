package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public interface Activity extends Executable {

    @Override
    ActivityExecutionStatus doExecute(Context context) throws ExecutionException;

    ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException;

    ActivityExecutionStatus doFinalyze(Context context, ActivityExecutionStatus activityExecutionStatus) throws FinalyzationException;
}
