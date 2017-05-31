package org.tinyesb.orchestration;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public abstract class AbstractExecutable implements Executable {

    public static final String EXECUTION_PATH_SEPARATOR = "/";
    protected String id;
    protected ExecutionStatus executionStatus;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractExecutable)) return false;

        AbstractExecutable that = (AbstractExecutable) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AbstractExecutable{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExecutionPath(String parentExecutionPath) {
        return parentExecutionPath + EXECUTION_PATH_SEPARATOR + this.id;
    }


}
