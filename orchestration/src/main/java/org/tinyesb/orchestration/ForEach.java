package org.tinyesb.orchestration;

import java.util.Collection;
import java.util.Map;

/**
 * Created by Neranjana Karunaratne on 31/05/2017.
 */
public class ForEach extends Parallel {

    private String listVariableName;

    private String localVariableName;
    private Executable executable;

    public ForEach(String id, String listVariableName, String localVariableName, Executable executable) {
        super(id);
        this.listVariableName = listVariableName;
        this.localVariableName = localVariableName;
        this.executable = executable;
    }

    public ForEach(String id, String listVariableName, String localVariableName, Executable executable, int threadCount) {
        super(id, threadCount);
        this.listVariableName = listVariableName;
        this.localVariableName = localVariableName;
        this.executable = executable;
    }

    @Override
    public ExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        Object listVariable = workflowVariables.get(listVariableName);
        long index = 0;
        if (listVariable != null && listVariable instanceof Collection) {
            Collection variableCollection = (Collection) listVariable;
            for (Object instanceVariable : variableCollection) {
                this.addExecutable(new ForEachExecutableWrapper(String.valueOf(index), localVariableName, instanceVariable, executable));
                index++;
            }
        } else if (listVariable instanceof Map) {
            Map variableMap = (Map) listVariable;
            for (Object instanceVariable : (variableMap.entrySet())) {
                this.addExecutable(new ForEachExecutableWrapper(String.valueOf(index), localVariableName, instanceVariable, executable));
                index++;
            }
        }

        return super.doExecute(getExecutionPath(parentExecutionPath), context, workflowVariables);
    }

}
