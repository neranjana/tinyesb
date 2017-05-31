package org.tinyesb.orchestration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 25/05/2017.
 */
class ParallelTest {
    @Test
    void doExecute() throws ExecutionException {

        // given

        // create 3 executables
        Executable executable1 = new TestActivity("activity1");
        Executable executable2 = new TestActivity("activity2");
        Executable executable3 = new TestActivity("activity3");

        // create one sequance
        Parallel parallel = new Parallel("parallel1", 3);
        // add the executables to the sequence
        parallel.addExecutable("activity1", executable1);
        parallel.addExecutable("activity2", executable2);
        parallel.addExecutable("activity3", executable3);

        // create a context for the sequence
        Context context = new Context();

        // when
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("activity1-thread-id", "activity2-thread-id", "activity3-thread-id"));

        // execute the sequence
        ExecutionStatus status = parallel.doExecute(context, workflowVariables);

        // then
        assertEquals(3, workflowVariables.size());
        assertNotEquals(workflowVariables.get("activity1-thread-id"), workflowVariables.get("activity2-thread-id"));
        assertNotEquals(workflowVariables.get("activity1-thread-id"), workflowVariables.get("activity3-thread-id"));
        assertNotEquals(workflowVariables.get("activity3-thread-id"), workflowVariables.get("activity2-thread-id"));

    }

}