package com.adfenix.readers.csv;

import com.adfenix.models.SaleObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvReaderImplTest {

    private List<SaleObject> saleObjects;

    @BeforeEach
    void setUp() {
        CsvReaderImpl csvReader = new CsvReaderImpl();

        saleObjects = csvReader.readAll(Paths.get("src/test/resources/SaleObjectData/SaleObjects.csv").toFile());
    }

    @ParameterizedTest
    @CsvSource(value = {
           "0;70;69857143;Stockholm;Försäljningsgatan 14;2;",
           "1;160;50625000;Stockholm;Lidingövägen 121;",
           "2;430;23023256;München;Hohenzollernstraße11;",
           "3;19;134210526;Stockholm;Ringvägen 3;0;",
           "4;95;32631579;Umeå;Universitetsgränd5;4;"
    }, delimiter = ';')
    void parseTest(int index, int meters, String pricePerMeter, String city, String street, Integer floor) {
        assertEquals(5, saleObjects.size());
        SaleObject object = saleObjects.get(index);
        assertAll(
                () ->assertEquals(meters, object.getSquareMeters()),
                () ->assertEquals(pricePerMeter, object.getPricePerSquareMeter()),
                () ->assertEquals(city, object.getCity()),
                () ->assertEquals(street, object.getStreet()),
                () ->assertEquals(floor, object.getFloor())
        );
    }
}