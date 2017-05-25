package org.tinyesb.orchestration;

import org.junit.jupiter.api.Test;

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

        // execute the sequence
        ExecutionStatus status = parallel.doExecute(context);

        // then
        assertEquals(3, context.getVariables().size());
        assertNotEquals(context.getVariable("activity1-thread-id"), context.getVariable("activity2-thread-id2"));
        assertNotEquals(context.getVariable("activity1-thread-id"), context.getVariable("activity3-thread-id2"));
        assertNotEquals(context.getVariable("activity3-thread-id"), context.getVariable("activity2-thread-id2"));


    }

}