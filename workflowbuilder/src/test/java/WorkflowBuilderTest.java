import org.junit.jupiter.api.Test;
import org.tinyesb.orchestration.Workflow;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 3/06/2017.
 */
class WorkflowBuilderTest {
    @Test
    void createBuilder() {
        //given

        //when
        WorkflowBuilder workflowBuilder = WorkflowBuilder.createBuilder("MyWorkflow");

        //then
        assertNotNull(workflowBuilder);
    }

    @Test
    void getWorkflow() {
        // given
        WorkflowBuilder workflowBuilder = WorkflowBuilder.createBuilder("MyWorkflow");

        // when
        Workflow workflow = workflowBuilder.getWorkflow();

        // then
        assertNotNull(workflow);
        assertEquals("MyWorkflow", workflow.getId());
    }

}