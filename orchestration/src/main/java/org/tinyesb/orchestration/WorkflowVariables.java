package org.tinyesb.orchestration;

import java.util.Map;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
public interface WorkflowVariables<K, V> extends Map<K, V> {
    boolean isValidKey(K key);
}
