package org.tinyesb.ensemble.workflow;

import org.junit.jupiter.api.Test;
import org.tinyesb.codec.EncodeException;
import org.tinyesb.codec.Java2TextCodec;
import org.tinyesb.codec.yaml.Java2YamlCodec;
import org.tinyesb.orchestration.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
class WorkFlowLoaderTest {
    @Test
    void serializeExecutable() throws ExecutionException, EncodeException {
        // given

        // create 3 executables
        Executable executable1 = new TestExecutable("executable1");
        Executable executable2 = new TestExecutable("executable2");
        Executable executable3 = new TestExecutable("executable3");

        // create one sequance
        Sequence sequence = new Sequence("sequence1");
        // add the executables to the sequence
        sequence.addExecutable("exec1", executable1);
        sequence.addExecutable("exec2", executable2);
        sequence.addExecutable("exec3", executable3);

        Java2TextCodec codec = new Java2YamlCodec();

        String yaml = codec.encode(sequence);
        System.out.println(yaml);

    }

}