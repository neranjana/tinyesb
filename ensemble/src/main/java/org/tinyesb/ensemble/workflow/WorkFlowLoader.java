package org.tinyesb.ensemble.workflow;

import org.tinyesb.codec.EncodeException;
import org.tinyesb.codec.Java2TextCodec;
import org.tinyesb.codec.yaml.Java2YamlCodec;
import org.tinyesb.orchestration.Executable;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
public class WorkFlowLoader {

    public String serializeExecutable(Executable executable) throws EncodeException {
        Java2TextCodec codec = new Java2YamlCodec();

            return codec.encode(executable);

    }
}
