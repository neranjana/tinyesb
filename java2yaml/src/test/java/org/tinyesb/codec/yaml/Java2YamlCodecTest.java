package org.tinyesb.codec.yaml;

import org.tinyesb.codec.DecodeException;
import org.tinyesb.codec.EncodeException;
import org.tinyesb.codec.Java2TextCodec;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
class Java2YamlCodecTest {

    private static final String YAML_STRING = "---\n" +
            "firstName: \"John\"\n" +
            "lastName: \"Smith\"\n" +
            "age: 50\n" +
            "children:\n" +
            "- firstName: \"Sally\"\n" +
            "  lastName: \"Smith\"\n" +
            "  age: 25\n" +
            "  children: []\n" +
            "  phoneNumbers: {}\n" +
            "- firstName: \"Tom\"\n" +
            "  lastName: \"Smit\"\n" +
            "  age: 20\n" +
            "  children: []\n" +
            "  phoneNumbers: {}\n" +
            "phoneNumbers:\n" +
            "  mobile: \"0487654321\"\n" +
            "  home: \"0312345678\"\n";

    private static final String MAP_YAML_STRING = "---\n" +
            "key1: \"value1\"\n" +
            "key2: \"value2\"\n" +
            "firstMap:\n" +
            "  firstMapList1:\n" +
            "  - \"listitem1\"\n" +
            "  - \"listitem2\"\n" +
            "  - \"listitem3\"\n" +
            "  firstMapKey2: \"firstMapValue2\"\n" +
            "  secondMap:\n" +
            "    secondMapKey3: \"secondMapValue3\"\n" +
            "    secondMapKey1: \"secondMapValue1\"\n" +
            "    secondMapKey2: \"secondMapValue2\"\n" +
            "  firstMapKey1: \"firstMapValue1\"\n";

    @org.junit.jupiter.api.Test
    void encode() throws EncodeException {

        Java2TextCodec codec = new Java2YamlCodec();
        String output = codec.encode(createTestPerson());
        assertEquals(YAML_STRING, output);
        System.out.println(output);


    }

    @org.junit.jupiter.api.Test
    void decode() throws DecodeException {
        Java2TextCodec codec = new Java2YamlCodec();
        Person person = codec.decode(YAML_STRING, Person.class);
        assertEquals(createTestPerson(), person);

    }

    private Person createTestPerson() {
        Person john = new Person("John", "Smith", 50);
        Person sally = new Person("Sally", "Smith", 25);
        Person tom = new Person("Tom", "Smit", 20);
        john.addChild(sally);
        john.addChild(tom);
        john.addPhoneNumber("home", "0312345678");
        john.addPhoneNumber("mobile", "0487654320");

        return john;
    }

    @Test
    void encodeMap() throws EncodeException, DecodeException {
        Java2TextCodec codec = new Java2YamlCodec();
        Map<Object, Object> topMap = new HashMap<>();
        topMap.put("key1", "value1");
        Map<Object, Object> firstMap = new HashMap<>();
        firstMap.put("firstMapKey1", "firstMapValue1");
        firstMap.put("firstMapKey2", "firstMapValue2");
        firstMap.put("firstMapList1", Arrays.asList("listitem1", "listitem2", "listitem3"));
        Map<Object, Object> secondMap = new HashMap<>();
        secondMap.put("secondMapKey1", "secondMapValue1");
        secondMap.put("secondMapKey2", "secondMapValue2");
        secondMap.put("secondMapKey3", "secondMapValue3");
        firstMap.put("secondMap", secondMap);
        topMap.put("firstMap", firstMap);
        topMap.put("key2", "value2");

        String yaml = codec.encode(topMap);
        assertEquals(MAP_YAML_STRING, yaml);

        Object object = codec.decode(MAP_YAML_STRING, Object.class);
        assertEquals(topMap, object);
    }
}