package com.mihaliov_arthur.converters;

import com.mihaliov_arthur.models.SaleObject;
import com.mihaliov_arthur.readers.Reader;
import com.mihaliov_arthur.readers.ReaderUtils;

import java.io.File;
import java.util.List;

public class FileToSaleObjectConverter {

    public static List<SaleObject> convert(File file) {
        Reader<SaleObject> reader = ReaderUtils.createReader(file.getName());
        return reader.readAll(file);
    }
}
