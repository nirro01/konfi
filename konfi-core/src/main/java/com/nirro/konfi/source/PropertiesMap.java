package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Map;
import java.util.Properties;

/**
 * Map properties source
 */
public class PropertiesMap implements PropertiesSource {

    private final Map<String, String> map;

    /**
     * Construct a new MapSource
     * @param map properties map
     */
    public PropertiesMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public Properties get() throws SourceAccessException {
        Properties properties = new Properties();
        properties.putAll(map);
        return properties;
    }
}
