package org.tinyesb.orchestration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class TestParallelActivity implements Activity {

    static Logger LOGGER = LoggerFactory.getLogger(TestParallelActivity.class);


    private String id;

    public TestParallelActivity(String id) {
        this.id = id;
    }

    @Override
    public ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        ActivityExecutionStatus activityExecutionStatus = new ActivityExecutionStatus(id);
        workflowVariables.put(id + "-thread-id", Thread.currentThread().getName());
        activityExecutionStatus.setComplete();
        LOGGER.info("TestParallelActivity in " + parentExecutionPath + "/" + id);
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException {
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doConfirm(Context context, ActivityExecutionStatus activityExecutionStatus) throws ConfirmationException {
        return activityExecutionStatus;
    }

    @Override
    public String getId() {
        return id;
    }
}
