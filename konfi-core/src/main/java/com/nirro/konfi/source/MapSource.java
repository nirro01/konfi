package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Map;
import java.util.Properties;

public class MapSource implements Source {

    private final Map<String, String> propertiesMap;

    public MapSource(Map<String, String> propertiesMap) {
        this.propertiesMap = propertiesMap;
    }

    @Override
    public Properties get() throws SourceAccessException {
        Properties properties = new Properties();
        properties.putAll(propertiesMap);
        return properties;
    }
}
