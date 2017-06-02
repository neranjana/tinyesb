package org.tinyesb.jsshell.orchestration;

/**
 * Created by Neranjana Karunaratne on 2/06/2017.
 */
public abstract class JsExecutable {
    public static final String EMPTY_SCRIPT = "";
    public static final String DEFAULT_JS_ENGINE_NAME = "nashorn";
    public static final String SCRIPT_BEGINING = "var doExecute = function(workflowVariables) { ";
    public static final String SCRIPT_END = " };";
    protected String scriptBody;
}
