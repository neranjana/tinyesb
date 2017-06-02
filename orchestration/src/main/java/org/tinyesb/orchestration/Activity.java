package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public interface Activity extends Executable {

    @Override
    ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException;

    ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException;

    ActivityExecutionStatus doConfirm(Context context, ActivityExecutionStatus activityExecutionStatus) throws ConfirmationException;
}
