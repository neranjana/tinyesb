package org.tinyesb.orchestration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class Sequence extends AbstractExecutable {

    protected List<Executable> executableList;

    public Sequence(String id) {
        this.id = id;
        this.executionStatus = new ExecutionStatus(id);
        this.executableList = new ArrayList<>();
    }

    @Override
    public ExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        for (Executable executable : executableList) {
            executionStatus.addChild((executable.doExecute(getExecutionPath(parentExecutionPath), context, workflowVariables)));
        }
        this.executionStatus.setComplete();
        return executionStatus;
    }

    public void addExecutable(Executable executable) {
        this.executableList.add(executable);
    }

    public List<Executable> getExecutableList() {
        return executableList;
    }

    public void setExecutableList(List< Executable> executableList) {
        this.executableList = executableList;
    }
}
