package com.idefix.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class FileWriterUtils {

    private FileWriterUtils() {
    }

    public static void writeProductInfo(String productName, String productPrice) {
        Path outputPath = Path.of(ConfigReader.get("output.product.file"));
        try {
            Files.createDirectories(outputPath.getParent());
            String content = "Product Name: " + productName + System.lineSeparator()
                    + "Product Price: " + productPrice + System.lineSeparator();
            Files.writeString(outputPath, content, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            throw new IllegalStateException("Product information could not be written to file.", exception);
        }
    }
}
