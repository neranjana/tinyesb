package org.tinyesb.orchestration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class Context {

    private Map<String, Object> variables;

    public Context() {

        this.variables = new ConcurrentHashMap<>();
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }

    public void setVariable(String key, Object value) {
        this.variables.put(key, value);
    }

    public Object getVariable(String key) {
        return this.variables.get(key);
    }

    public void removeVariable(String key) {
        this.variables.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Context)) return false;

        Context context = (Context) o;

        return variables != null ? variables.equals(context.variables) : context.variables == null;
    }

    @Override
    public int hashCode() {
        return variables != null ? variables.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Context{" +
                "variables=" + variables +
                '}';
    }
}
