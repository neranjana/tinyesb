package org.tinyesb.orchestration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class Context {

    private Map<String, Activity> successfullyCompletedActivityMap;
    private Map<String, Activity> failedActivityMap;


    public Context() {

        successfullyCompletedActivityMap = new ConcurrentHashMap<>();
        failedActivityMap = new ConcurrentHashMap<>();
    }

    public void addSuccessfullyCompletedActivity(String executionPath, Activity activity) {
        this.successfullyCompletedActivityMap.put(executionPath, activity);
    }

    public void addFailedActivity(String executionPath, Activity activity) {
        this.failedActivityMap.put(executionPath, activity);
    }

    public Map<String, Activity> getSuccessfullyCompletedActivityMap() {
        return successfullyCompletedActivityMap;
    }

    public Map<String, Activity> getFailedActivityMap() {
        return failedActivityMap;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
