package org.tinyesb.orchestration;

import java.lang.reflect.*;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.slf4j.*;

/**
 * Created by Neranjana Karunaratne on 23/05/2017.
 */
public class Choice extends AbstractExecutable {

    static Logger LOGGER = LoggerFactory.getLogger(Choice.class);

    private Map<Condition, Executable> conditionsMap;
    private Executable defaultExecutable;

    public Choice(String id) {
        super();
        this.id = id;
        conditionsMap  = new LinkedHashMap<>();
        executionStatus = new ExecutionStatus(id);
    }

    @Override
    public ExecutionStatus doExecute(Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {

        boolean conditionMet = false;

        Iterator<Map.Entry<Condition, Executable>> conditionIterator = conditionsMap.entrySet().iterator();

        while (!conditionMet && conditionIterator.hasNext()) {
            Map.Entry<Condition, Executable> entry = conditionIterator.next();
            try {
                if (entry.getKey().evaluate(context)) {
                    ExecutionStatus childExecutionStatus = entry.getValue().doExecute(context, workflowVariables);
                    this.executionStatus.addChild(childExecutionStatus);
                    conditionMet = true;
                    LOGGER.info("Choice :" + this.id + " evaluated success for condition:" + entry.getKey());
                }
            } catch (EvaluationException e) {
                throw new ExecutionException("Evaluation of condition failed", e);
            }
        }

        if (!conditionMet && defaultExecutable != null) {
            ExecutionStatus childExecutionStatus = defaultExecutable.doExecute(context, workflowVariables);
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
