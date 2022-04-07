package com.adfenix.converters;

import com.adfenix.models.SaleObject;
import com.adfenix.readers.Reader;
import com.adfenix.readers.ReaderUtils;

import java.io.File;
import java.util.List;

public class FileToSaleObjectConverter implements Converter<SaleObject> {

    private final Reader<SaleObject> reader;
    private File file;

    public FileToSaleObjectConverter(File file) {
        this.file = file;
        this.reader = ReaderUtils.createReader(file.getName());
    }

    public List<SaleObject> convert() {
        return reader.readAll(file);
    }
}
