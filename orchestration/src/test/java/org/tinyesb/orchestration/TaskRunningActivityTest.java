package org.tinyesb.orchestration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 1/06/2017.
 */
class TaskRunningActivityTest {
    WorkflowVariables<String, Object> workflowVariables;
    Context context;

    @BeforeEach
    void setUp() {
        workflowVariables = new ExtensibleWorkflowVariables<>(Arrays.asList("key1", "key2", "key3"));
        workflowVariables.put("key1", "value1");
        workflowVariables.put("key2", "value2");
        workflowVariables.put("key3", "value3");
        context = new Context();
    }

    @Test
    void doExecuteSuccessfully() throws ExecutionException {
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<>(Arrays.asList("var1", "var2", "var3"));
        Context context = new Context();
        Task successTask = new Task() {
            @Override
            public boolean runTask(WorkflowVariables<String, Object> workflowVariables) throws Exception {
                return true;
            }
        };

        TaskRunningActivity taskRunningActivity = new TaskRunningActivity("myTaskRunningActivity", successTask, null, null );
        ActivityExecutionStatus activityExecutionStatus = taskRunningActivity.doExecute("/path1/path2", context, workflowVariables);
        assertTrue(activityExecutionStatus.isExecutionSuccess());
        assertTrue(activityExecutionStatus.isComplete());

        //TODO check workflowVariableSnapshot

    }

    @Test
    void doExecuteUnsuccessfully() throws ExecutionException {
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<>(Arrays.asList("var1", "var2", "var3"));
        Context context = new Context();
        Task successTask = new Task() {
            @Override
            public boolean runTask(WorkflowVariables<String, Object> workflowVariables) throws Exception {
                return false;
            }
        };

        TaskRunningActivity taskRunningActivity = new TaskRunningActivity("myTaskRunningActivity", successTask, null, null );
        ActivityExecutionStatus activityExecutionStatus = taskRunningActivity.doExecute("/path1/path2", context, workflowVariables);
        assertFalse(activityExecutionStatus.isExecutionSuccess());
        assertTrue(activityExecutionStatus.isComplete());

    }

    @Test
    void doExecuteWithException() throws ExecutionException {
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<>(Arrays.asList("var1", "var2", "var3"));
        Context context = new Context();
        Task successTask = new Task() {
            @Override
            public boolean runTask(WorkflowVariables<String, Object> workflowVariables) throws Exception {
                throw new RuntimeException("Something went wrong");
            }
        };

        TaskRunningActivity taskRunningActivity = new TaskRunningActivity("myTaskRunningActivity", successTask, null, null );
        ActivityExecutionStatus activityExecutionStatus = taskRunningActivity.doExecute("/path1/path2", context, workflowVariables);
        assertFalse(activityExecutionStatus.isExecutionSuccess());
        assertTrue(activityExecutionStatus.isComplete());
        assertEquals("myTaskRunningActivity", context.getFailedActivityMap().get("/path1/path2/myTaskRunningActivity").getId());
        assertEquals("myTaskRunningActivity", context.getFailedActivityMap().get("/path1/path2/myTaskRunningActivity").getId());

    }

    @Test
    void doCompensate() {
    }

    @Test
    void doFinalyze() {
    }

}