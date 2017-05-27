package org.tinyesb.jsshell.orchestration;

import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.ActivityExecutionStatus;
import org.tinyesb.orchestration.Context;
import org.tinyesb.orchestration.ExecutionStatus;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
class ScriptRunnerTest {
    @Test
    void doExecute() throws Exception {

        String executeScript = "context.variables.var3='value3'; context.variables.var1=context.variables.var2;";

        // given
        Context context = new Context();
        context.setVariable("var1", "value1");
        context.setVariable("var2", "value2");

        //when
        ScriptRunner scriptRunner = new ScriptRunner(executeScript, null, null);
        ActivityExecutionStatus status = scriptRunner.doExecute(context);
        System.out.println(context.getVariable("var3"));

        //then
        assertEquals("value2", context.getVariable("var1"));
        assertEquals("value3", context.getVariable("var3"));
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