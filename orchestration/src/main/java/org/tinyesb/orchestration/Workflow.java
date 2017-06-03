package org.tinyesb.orchestration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
public class Workflow {

    String id;
    Map<String, EntryPoint> entryPointMap;
    Executable executable;

    public Workflow(String id) {

        this.id = id;
        entryPointMap = new ConcurrentHashMap<>();
    }

    public String getId() {
        return this.id;
    }

    public void setExecutable(Executable executable) {
        this.executable = executable;
    }

    public Executable getExecutable() {
        return executable;
    }

    public  void addEntryPoint(String entryPointId, EntryPoint entryPoint) {
        this.entryPointMap.put(entryPointId, entryPoint);
    }

    public EntryPoint getEntryPoint(String entryPointId) {
        return this.entryPointMap.get(entryPointId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Workflow)) return false;

        Workflow workflow = (Workflow) o;

        if (id != null ? !id.equals(workflow.id) : workflow.id != null) return false;
        if (entryPointMap != null ? !entryPointMap.equals(workflow.entryPointMap) : workflow.entryPointMap != null)
            return false;
        return executable != null ? executable.equals(workflow.executable) : workflow.executable == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (entryPointMap != null ? entryPointMap.hashCode() : 0);
        result = 31 * result + (executable != null ? executable.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Workflow{" +
                "id='" + id + '\'' +
                ", entryPointMap=" + entryPointMap +
                ", executable=" + executable +
                '}';
    }
}
