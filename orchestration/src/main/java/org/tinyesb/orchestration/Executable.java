package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public interface Executable {

    ExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException;

    String getId();
}