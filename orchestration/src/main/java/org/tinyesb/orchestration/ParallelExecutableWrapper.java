package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
public class ParallelExecutableWrapper implements Runnable {

    String activityId;
    Parallel parallel;
    Executable executable;
    Context context;
    WorkflowVariables<String, Object> workflowVariables;

    public ParallelExecutableWrapper(String activityId, Parallel parallel, Executable executable, Context context, WorkflowVariables<String, Object> workflowVariables) {
        this.activityId = activityId;
        this.parallel = parallel;
        this.executable = executable;
        this.context = context;
        this.workflowVariables = workflowVariables;

    }

    @Override
    public void run() {
        try {
            ExecutionStatus executionStatus = executable.doExecute(context, workflowVariables);
            parallel.nofifyTaskCompletion(activityId, executionStatus);
        } catch (ExecutionException e) {
            //TODO handle this
            e.printStackTrace();
        }
    }
}
