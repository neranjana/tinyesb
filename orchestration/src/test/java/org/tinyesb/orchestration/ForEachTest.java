package org.tinyesb.orchestration;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 31/05/2017.
 */
class ForEachTest {
    @Test
    void doExecute() throws ExecutionException {

        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("foo", "nameList", "bar", "executionPathThreadMap", "executionPathNameMap"));
        List<String> nameList = Arrays.asList("John", "Sam", "Peter", "Tony");
        workflowVariables.put("foo", "foovalue");
        workflowVariables.put("nameList", nameList);
        workflowVariables.put("bar", "barValue");
        workflowVariables.put("executionPathThreadMap", new ConcurrentHashMap<>());
        workflowVariables.put("executionPathNameMap", new ConcurrentHashMap<>());

        ForEach forEach = new ForEach("myForEach", "nameList", "name", new TestForEachActivity("MyActivity"), 5);

        ExecutionStatus executionStatus = forEach.doExecute("", new Context(), workflowVariables);

        assertEquals(true, executionStatus.isComplete());
        Map<String, String> executionPathThreadMap = (Map) workflowVariables.get("executionPathThreadMap");
        assertEquals(4, executionPathThreadMap.size());
        List<String> expectedExecutionPathList = Arrays.asList("/myForEach/0/MyActivity", "/myForEach/1/MyActivity", "/myForEach/2/MyActivity", "/myForEach/3/MyActivity");
        assertTrue(executionPathThreadMap.keySet().containsAll(expectedExecutionPathList));
        Map<String, String> executionPathNameMap = (Map) workflowVariables.get("executionPathNameMap");

        assertEquals(4, executionPathNameMap.size());
        assertTrue(executionPathNameMap.values().containsAll(nameList));

    }

}