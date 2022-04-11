package com.mihaliov_arthur.readers.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihaliov_arthur.models.SaleObject;
import com.mihaliov_arthur.readers.Reader;

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
