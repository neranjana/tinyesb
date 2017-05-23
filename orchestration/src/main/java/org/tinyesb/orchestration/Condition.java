package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public interface Condition {

    boolean evaluate(Context context) throws EvaluationException;
}
