package org.tinyesb.orchestration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Neranjana Karunaratne on 1/06/2017.
 */
public class TestForEachActivity implements Activity {

    static Logger LOGGER = LoggerFactory.getLogger(TestForEachActivity.class);

    private String id;

    public TestForEachActivity(String id) {
        this.id = id;
    }

    @Override
    public ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        ActivityExecutionStatus activityExecutionStatus = new ActivityExecutionStatus(id);
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, String> executionPathThreadMap = (Map) workflowVariables.get("executionPathThreadMap");
        executionPathThreadMap.put(parentExecutionPath + "/" + id, Thread.currentThread().getName());

        Map<String, String> executionPathNameMap = (Map) workflowVariables.get("executionPathNameMap");
        executionPathNameMap.put(parentExecutionPath + "/" + id, (String) workflowVariables.get("name"));
        activityExecutionStatus.setComplete();
        return activityExecutionStatus;
    }

    @Override
    public ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException {
        return null;
    }

    @Override
    public ActivityExecutionStatus doConfirm(Context context, ActivityExecutionStatus activityExecutionStatus) throws ConfirmationException {
        return null;
    }

    @Override
    public String getId() {
        return this.id;
    }
}
