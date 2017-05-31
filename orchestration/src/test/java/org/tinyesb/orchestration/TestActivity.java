package org.tinyesb.orchestration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class TestActivity implements Activity {

    static Logger LOGGER = LoggerFactory.getLogger(TestActivity.class);


    private String id;

    public TestActivity(String id) {
        this.id = id;
    }

    @Override
    public ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        ActivityExecutionStatus activityExecutionStatus = new ActivityExecutionStatus(id);
        workflowVariables.put(id + "-thread-id", Thread.currentThread().getName());
        activityExecutionStatus.setComplete();
        LOGGER.info("TestActivity in " + parentExecutionPath + "/" + id);
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException {
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doFinalyze(Context context, ActivityExecutionStatus activityExecutionStatus) throws FinalyzationException {
        return activityExecutionStatus;
    }

    @Override
    public String getId() {
        return id;
    }
}
