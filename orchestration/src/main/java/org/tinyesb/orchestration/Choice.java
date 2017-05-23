package org.tinyesb.orchestration;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class Choice extends AbstractExecutable {

    private Map<Condition, Executable> conditionsMap;
    private ExecutionStatus executionStatus;
    private Executable defaultExecutable;

    public Choice(String id) {
        super();
        this.id = id;
        conditionsMap  = new LinkedHashMap<>();
        executionStatus = new ExecutionStatus(id);
    }

    @Override
    public ExecutionStatus doExecute(Context context) throws ExecutionException {

        boolean conditionMet = false;

        Iterator<Map.Entry<Condition, Executable>> conditionIterator = conditionsMap.entrySet().iterator();

        while (!conditionMet && conditionIterator.hasNext()) {
            Map.Entry<Condition, Executable> entry = conditionIterator.next();
            try {
                if (entry.getKey().evaluate(context)) {
                    ExecutionStatus childExecutionStatus = entry.getValue().doExecute(context);
                    this.executionStatus.addChild(childExecutionStatus);
                    conditionMet = true;
                }
            } catch (EvaluationException e) {
                throw new ExecutionException("Evaluation of condition failed", e);
            }
        }

        if (!conditionMet && defaultExecutable != null) {
            ExecutionStatus childExecutionStatus = defaultExecutable.doExecute(context);
            this.executionStatus.addChild(childExecutionStatus);
        }

        executionStatus.setComplete();
        return executionStatus;
    }

    public void addCondition(Condition condition, Executable executable) throws OrchestrationConfigurationException {
        if (condition == null || executable == null) {
            throw new OrchestrationConfigurationException("Condition or Excutable cannot be null when adding a condition to choice :" + this.id);
        } else {
            conditionsMap.put(condition, executable);
        }
    }

    public void addDefault(Executable executable) throws OrchestrationConfigurationException {
        if (executable == null) {
            throw new OrchestrationConfigurationException("Excutable cannot be null when adding a default condition to choice :" + this.id);
        } else {
            defaultExecutable = executable;
        }
    }

}
