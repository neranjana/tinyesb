package org.tinyesb.orchestration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * Created by Neranjana Karunaratne on 31/05/2017.
 */
public class ForEachExecutableWrapper extends AbstractExecutable {
    static Logger LOGGER = LoggerFactory.getLogger(ForEachExecutableWrapper.class);

    private Executable executable;

    private Object instanceVariable;

    private String instanceVariableName;

    public ForEachExecutableWrapper(String id, String instanceVariableName, Object instanceVariable, Executable executable) {
        this.id = id;
        this.executable = executable;
        this.instanceVariableName = instanceVariableName;
        this.instanceVariable = instanceVariable;
    }

    @Override
    public ExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        WorkflowVariables<String, Object> localWorkflowVariables = new ExtensibleWorkflowVariables(Arrays.asList(instanceVariableName), workflowVariables);
        localWorkflowVariables.put(instanceVariableName, instanceVariable);
        return executable.doExecute(getExecutionPath(parentExecutionPath), context, localWorkflowVariables);
    }
}
