package org.tinyesb.orchestration;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
public class ExtensibleWorkflowVariables<K, V> implements WorkflowVariables<K, V> {

    private WorkflowVariables<K, V> parentWorkflowVariables;
    private Map<K, V> localWorkflowVariables;
    private List<K> keyList;

    public ExtensibleWorkflowVariables(List<K> keyList) {
        localWorkflowVariables = new ConcurrentHashMap<>();
        parentWorkflowVariables = null;
        this.keyList = new ArrayList<>(keyList);
    }

    public ExtensibleWorkflowVariables(List<K> keyList, WorkflowVariables<K, V> parentProcessVariables) {
        this.parentWorkflowVariables = parentProcessVariables;
        this.localWorkflowVariables = new ConcurrentHashMap<>();
        for (K key : keyList) {
            if (parentProcessVariables.isValidKey(key)) {
                throw new IllegalArgumentException("Key : " + key + " is already defined in parent scope.");
            }
        }
        this.keyList = new ArrayList<>(keyList);
    }

    @Override
    public int size() {
        if (parentWorkflowVariables == null) {
            return localWorkflowVariables.size();
        } else {
            return parentWorkflowVariables.size() + localWorkflowVariables.size();
        }
    }

    @Override
    public boolean isEmpty() {
        if (parentWorkflowVariables == null) {
            return localWorkflowVariables.isEmpty();
        } else {
            return parentWorkflowVariables.isEmpty() && localWorkflowVariables.isEmpty();
        }
    }

    @Override
    public boolean containsKey(Object key) {
        if (parentWorkflowVariables == null) {
            return localWorkflowVariables.containsKey(key);
        } else {
            return parentWorkflowVariables.containsKey(key) || localWorkflowVariables.containsKey(key);
        }
    }

    @Override
    public boolean containsValue(Object value) {
        if (parentWorkflowVariables == null) {
            return localWorkflowVariables.containsValue(value);
        } else {
            return parentWorkflowVariables.containsValue(value) || localWorkflowVariables.containsValue(value);
        }
    }

    @Override
    public V get(Object key) {
        V value;

        if (localWorkflowVariables.containsKey(key)) {
            value = localWorkflowVariables.get(key);
        } else if (parentWorkflowVariables != null) {
            value = parentWorkflowVariables.get(key);
        } else {
            value = null;
        }

        return value;
    }

    @Override
    public V put(K key, V value) {

        V previousValue;
        if (parentWorkflowVariables != null && parentWorkflowVariables.isValidKey(key)) {
            // if variable key is valid in parent scope then add to parent scope
            previousValue = parentWorkflowVariables.put(key, value);
        } else if (isValidKey(key)) {
            // if variable key is valid in this scope then add to this scope
            previousValue = localWorkflowVariables.put(key, value);
        } else {
            // if variable key is not valid then throw exception
            throw new IllegalArgumentException("Key : " + key + " is not defined as a variable name.");
        }
        return previousValue;

    }

    @Override
    public V remove(Object key) {
        // Can only remove a variable from the local scope
        return localWorkflowVariables.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        for (Map.Entry<? extends K, ? extends V> entry : m.entrySet()) {
            this.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void clear() {
        // Can only clear all variables from the local scope
        localWorkflowVariables.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        if (parentWorkflowVariables != null) {
            keySet.addAll(parentWorkflowVariables.keySet());
        }
        keySet.addAll(localWorkflowVariables.keySet());
        return keySet;
    }

    @Override
    public Collection<V> values() {
        List<V> valueList = new ArrayList<>();
        if (parentWorkflowVariables != null) {
            valueList.addAll(parentWorkflowVariables.values());
        }
        valueList.addAll(localWorkflowVariables.values());
        return valueList;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entrySet = new HashSet<>();
        if (parentWorkflowVariables != null) {
            entrySet.addAll(parentWorkflowVariables.entrySet());
        }
        entrySet.addAll(localWorkflowVariables.entrySet());
        return entrySet;
    }

    @Override
    public boolean isValidKey(K key) {
        boolean isValid = false;

        if (keyList.contains(key)) {
            // checking if the key is in local scope
            isValid = true;
        } else if (parentWorkflowVariables != null) {
            // if there is a parent scope, then checking if key is valid in parent scope
            isValid = parentWorkflowVariables.isValidKey(key);
        }
        return isValid;
    }

    @Override
    public WorkflowVariables<K, V> createSnapshot() {

        WorkflowVariables<K, V> snapshot = new ExtensibleWorkflowVariables<>(new ArrayList(this.keySet()));
        for (Map.Entry<K, V> entry : this.entrySet()) {
            snapshot.put(entry.getKey(), entry.getValue());
        }
        return snapshot;
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();

        for (Map.Entry<K, V> entry : this.entrySet()) {
            objectStringBuilder.append(entry.getKey()).append(" = ").append(entry.getValue()).append(" , ");
        }
        return objectStringBuilder.toString();
    }
}
