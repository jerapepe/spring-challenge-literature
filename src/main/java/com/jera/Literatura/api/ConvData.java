package com.jera.Literatura.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvData implements Data{
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T getData(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
