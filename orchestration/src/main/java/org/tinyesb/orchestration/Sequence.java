package org.tinyesb.orchestration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class Sequence extends AbstractExecutable {

    ExecutionStatus executionStatus;
    List<Executable> executableList;

    public Sequence(String id) {
        this.id = id;
        this.executionStatus = new ExecutionStatus(id);
        this.executableList = new ArrayList<>();
    }

    @Override
    public ExecutionStatus doExecute(Context context) throws ExecutionException {
        for (Executable executable : executableList) {
            executionStatus.addChild((executable.doExecute(context)));
        }
        this.executionStatus.setComplete();
        return executionStatus;
    }

    public void addExecutable(Executable executable) {
        this.executableList.add(executable);
    }

}
