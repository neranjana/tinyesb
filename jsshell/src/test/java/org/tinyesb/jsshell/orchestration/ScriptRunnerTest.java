package org.tinyesb.jsshell.orchestration;

import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
class ScriptRunnerTest {
    @Test
    void doExecute() throws Exception {

        String executeScript = "workflowVariables.var3='value3'; workflowVariables.var1=workflowVariables.var2;";

        //given
        Context context = new Context();
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("var1", "var2", "var3"));
        workflowVariables.put("var1", "value1");
        workflowVariables.put("var2", "value2");

        //when
        ScriptRunner scriptRunner = new ScriptRunner(executeScript, null, null);
        ActivityExecutionStatus status = scriptRunner.doExecute("", context, workflowVariables);

        //then
        assertEquals("value2", workflowVariables.get("var1"));
        assertEquals("value3", workflowVariables.get("var3"));
        assertTrue(status.isComplete());
        assertTrue(status.isExecutionSuccess());
    }

    @Test
    void doCompensate() {
        // TODO implement
    }

    @Test
    void doFinalyze() {
        // TODO implement
    }

    @Test
    void getId() {
        // TODO implement
    }

}