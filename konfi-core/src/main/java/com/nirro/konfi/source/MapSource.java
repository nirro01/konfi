package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Map;
import java.util.Properties;

/**
 * Map properties source
 */
public class MapSource implements Source {

    private final Map<String, String> propertiesMap;

    /**
     * Construct a new MapSource
     * @param propertiesMap properties map
     */
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
