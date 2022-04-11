package com.adfenix.readers;

import com.adfenix.models.SaleObject;
import com.adfenix.readers.csv.CsvReaderImpl;
import com.adfenix.readers.json.JsonReaderImpl;
import org.apache.commons.io.FilenameUtils;

public class ReaderUtils {

    public static Reader<SaleObject> createReader(String fileName) {
        String extension = FilenameUtils.getExtension(fileName);

        if("csv".equals(extension)) {
            return new CsvReaderImpl();
        } else if("json".equals(extension)){
            return new JsonReaderImpl();
        } else {
            throw new IllegalArgumentException("Extension is not supported, fileName: " + fileName);
        }
    }
}
