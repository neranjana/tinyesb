package org.tinyesb.workflowbuilder;

import org.tinyesb.orchestration.Executable;
import org.tinyesb.orchestration.Sequence;
import org.tinyesb.orchestration.Workflow;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
public class WorkflowBuilder {

    private Workflow workflow;

    private WorkflowBuilder(String workflowId) {
        workflow = new Workflow(workflowId);
    }

    public static WorkflowBuilder createBuilder(String workflowId) {
        return new WorkflowBuilder(workflowId);
    }

    public WorkflowBuilder() {
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }

    public SequenceBuilder withSequence(String sequenceId) {
        return new SequenceBuilder(sequenceId, this);
    }

    protected void addExecutable(Executable executable) {
        this.workflow.addExecutable(executable);
    }

    public Workflow build() {
        return this.workflow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WorkflowBuilder)) return false;

        WorkflowBuilder that = (WorkflowBuilder) o;

        return workflow != null ? workflow.equals(that.workflow) : that.workflow == null;
    }

    @Override
    public int hashCode() {
        return workflow != null ? workflow.hashCode() : 0;
    }
}
