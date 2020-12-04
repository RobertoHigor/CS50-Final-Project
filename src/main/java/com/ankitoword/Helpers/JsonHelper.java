package com.ankitoword.Helpers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonHelper {

    //https://gist.github.com/rifat159/88d70d1d07e2594af3dd67bfd9d11767
    public static <T> T toObject(String value, Class<T> modelClass) {
        ObjectMapper mapper = new ObjectMapper();
        // Ao adicionar um cartão, o resultado é uma string, e não um array.
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        try {
            return mapper.readValue(value, modelClass);
        } catch (JsonProcessingException e) {            
            e.printStackTrace();
        }
        return null;
    }
}
