package org.tinyesb.jsshell.orchestration;

import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.Context;
import org.tinyesb.orchestration.ExecutionStatus;
import org.tinyesb.orchestration.ExtensibleWorkflowVariables;
import org.tinyesb.orchestration.WorkflowVariables;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 2/06/2017.
 */
class JsConditionTest {
    @Test
    void evaluateSuccess() throws Exception {

        //given
        String executableScript = "if (workflowVariables.var2=='value2') { return true; } else { return false;}";

        Context context = new Context();
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("var1", "var2", "var3"));
        workflowVariables.put("var1", "value1");
        workflowVariables.put("var2", "value2");

        //when
        JsCondition jsCondition = new JsCondition(executableScript);
        boolean success = jsCondition.evaluate(workflowVariables);

        //then
        assertTrue(success);
    }

    @Test
    void evaluateFailure() throws Exception {

        //given
        String executableScript = "if (workflowVariables.var2=='value1') { return true; } else { return false;}";

        Context context = new Context();
        WorkflowVariables<String, Object> workflowVariables = new ExtensibleWorkflowVariables<String, Object>(Arrays.asList("var1", "var2", "var3"));
        workflowVariables.put("var1", "value1");
        workflowVariables.put("var2", "value2");

        //when
        JsCondition jsCondition = new JsCondition(executableScript);
        boolean success = jsCondition.evaluate(workflowVariables);

        //then
        assertFalse(success);
    }

}