package org.tinyesb.workflowbuilder;

import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.Executable;
import org.tinyesb.orchestration.Workflow;
import org.tinyesb.workflowbuilder.WorkflowBuilder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
class WorkflowBuilderTest {
    @Test
    void createBuilder() {
        //given

        //when
        WorkflowBuilder workflowBuilder = WorkflowBuilder.createBuilder("MyWorkflow");

        //then
        assertNotNull(workflowBuilder);
    }

    @Test
    void getWorkflow() {
        // given
        WorkflowBuilder workflowBuilder = WorkflowBuilder.createBuilder("MyWorkflow");

        // when
        Workflow workflow = workflowBuilder.getWorkflow();

        // then
        assertNotNull(workflow);
        assertEquals("MyWorkflow", workflow.getId());
    }

    @Test
    void withSequence() throws Exception {
        // given

        // when
        SequenceBuilder sequenceBuilder = WorkflowBuilder.createBuilder("MyWorkflow").withSequence("sequence1");

        // then
        assertNotNull(sequenceBuilder);
    }

    @Test
    void build() throws Exception {
        // given

        // when
        Workflow workflow = WorkflowBuilder.createBuilder("MyWorkflow").build();

        // then
        assertNotNull(workflow);
        assertEquals("MyWorkflow", workflow.getId());
        assertEquals("MyWorkflow", workflow.getDefaultSequence().getId());
        assertTrue(workflow.getDefaultSequence().getExecutableList().isEmpty());
    }

}