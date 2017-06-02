package org.tinyesb.jsshell.orchestration;

import org.tinyesb.orchestration.ActivityExecutionStatus;
import org.tinyesb.orchestration.Task;
import org.tinyesb.orchestration.WorkflowVariables;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by Neranjana Karunaratne on 2/06/2017.
 */
public class ScriptingTask implements Task {

    public static final String EMPTY_SCRIPT = "";
    public static final String DEFAULT_JS_ENGINE_NAME = "nashorn";
    public static final String SCRIPT_BEGINING = "var doExecute = function(workflowVariables) { ";
    public static final String SCRIPT_END = " };";

    private String scriptBody;

    public ScriptingTask(String scriptBody) {
        if (scriptBody != null) {
            this.scriptBody = scriptBody;
        } else {
            scriptBody = EMPTY_SCRIPT;
        }
    }

    @Override
    public boolean runTask(WorkflowVariables<String, Object> workflowVariables) throws Exception {
        boolean executionSuccess = false;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName(DEFAULT_JS_ENGINE_NAME);


            engine.eval(SCRIPT_BEGINING + scriptBody + SCRIPT_END);
            Invocable invocable = (Invocable) engine;
            Object success = invocable.invokeFunction("doExecute", workflowVariables);
            if (success!= null && success instanceof Boolean) {
                executionSuccess = (Boolean) success;

            }


        return executionSuccess;
    }
}
