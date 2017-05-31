package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
public class ParallelExecutableRunner implements Runnable {

    String parentExecutionPath;
    Parallel parallel;
    Executable executable;
    Context context;
    WorkflowVariables<String, Object> workflowVariables;

    public ParallelExecutableRunner(String parentExecutionPath, Parallel parallel, Executable executable, Context context, WorkflowVariables<String, Object> workflowVariables) {
        this.parentExecutionPath = parentExecutionPath;
        this.parallel = parallel;
        this.executable = executable;
        this.context = context;
        this.workflowVariables = workflowVariables;

    }

    @Override
    public void run() {
        try {
            ExecutionStatus executionStatus = executable.doExecute(parentExecutionPath, context, workflowVariables);
            parallel.nofifyTaskCompletion(executable.getId(), executionStatus);
        } catch (ExecutionException e) {
            //TODO handle this
            e.printStackTrace();
        }
    }
}
