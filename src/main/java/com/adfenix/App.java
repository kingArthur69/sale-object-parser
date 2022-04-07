package com.adfenix;

import com.adfenix.consumers.MockSaleObjectConsumer;
import com.adfenix.converters.FileToSaleObjectConverter;
import com.adfenix.models.SaleObject;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

public class App {
    public static void main(String[] args) {
        SaleObjectReporter saleObjectReporter = new SaleObjectReporter(new MockSaleObjectConsumer());

        for (String fileName : args) {
            try {
                File file = Paths.get(fileName).toFile();

                List<SaleObject> saleObjects = FileToSaleObjectConverter.convert(file);

                saleObjectReporter.reportObjects(saleObjects);
            } catch (Exception e) {
                System.out.println(ExceptionUtils.getStackTrace(e));
            }
            /*catch (FileNotFoundException e) {
                System.out.println("File not found: " +  e.getMessage());
            }*/
        }
    }
}
