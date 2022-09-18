package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileSource implements Source {

    private final String path;

    public FileSource(String path) {
        this.path = path;
    }

    @Override
    public Properties get() throws SourceAccessException {
        Properties properties = new Properties();
        try (var fis = new FileInputStream(path)) {
            properties.load(fis);
            return properties;
        } catch (IOException e) {
            throw new SourceAccessException("Failed to read resource " + path, e);
        }
    }

}
