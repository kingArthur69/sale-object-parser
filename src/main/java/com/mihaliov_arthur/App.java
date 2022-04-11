package com.mihaliov_arthur;

import com.mihaliov_arthur.consumers.MockSaleObjectConsumer;
import com.mihaliov_arthur.converters.FileToSaleObjectConverter;
import com.mihaliov_arthur.models.SaleObject;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
public class App {
    public static void main(String[] args) {
        SaleObjectReporter saleObjectReporter = new SaleObjectReporter(new MockSaleObjectConsumer());

        for (String fileName : args) {
            try {
                File file = Paths.get(fileName).toFile();

                List<SaleObject> saleObjects = FileToSaleObjectConverter.convert(file);

                saleObjectReporter.reportObjects(saleObjects);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
