package org.tinyesb.orchestration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
public class Parallel extends Sequence {


    public static final int DEFAULT_THREAD_COUNT = 5;
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
    public ExecutionStatus doExecute(Context context) throws ExecutionException {

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        for (Map.Entry<String, Executable> entry : executableMap.entrySet()) {
            ParallelExecutableWrapper executableWrapper = new ParallelExecutableWrapper(entry.getKey(), this, entry.getValue(), context);
            executor.execute(executableWrapper);
        }

        while (!checkFinished()) {
            try {
                Thread.sleep(100);
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
        if (executionStatusMap.size() == executableMap.size()) {
            return true;
        } else {
            return false;
        }
    }
}
