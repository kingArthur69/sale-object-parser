package com.mihaliov_arthur.readers.csv;

import com.mihaliov_arthur.models.SaleObject;
import com.mihaliov_arthur.readers.Reader;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CsvReaderImpl implements Reader<SaleObject> {

    private static final CSVParser PARSER = new CSVParserBuilder()
            .withSeparator(';')
            .build();

    @Override
    public List<SaleObject> readAll(File file) {
        try (CSVReader reader = new CSVReaderBuilder(new FileReader(file))
                .withSkipLines(1)
                .withCSVParser(PARSER)
                .build()
        ) {
            List<String[]> strings = reader.readAll();

            return strings.stream()
                    .map(line -> isValid(line) ? createSaleObject(line) : null)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private boolean isValid(String[] line) {
        return line.length == 6 || line.length == 7;
    }

    private SaleObject createSaleObject(String[] line) {
        SaleObject saleObject = new SaleObject();

        int squareMeters = Integer.parseInt(line[1]);
        saleObject.setSquareMeters(squareMeters);

        String pricePerSquareMeter = new BigDecimal(line[2])
                .divide(BigDecimal.valueOf(squareMeters),3, RoundingMode.HALF_UP)
                .toPlainString().replace(".", "");
        saleObject.setPricePerSquareMeter(pricePerSquareMeter);

        saleObject.setCity(line[3]);
        saleObject.setStreet(line[4]);

        if(StringUtils.equals(line[0], "A")) {
            saleObject.setFloor(Integer.valueOf(line[5]));
        }

        return saleObject;
    }
}
