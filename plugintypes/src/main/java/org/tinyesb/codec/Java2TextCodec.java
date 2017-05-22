package org.tinyesb.codec;

/**
 * Created by Neranjana Karunaratne on 22/05/2017.
 */
public interface Java2TextCodec {

    String encode(Object object) throws EncodeException;

    <T> T decode(String objectString, Class<T> clazz) throws DecodeException;
}
