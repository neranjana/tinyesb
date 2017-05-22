package org.tinyesb.codec.yaml;

import org.tinyesb.codec.DecodeException;
import org.tinyesb.codec.EncodeException;
import org.tinyesb.codec.Java2TextCodec;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by neran on 22/05/2017.
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


    @org.junit.jupiter.api.Test
    void encode() throws EncodeException {

        Java2TextCodec codec = new Java2YamlCodec();
        String output = codec.encode(createTestPerson());
        assertEquals(YAML_STRING, output);


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

}