package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 1/06/2017.
 */
public class TaskRunningActivity extends AbstractExecutable implements Activity {

    private Task mainExecutableTask;
    private Task finalyzationTask;
    private Task compensationTask;
    private WorkflowVariables<String, Object> workflowVariablesSnapshot;

    public TaskRunningActivity(String id, Task mainExecutableTask, Task finalyzationTask, Task compensationTask) {
        this.id = id;
        this.mainExecutableTask = mainExecutableTask;
        this.finalyzationTask = finalyzationTask;
        this.compensationTask = compensationTask;
        this.executionStatus = new ExecutionStatus(id);
    }

    @Override
    public ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {

        ActivityExecutionStatus activityExecutionStatus = new ActivityExecutionStatus(id);
        boolean isSuccess = false;

        try {
            isSuccess = mainExecutableTask.runTask(workflowVariables);
            if (isSuccess) {
                workflowVariablesSnapshot = workflowVariables.createSnapshot();
                context.addSuccessfullyCompletedActivity(getExecutionPath(parentExecutionPath), this);
                activityExecutionStatus.setExecutionSuccess();
            } else {
                context.addFailedActivity(getExecutionPath(parentExecutionPath), this);
            }
        } catch (Exception e) {
            context.addFailedActivity(getExecutionPath(parentExecutionPath), this);
            activityExecutionStatus.setError(e);
        } finally {
            activityExecutionStatus.setComplete();
        }
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException {
        return null;
    }

    @Override
    public ActivityExecutionStatus doFinalyze(Context context, ActivityExecutionStatus activityExecutionStatus) throws FinalyzationException {
        return null;
    }


}
