package com.idefix.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idefix.models.TestData;

import java.io.IOException;
import java.io.InputStream;

public final class JsonDataReader {

    private JsonDataReader() {
    }

    public static TestData readTestData() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = JsonDataReader.class.getClassLoader().getResourceAsStream("test-data.json")) {
            if (inputStream == null) {
                throw new IllegalStateException("test-data.json file could not be found.");
            }
            return objectMapper.readValue(inputStream, TestData.class);
        } catch (IOException exception) {
            throw new IllegalStateException("test-data.json file could not be read.", exception);
        }
    }
}
