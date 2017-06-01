package org.tinyesb.orchestration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neranjana Karunaratne on 27/05/2017.
 */
class ExtensibleWorkflowVariablesTest {

    private WorkflowVariables<String, String> variablesLevel1;
    private WorkflowVariables<String, String> variablesLevel2;
    private WorkflowVariables<String, String> variablesLevel3;

    // key values for top level scope
    private String key1 = "key1";
    private String value1 = "value1";
    private String key2 = "key2";
    private String value2 = "value2";
    private String key3 = "key3";
    private String value3 = "value3";

    // key values for second level scope
    private String key11 = "key11";
    private String value11 = "value11";
    private String key12 = "key12";
    private String value12 = "value12";
    private String key13 = "key13";
    private String value13 = "value13";

    // key values for thrird level scope
    private String key21 = "key21";
    private String value21 = "value21";
    private String key22 = "key22";
    private String value22 = "value22";
    private String key23 = "key23";
    private String value23 = "value23";

    @BeforeEach
    void setUp() {
        variablesLevel1 = new ExtensibleWorkflowVariables<>(Arrays.asList("key1", "key2", "key3", "key4"));
        variablesLevel1.put(key1, value1);
        variablesLevel1.put(key2, value2);
        variablesLevel1.put(key3, value3);

        variablesLevel2 = new ExtensibleWorkflowVariables<>(Arrays.asList("key11", "key12", "key13", "key14"), variablesLevel1);
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
        assertEquals(3, variablesLevel1.size());
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
        assertTrue(variablesLevel3.containsKey(key1));
        assertTrue(variablesLevel3.containsKey(key11));
        assertTrue(variablesLevel3.containsKey(key21));

        assertTrue(variablesLevel2.containsKey(key1));
        assertTrue(variablesLevel2.containsKey(key11));
        assertTrue(!variablesLevel2.containsKey(key21));

        assertTrue(variablesLevel2.containsKey(key1));
        assertTrue(variablesLevel2.containsKey(key11));
        assertTrue(!variablesLevel2.containsKey(key21));
        assertTrue(!variablesLevel2.containsKey("key14"));
        assertTrue(!variablesLevel2.containsKey("key15"));

        assertTrue(variablesLevel1.containsKey(key1));
        assertTrue(!variablesLevel1.containsKey(key11));
        assertTrue(!variablesLevel1.containsKey(key21));
    }

    @Test
    void containsValue() {
        assertTrue(variablesLevel3.containsValue(value1));
        assertTrue(variablesLevel3.containsValue(value11));
        assertTrue(variablesLevel3.containsValue(value21));

        assertTrue(variablesLevel2.containsValue(value1));
        assertTrue(variablesLevel2.containsValue(value11));
        assertTrue(!variablesLevel2.containsValue(value21));

        assertTrue(variablesLevel2.containsValue(value1));
        assertTrue(variablesLevel2.containsValue(value11));
        assertTrue(!variablesLevel2.containsValue(value21));
        assertTrue(!variablesLevel2.containsValue("value14"));

        assertTrue(variablesLevel1.containsValue(value1));
        assertTrue(!variablesLevel1.containsValue(value11));
        assertTrue(!variablesLevel1.containsValue(value21));
    }

    @Test
    void get() {
        assertEquals(value1, variablesLevel1.get(key1));
        assertEquals(value1, variablesLevel2.get(key1));
        assertEquals(value1, variablesLevel3.get(key1));

        assertEquals(null, variablesLevel1.get(key11));
        assertEquals(value11, variablesLevel2.get(key11));
        assertEquals(value11, variablesLevel3.get(key11));

        assertEquals(null, variablesLevel1.get(key21));
        assertEquals(null, variablesLevel2.get(key21));
        assertEquals(value21, variablesLevel3.get(key21));

        assertEquals(null, variablesLevel1.get("key14"));
        assertEquals(null, variablesLevel2.get("key14"));
        assertEquals(null, variablesLevel3.get("key14"));

        assertEquals(null, variablesLevel1.get("key15"));
        assertEquals(null, variablesLevel2.get("key15"));
        assertEquals(null, variablesLevel3.get("key15"));

        // trying to get a variable of a child scope in parent scope
        assertEquals(null, variablesLevel1.get(key12));
    }

    @Test
    void put() {

        assertEquals(null, variablesLevel1.put("key4", "value4"));
        assertEquals("value4", variablesLevel1.get("key4"));
        assertEquals("value4", variablesLevel1.put("key4", "value4new"));


        try {
            variablesLevel2.put("key8", "value8");
            fail("Should have thrown an exception");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }

        assertEquals("value1", variablesLevel3.put(key1, "value1new"));
        assertEquals("value1new", variablesLevel3.get(key1));
        assertEquals(null, variablesLevel3.put("key14", "value14"));
        assertEquals("value14", variablesLevel3.get("key14"));
    }

