package com.consultora.TabelaFipe.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert{
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T getData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }
}
