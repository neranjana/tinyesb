package org.tinyesb.orchestration;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 31/05/2017.
 */
class ForEachTest {
    @Test
    void doExecute() throws ExecutionException {

        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("foo", "nameList", "bar", "MyActivity-thread-id"));
        List<String> nameList = Arrays.asList("John", "Sam", "Peter", "Tony");
        workflowVariables.put("foo", "foovalue");
        workflowVariables.put("nameList", nameList);
        workflowVariables.put("bar", "barValue");

        ForEach forEach = new ForEach("myForEach", "nameList", "name", new TestActivity("MyActivity"), 3);

        ExecutionStatus executionStatus = forEach.doExecute("", new Context(), workflowVariables);

        assertEquals(true, executionStatus.isComplete());

        System.out.println(executionStatus.getChildren());

    }

}