    @Test
    void remove() {
        assertEquals(value1, variablesLevel1.remove(key1));
        assertEquals(null, variablesLevel1.get(key1));
        assertEquals(null, variablesLevel2.get(key1));
        assertEquals(null, variablesLevel3.get(key1));
        // can't remove the following because it is in a parent level
        assertEquals(null, variablesLevel3.remove(key2));
        assertEquals(value2, variablesLevel1.get(key2));
        assertEquals(value2, variablesLevel2.get(key2));
        assertEquals(value2, variablesLevel3.get(key2));


        assertEquals(null, variablesLevel1.remove(key12));

        // can't see the variables in child scope anyway
        assertEquals(null, variablesLevel1.get(key12));
        assertEquals(value12, variablesLevel2.get(key12));
        assertEquals(value12, variablesLevel3.get(key12));
    }

    @Test
    void putAll() {
        Map<String, String> newValueMap = new HashMap<>();
        newValueMap.put(key1, "value1new");
        newValueMap.put(key12, "value12new");
        newValueMap.put(key23, "value23new");


        variablesLevel3.putAll(newValueMap);

        assertEquals("value1new", variablesLevel1.get(key1));
        assertEquals(value2, variablesLevel1.get(key2));
        assertEquals(value3, variablesLevel1.get(key3));

        assertEquals("value1new", variablesLevel2.get(key1));
        assertEquals(value2, variablesLevel2.get(key2));
        assertEquals(value3, variablesLevel2.get(key3));

        assertEquals("value1new", variablesLevel3.get(key1));
        assertEquals(value2, variablesLevel3.get(key2));
        assertEquals(value3, variablesLevel3.get(key3));


        assertEquals("value12new", variablesLevel2.get(key12));

        assertEquals("value12new", variablesLevel3.get(key12));

        assertEquals("value23new", variablesLevel3.get(key23));
    }

    @Test
    void clear() {
        variablesLevel3.clear();
        assertNull(variablesLevel3.get(key21));
        assertNull(variablesLevel3.get(key22));
        assertNull(variablesLevel3.get(key23));
        assertEquals(6, variablesLevel3.size());
        variablesLevel1.clear();
        assertNull(variablesLevel3.get(key1));
        assertNull(variablesLevel3.get(key2));
        assertNull(variablesLevel3.get(key3));
        assertEquals(3, variablesLevel3.size());
    }

    @Test
    void keySet() {
        assertTrue(variablesLevel3.keySet().containsAll(Arrays.asList(key1, key2, key3, key11, key12, key13, key21, key22, key23)));
        assertEquals(9, variablesLevel3.keySet().size());
        assertTrue(variablesLevel3.keySet().containsAll(Arrays.asList(key1, key2, key3, key11, key12, key13)));
        assertEquals(6, variablesLevel2.keySet().size());
        assertTrue(variablesLevel3.keySet().containsAll(Arrays.asList(key1, key2, key3)));
        assertEquals(3, variablesLevel1.keySet().size());

    }

    @Test
    void values() {
        assertTrue(variablesLevel3.values().containsAll(Arrays.asList(value1, value2, value3, value11, value12, value13, value21, value22, value23)));
        assertEquals(9, variablesLevel3.values().size());
        assertTrue(variablesLevel3.values().containsAll(Arrays.asList(value1, value2, value3, value11, value12, value13)));
        assertEquals(6, variablesLevel2.values().size());
        assertTrue(variablesLevel3.values().containsAll(Arrays.asList(value1, value2, value3)));
        assertEquals(3, variablesLevel1.values().size());
    }

    @Test
    void entrySet() {
        Map<String, String> referenceMap = new HashMap<>();
        referenceMap.put(key1, value1);
        referenceMap.put(key2, value2);
        referenceMap.put(key3, value3);
        referenceMap.put(key11, value11);
        referenceMap.put(key12, value12);
        referenceMap.put(key13, value13);

        assertTrue(referenceMap.keySet().containsAll(variablesLevel2.keySet()));

        assertEquals(9, variablesLevel3.entrySet().size());
        assertEquals(6, variablesLevel2.entrySet().size());
        assertEquals(3, variablesLevel1.entrySet().size());

    }

    @Test
    void createSnapshot() throws Exception {
        WorkflowVariables<String, String> snapshot = variablesLevel3.createSnapshot();
        for (Map.Entry<String, String> entry : variablesLevel3.entrySet()) {
            assertEquals(entry.getValue(), snapshot.get(entry.getKey()));
        }

        // Change value in original workflowVariables
        variablesLevel3.put(key2, "modifiedvalue2");
        variablesLevel3.put(key13, "modifiedvalue13");
        variablesLevel3.put(key21, "modifiedvalue21");

        assertEquals(value2, snapshot.get(key2));
        assertEquals(value13, snapshot.get(key13));
        assertEquals(value21, snapshot.get(key21));

    }

}