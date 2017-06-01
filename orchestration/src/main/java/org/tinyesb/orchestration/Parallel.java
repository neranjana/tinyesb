package org.tinyesb.orchestration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
public class Parallel extends Sequence {


    static Logger LOGGER = LoggerFactory.getLogger(Parallel.class);

    public static final int DEFAULT_THREAD_COUNT = 5;
    public static final int CHECK_FINISHED_PERIOD = 50;
    private int threadCount;
    private Map<String, ExecutionStatus> executionStatusMap;


    public Parallel(String id) {
        super(id);
        this.threadCount = DEFAULT_THREAD_COUNT;

    }

    public Parallel(String id, int threadCount) {
        super(id);
        this.threadCount = threadCount;
        this.executionStatusMap = new ConcurrentHashMap<>();
    }

    @Override
    public ExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (Executable executable : executableList) {
            ParallelExecutableRunner executableRunner = new ParallelExecutableRunner(getExecutionPath(parentExecutionPath), this, executable, context, workflowVariables);
            executor.execute(executableRunner);
        }

        while (!checkFinished()) {
            try {
                Thread.sleep(CHECK_FINISHED_PERIOD);
            } catch (InterruptedException e) {
                //TODO handle this
                e.printStackTrace();
            }
        }

        for (ExecutionStatus childExecutionStatus : executionStatusMap.values()) {
            this.executionStatus.addChild(childExecutionStatus);
        }
        this.executionStatus.setComplete();
        return executionStatus;
    }

    public void nofifyTaskCompletion(String activityId, ExecutionStatus executionStatus) {

        executionStatusMap.put(activityId, executionStatus);
    }

    private boolean checkFinished() {

        return executionStatusMap.size() == executableList.size();
    }
}
