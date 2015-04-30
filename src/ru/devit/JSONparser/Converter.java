package ru.devit.JSONparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by user on 23.04.2015.
 */
public class Converter {

    public static Request toJavaObject(String str) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue( str, Request.class );
    }

    public static String toJSON(Request request) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(request);
    }

}
