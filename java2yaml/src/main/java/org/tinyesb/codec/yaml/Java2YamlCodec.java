package org.tinyesb.codec.yaml;

import org.tinyesb.codec.DecodeException;
import org.tinyesb.codec.EncodeException;
import org.tinyesb.codec.Java2TextCodec;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
public class Java2YamlCodec implements Java2TextCodec {

    ObjectMapper mapper;

    public Java2YamlCodec() {
        this.mapper = new ObjectMapper(new YAMLFactory());
    }


    @Override
    public String encode(Object object) throws EncodeException {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new EncodeException(e);
        }
    }

    @Override
    public <T> T decode(String objectString, Class<T> clazz) throws DecodeException {
        try {
            return mapper.readValue(objectString, clazz);
        } catch (IOException e) {
            throw new DecodeException(e);
        }
    }
}
