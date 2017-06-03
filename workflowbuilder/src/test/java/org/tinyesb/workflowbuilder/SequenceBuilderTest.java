package org.tinyesb.workflowbuilder;

import com.sun.xml.internal.ws.api.pipe.FiberContextSwitchInterceptor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
class SequenceBuilderTest {

    @Test
    public void and() {
        // given
        WorkflowBuilder originalWorkflowBuilder = WorkflowBuilder.createBuilder("MyWorkflow");
        SequenceBuilder<WorkflowBuilder> sequenceBuilder1 = originalWorkflowBuilder.withSequence("Sequence1");
        SequenceBuilder<SequenceBuilder> sequenceBuilder2 = sequenceBuilder1.withSequence("Sequence2");

        // when
        SequenceBuilder parentOfSequence2 = sequenceBuilder2.and();
        WorkflowBuilder parentOfSequence1 = parentOfSequence2.and();

        // then
        assertEquals(originalWorkflowBuilder, parentOfSequence1);
        assertEquals(sequenceBuilder1, parentOfSequence2);
    }

}