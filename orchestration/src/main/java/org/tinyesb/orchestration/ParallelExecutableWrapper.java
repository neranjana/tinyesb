package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
public class ParallelExecutableWrapper implements Runnable {

    String activityId;
    Parallel parallel;
    Executable executable;
    Context context;

    public ParallelExecutableWrapper(String activityId, Parallel parallel, Executable executable, Context context) {
        this.activityId = activityId;
        this.parallel = parallel;
        this.executable = executable;
        this.context = context;
    }

    @Override
    public void run() {
        try {
            ExecutionStatus executionStatus = executable.doExecute(context);
            parallel.nofifyTaskCompletion(activityId, executionStatus);
        } catch (ExecutionException e) {
            //TODO handle this
            e.printStackTrace();
        }
    }
}
