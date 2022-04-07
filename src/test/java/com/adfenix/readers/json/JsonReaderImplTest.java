package com.adfenix.readers.json;

import com.adfenix.models.SaleObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonReaderImplTest {

    private List<SaleObject> saleObjects;

    @BeforeEach
    void setUp() {
        JsonReaderImpl csvReader = new JsonReaderImpl();

        saleObjects = csvReader.readAll(Paths.get("src/test/resources/SaleObjectData/SaleObjects.json").toFile());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "0;190;18421053;Stockholm;DuperStreet 5;",
            "1;28;171642821;New York;Crampedstreet 5;18",
            "2;55;141927255;New York;Crampedstreet 9A;18",
            "3;105;30476190;Uppsala;Universistetsgatan 104;2",
    }, delimiter = ';')
    void parseTest(int index, int meters, String pricePerMeter, String city, String street, Integer floor) {
        assertEquals(4, saleObjects.size());
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