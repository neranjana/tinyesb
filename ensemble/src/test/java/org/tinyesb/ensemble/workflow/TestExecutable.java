package org.tinyesb.ensemble.workflow;

import org.tinyesb.orchestration.Context;
import org.tinyesb.orchestration.Executable;
import org.tinyesb.orchestration.ExecutionException;
import org.tinyesb.orchestration.ExecutionStatus;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
public class TestExecutable implements Executable {

    private String id;

    public TestExecutable(String id) {
        this.id = id;
    }

    @Override
    public ExecutionStatus doExecute(Context context) throws ExecutionException {
        ExecutionStatus executionStatus = new ExecutionStatus(id);
        executionStatus.setComplete();
        return executionStatus;
    }

    @Override
    public String getId() {
        return id;
    }
}
