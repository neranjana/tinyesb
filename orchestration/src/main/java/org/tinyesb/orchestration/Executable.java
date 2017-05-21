package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public interface Executable {

    ExecutionStatus doExecute(Context context) throws ExecutionException;

    String getId();
}
