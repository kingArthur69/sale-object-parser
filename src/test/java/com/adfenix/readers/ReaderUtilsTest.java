package com.adfenix.readers;

import com.adfenix.readers.csv.CsvReaderImpl;
import com.adfenix.readers.json.JsonReaderImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ReaderUtilsTest {

    private static Stream<Arguments> createReaderSupplier() {
        return Stream.of(
                Arguments.of("test.csv", CsvReaderImpl.class),
                Arguments.of("test.json", JsonReaderImpl.class)
        );
    }

    @ParameterizedTest
    @MethodSource("createReaderSupplier")
    void createReaderTest(String fileName, Class clazz) {
        assertEquals(clazz, ReaderUtils.createReader(fileName).getClass());
    }

    @ParameterizedTest
    @CsvSource(value = {
            "test.xml;Extension is not supported, fileName: test.xml",
            "test;Extension is not supported, fileName: test"
    }, delimiter = ';')
    void createReaderFailTest(String fileName, String message) {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> ReaderUtils.createReader(fileName));
        assertEquals(message, exception.getMessage());
    }
}