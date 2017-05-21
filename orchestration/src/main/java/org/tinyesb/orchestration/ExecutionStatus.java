package org.tinyesb.orchestration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class ExecutionStatus {

    private String executableId;
    private boolean isComplete;
    private List<ExecutionStatus> children;

    public ExecutionStatus(String executableId) {
        this.executableId = executableId;
        children = new ArrayList<>();
    }

    public String getExecutableId() {
        return executableId;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete() {
        isComplete = true;
    }

    public void addChild(ExecutionStatus child) {
        this.children.add(child);
    }

    public List<ExecutionStatus> getChildren() {
        return children;
    }
}
