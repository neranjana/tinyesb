package org.tinyesb.jsshell.orchestration;

import org.tinyesb.orchestration.Condition;
import org.tinyesb.orchestration.Context;
import org.tinyesb.orchestration.EvaluationException;
import org.tinyesb.orchestration.WorkflowVariables;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by Neranjana Karunaratne on 2/06/2017.
 */
public class JsCondition extends JsExecutable implements Condition {

    public JsCondition(String scriptBody) {
        if (scriptBody != null) {
            this.scriptBody = scriptBody;
        } else {
            scriptBody = EMPTY_SCRIPT;
        }
    }

    @Override
    public boolean evaluate(WorkflowVariables<String, Object> workflowVariables) throws EvaluationException {
        boolean evaluation = false;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(DEFAULT_JS_ENGINE_NAME);

        Object evalObject = null;

        try {
            engine.eval(SCRIPT_BEGINING + scriptBody + SCRIPT_END);

        Invocable invocable = (Invocable) engine;
        evalObject = invocable.invokeFunction("doExecute", workflowVariables);
        } catch (ScriptException | NoSuchMethodException e) {
            throw new EvaluationException("Evaluation : " + scriptBody + " threw an Exception.", e);
        }
        if (evalObject!= null && evalObject instanceof Boolean) {
            evaluation = (Boolean) evalObject;
        } else {
            throw new EvaluationException("Evaluation : " + scriptBody + " did not return any meaningful result.");
        }
        return evaluation;
    }
}
