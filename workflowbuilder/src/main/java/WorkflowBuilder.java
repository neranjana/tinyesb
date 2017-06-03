import org.tinyesb.orchestration.Workflow;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
public class WorkflowBuilder {

    private Workflow workflow;

    private WorkflowBuilder(String workflowId) {
        workflow = new Workflow(workflowId);
    }

    public static WorkflowBuilder createBuilder(String workflowId) {
        return new WorkflowBuilder(workflowId);
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }


}
