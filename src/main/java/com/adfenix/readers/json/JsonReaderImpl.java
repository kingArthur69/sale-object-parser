package com.adfenix.readers.json;

import com.adfenix.models.SaleObject;
import com.adfenix.readers.Reader;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class JsonReaderImpl implements Reader<SaleObject> {

    @Override
    public List<SaleObject> readAll(File file) {

        try {
            ObjectMapper mapper = createMapper();
            JsonSaleObjects jsonSaleObjects = mapper.readValue(file, JsonSaleObjects.class);
            return jsonSaleObjects.getSaleObjects();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static ObjectMapper createMapper() {
        return new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}
