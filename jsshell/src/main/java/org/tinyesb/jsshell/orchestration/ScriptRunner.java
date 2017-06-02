package org.tinyesb.jsshell.orchestration;

import org.tinyesb.orchestration.*;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
public class ScriptRunner extends AbstractExecutable implements Activity {

    public static final String EMPTY_SCRIPT = "";
    public static final String DEFAULT_JS_ENGINE_NAME = "nashorn";
    public static final String SCRIPT_BEGINING = "var doExecute = function(workflowVariables) { ";
    public static final String SCRIPT_END = " };";

    private String executeScript;
    private String compensateScript;
    private String confirmScript;

    public ScriptRunner(String executeScript, String compensateScript, String confirmScript) {
        if (executeScript != null && !executeScript.isEmpty()) {
            this.executeScript = executeScript;
        } else {
            this.executeScript = EMPTY_SCRIPT;
        }
        if (compensateScript != null && !compensateScript.isEmpty()) {
            this.compensateScript = compensateScript;
        } else {
            this.compensateScript = EMPTY_SCRIPT;
        }
        if (confirmScript != null && !confirmScript.isEmpty()) {
            this.confirmScript = confirmScript;
        } else {
            confirmScript = EMPTY_SCRIPT;
        }
    }


    @Override
    public ActivityExecutionStatus doExecute(String parentExecutionPath, Context context, WorkflowVariables<String, Object> workflowVariables) throws ExecutionException {
        boolean executionSuccess = false;
        ScriptEngine engine = createScriptEngine();
        ActivityExecutionStatus executionStatus = new ActivityExecutionStatus(id);
        try {
            engine.eval(SCRIPT_BEGINING + executeScript + SCRIPT_END);
            Invocable invocable = (Invocable) engine;
            Object success = invocable.invokeFunction("doExecute", workflowVariables);
            if (success!= null && success instanceof Boolean) {
                executionSuccess = (Boolean) success;

            }
            executionStatus.setComplete();
            executionStatus.setExecutionSuccess();
        } catch (Throwable throwable) {
            executionStatus.setError(throwable);
            executionStatus.setNotComplete();
        }

        return executionStatus;
    }

    @Override
    public ActivityExecutionStatus doCompensate(Context context, ActivityExecutionStatus activityExecutionStatus) throws CompensationException {
        // TODO implement
        return null;
    }

    @Override
    public ActivityExecutionStatus doConfirm(Context context, ActivityExecutionStatus activityExecutionStatus) throws ConfirmationException {
        // TODO implement
        return null;
    }

    @Override
    public String getId() {
        // TODO implement
        return null;
    }

    private ScriptEngine createScriptEngine() {
        // TODO implement
        return new ScriptEngineManager().getEngineByName(DEFAULT_JS_ENGINE_NAME);
    }
}
