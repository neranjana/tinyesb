package org.tinyesb.orchestration;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
class ChoiceTest {
    @org.junit.jupiter.api.Test
    void doExecuteWithFirstConditionFalseSecondConditionTrue() throws OrchestrationConfigurationException, ExecutionException {
        Choice choice = new Choice("Choice1");
        choice.addCondition(new FalseCondition(), new TestParallelActivity("Activity1"));
        choice.addCondition(new TrueCondition(), new TestParallelActivity("Activity2"));

        ExecutionStatus executionStatus = choice.doExecute("", new Context(), new ExtensibleWorkflowVariables(new ArrayList<String>(Arrays.asList("Activity1-thread-id", "Activity2-thread-id", "Activity3-thread-id"))));

        assertTrue(executionStatus.isComplete());
        assertTrue(executionStatus.getChildren().get(0).isComplete());
        assertEquals("Activity2", executionStatus.getChildren().get(0).getExecutableId());

    }

    @org.junit.jupiter.api.Test
    void doExecuteWithTwoFalseConditionsAndADefault() throws OrchestrationConfigurationException, ExecutionException {
        Choice choice = new Choice("Choice1");
        choice.addCondition(new FalseCondition(), new TestParallelActivity("Activity1"));
        choice.addCondition(new FalseCondition(), new TestParallelActivity("Activity2"));
        choice.addDefault(new TestParallelActivity("Activity3"));

        ExecutionStatus executionStatus = choice.doExecute("",new Context(), new ExtensibleWorkflowVariables(new ArrayList<String>(Arrays.asList("Activity1-thread-id", "Activity2-thread-id", "Activity3-thread-id"))));

        assertTrue(executionStatus.isComplete());
        assertTrue(executionStatus.getChildren().get(0).isComplete());
        assertEquals("Activity3", executionStatus.getChildren().get(0).getExecutableId());

    }

    private class TrueCondition implements Condition {

        @Override
        public boolean evaluate(WorkflowVariables<String, Object> workflowVariables) throws EvaluationException {
            return true;
        }
    }

    private class FalseCondition implements Condition {

        @Override
        public boolean evaluate(WorkflowVariables<String, Object> workflowVariables) throws EvaluationException {
            return false;
        }
    }




}