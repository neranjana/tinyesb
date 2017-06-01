package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 1/06/2017.
 */
public interface Task {

    boolean runTask(WorkflowVariables<String, Object> workflowVariables) throws Exception;
}
