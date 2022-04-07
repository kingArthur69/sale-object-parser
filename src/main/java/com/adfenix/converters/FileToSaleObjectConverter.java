package com.adfenix.converters;

import com.adfenix.models.SaleObject;
import com.adfenix.readers.Reader;
import com.adfenix.readers.ReaderUtils;

import java.io.File;
import java.util.List;

public class FileToSaleObjectConverter {

    public static List<SaleObject> convert(File file) {
        Reader<SaleObject> reader = ReaderUtils.createReader(file.getName());
        return reader.readAll(file);
    }
}
