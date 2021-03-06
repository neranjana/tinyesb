package org.tinyesb.orchestration;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class SequenceTest {
    @Test
    public void doExecuteSimpleSequence() throws Exception {

        // given

        // create 3 executables
        Executable executable1 = new TestParallelActivity("activity1");
        Executable executable2 = new TestParallelActivity("activity2");
        Executable executable3 = new TestParallelActivity("activity3");

        // create one sequance
        Sequence sequence = new Sequence("sequence1");
        // add the executables to the sequence
        sequence.addExecutable(executable1);
        sequence.addExecutable(executable2);
        sequence.addExecutable(executable3);

        // create a context for the sequence
        Context context = new Context();

        // create a new WorkflowVariables for the sequence
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("activity1-thread-id", "activity2-thread-id", "activity3-thread-id"));

        // when

        // execute the sequence
        ExecutionStatus status = sequence.doExecute("",context, workflowVariables);

        // then

        // the execution status should be completed
        assertEquals(true, status.isComplete());

        // execution status should have three children, one for each child executable of the sequence
        assertEquals(3, status.getChildren().size());


        // each child execution status should have the id of its executable
        assertEquals("activity1", status.getChildren().get(0).getExecutableId());
        assertEquals("activity2", status.getChildren().get(1).getExecutableId());
        assertEquals("activity3", status.getChildren().get(2).getExecutableId());

        // each child execution status should also be completed
        assertEquals(true, status.getChildren().get(0).isComplete());
        assertEquals(true, status.getChildren().get(1).isComplete());
        assertEquals(true, status.getChildren().get(2).isComplete());

    }

}
