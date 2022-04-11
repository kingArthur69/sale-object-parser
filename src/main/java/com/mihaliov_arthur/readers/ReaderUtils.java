package com.mihaliov_arthur.readers;

import com.mihaliov_arthur.models.SaleObject;
import com.mihaliov_arthur.readers.csv.CsvReaderImpl;
import com.mihaliov_arthur.readers.json.JsonReaderImpl;
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
