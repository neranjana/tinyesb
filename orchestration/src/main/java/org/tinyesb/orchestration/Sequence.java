package org.tinyesb.orchestration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class Sequence extends AbstractExecutable {

    protected Map<String, Executable> executableMap;

    public Sequence(String id) {
        this.id = id;
        this.executionStatus = new ExecutionStatus(id);
        this.executableMap = new ConcurrentHashMap<>();
    }

    @Override
    public ExecutionStatus doExecute(Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        for (Executable executable : executableMap.values()) {
            executionStatus.addChild((executable.doExecute(context, workflowVariables)));
        }
        this.executionStatus.setComplete();
        return executionStatus;
    }

    public void addExecutable(String id, Executable executable) {
        this.executableMap.put(id, executable);
    }

    public Map<String, Executable> getExecutableMap() {
        return executableMap;
    }

    public void setExecutableMap(Map<String, Executable> executableMap) {
        this.executableMap = executableMap;
    }
}
