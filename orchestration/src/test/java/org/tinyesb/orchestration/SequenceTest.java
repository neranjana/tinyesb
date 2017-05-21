package org.tinyesb.orchestration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Neranjana Karunaratne on 21/05/2017.
 */
public class SequenceTest {
    @Test
    public void doExecuteSimpleSequence() throws Exception {

        // given

        // create 3 executables
        Executable executable1 = new TestExecutable("executable1");
        Executable executable2 = new TestExecutable("executable2");
        Executable executable3 = new TestExecutable("executable3");

        // create one sequance
        Sequence sequence = new Sequence("sequence1");
        // add the executables to the sequence
        sequence.addExecutable(executable1);
        sequence.addExecutable(executable2);
        sequence.addExecutable(executable3);

        // create a context for the sequence
        Context context = new Context();

        // when

        // execute the sequence
        ExecutionStatus status = sequence.doExecute(context);

        // then

        // the execution status should be completed
        assertEquals(true, status.isComplete());

        // execution status should have three children, one for each child executable of the sequence
        assertEquals(3, status.getChildren().size());


        // each child execution status should have the id of its executable
        assertEquals("executable1", status.getChildren().get(0).getExecutableId());
        assertEquals("executable2", status.getChildren().get(1).getExecutableId());
        assertEquals("executable3", status.getChildren().get(2).getExecutableId());

        // each child execution status should also be completed
        assertEquals(true, status.getChildren().get(0).isComplete());
        assertEquals(true, status.getChildren().get(1).isComplete());
        assertEquals(true, status.getChildren().get(2).isComplete());

    }

    // test executable which simply sets its execution status to true
    private class TestExecutable implements Executable {

        private String id;

        public TestExecutable(String id) {
            this.id = id;
        }

        @Override
        public ExecutionStatus doExecute(Context context) throws ExecutionException {
            ExecutionStatus executionStatus = new ExecutionStatus(id);
            executionStatus.setComplete();
            return executionStatus;
        }

        @Override
        public String getId() {
            return id;
        }
    }
}
