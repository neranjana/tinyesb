package org.tinyesb.workflowbuilder;

import org.tinyesb.orchestration.Sequence;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
public class SequenceBuilder<T extends WorkflowBuilder> extends WorkflowBuilder {

    private T parentBuilder;
    private Sequence sequence;

    protected SequenceBuilder(String id, T parentBuilder) {
        this.parentBuilder = parentBuilder;
        this.sequence = new Sequence(id);
    }

    public T and() {
        return this.parentBuilder;
    }
}
