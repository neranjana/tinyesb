package org.tinyesb.orchestration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
class ExtensibleWorkflowVariablesTest {

    private WorkflowVariables<String, String> variablesLevell;
    private WorkflowVariables<String, String> variablesLevel2;
    private WorkflowVariables<String, String> variablesLevel3;

    @BeforeEach
    void setUp() {
        // key values for top level scope
        String key1 = "key1";
        String value1 = "value1";
        String key2 = "key2";
        String value2 = "value2";
        String key3 = "key3";
        String value3 = "value3";

        // key values for second level scope
        String key11 = "key11";
        String value11 = "value11";
        String key12 = "key12";
        String value12 = "value12";
        String key13 = "key13";
        String value13 = "value13";

        // key values for thrird level scope
        String key21 = "key21";
        String value21 = "value21";
        String key22 = "key22";
        String value22 = "value22";
        String key23 = "key23";
        String value23 = "value23";

        variablesLevell = new ExtensibleWorkflowVariables<>(Arrays.asList("key1", "key2", "key3"));
        variablesLevell.put(key1, value1);
        variablesLevell.put(key2, value2);
        variablesLevell.put(key3, value3);

        variablesLevel2 = new ExtensibleWorkflowVariables<>(Arrays.asList("key11", "key12", "key13"), variablesLevell);
        variablesLevel2.put(key11, value11);
        variablesLevel2.put(key12, value12);
        variablesLevel2.put(key13, value13);

        variablesLevel3 = new ExtensibleWorkflowVariables<>(Arrays.asList("key21", "key22", "key23"), variablesLevel2);
        variablesLevel3.put(key21, value21);
        variablesLevel3.put(key22, value22);
        variablesLevel3.put(key23, value23);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void size() {
        assertEquals(3, variablesLevell.size());
        assertEquals(6, variablesLevel2.size());
        assertEquals(9, variablesLevel3.size());

    }

    @Test
    void isEmpty() {
        WorkflowVariables<String, String> emptyVariables = new ExtensibleWorkflowVariables<String, String>(Arrays.asList("1", "2"));
        assertTrue(emptyVariables.isEmpty());
        assertTrue(!variablesLevel3.isEmpty());
        WorkflowVariables<String, String> notEmptyVariables = new ExtensibleWorkflowVariables<String, String>(Arrays.asList("1", "2"), variablesLevel2);
        assertTrue(!notEmptyVariables.isEmpty());

    }

    @Test
    void containsKey() {
    }

    @Test
    void containsValue() {
    }

    @Test
    void get() {
    }

    @Test
    void put() {
    }

    @Test
    void remove() {
    }

    @Test
    void putAll() {
    }

    @Test
    void clear() {
    }

    @Test
    void keySet() {
    }

    @Test
    void values() {
    }

    @Test
    void entrySet() {
    }

}