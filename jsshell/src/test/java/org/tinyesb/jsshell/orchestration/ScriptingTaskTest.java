package org.tinyesb.jsshell.orchestration;

import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.ActivityExecutionStatus;
import org.tinyesb.orchestration.Context;
import org.tinyesb.orchestration.ExtensibleWorkflowVariables;
import org.tinyesb.orchestration.WorkflowVariables;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 2/06/2017.
 */
class ScriptingTaskTest {
    @Test
    void runTask() throws Exception {

        //given
        String executableScript = "workflowVariables.var3='value3'; workflowVariables.var1=workflowVariables.var2;";

        Context context = new Context();
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("var1", "var2", "var3"));
        workflowVariables.put("var1", "value1");
        workflowVariables.put("var2", "value2");

        //when
        ScriptingTask scriptingTask = new ScriptingTask(executableScript);
        boolean success = scriptingTask.runTask(workflowVariables);

        //then
        assertEquals("value2", workflowVariables.get("var1"));
        assertEquals("value3", workflowVariables.get("var3"));
        assertTrue(success);
    }

}