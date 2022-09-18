package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourcesSource implements Source {

    private final String path;

    public ResourcesSource(String path) {
        this.path = path;
    }

    @Override
    public Properties get() throws SourceAccessException {
        Properties properties = new Properties();
        try (var inputStream = getFileFromResourceAsStream(path)) {
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new SourceAccessException("Failed to read resource " + path ,e);
        }
    }

    private InputStream getFileFromResourceAsStream(String fileName) {

        // The class loader that loaded the class
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null) {
            throw new SourceAccessException("resource not found: " + fileName);
        } else {
            return inputStream;
        }

    }


}
