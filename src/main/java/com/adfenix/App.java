package com.adfenix;

import com.adfenix.consumers.MockSaleObjectconsumer;
import com.adfenix.converters.Converter;
import com.adfenix.converters.FileToSaleObjectConverter;
import com.adfenix.models.SaleObject;

import java.io.File;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SaleObjectReporter saleObjectReporter = new SaleObjectReporter(new MockSaleObjectconsumer());

        File file = new File("");
        Converter<SaleObject> converter = new FileToSaleObjectConverter(file);
        List<SaleObject> saleObjects = converter.convert();

        saleObjectReporter.reportObjects(saleObjects);
    }
}
