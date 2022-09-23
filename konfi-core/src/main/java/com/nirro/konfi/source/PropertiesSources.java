package com.nirro.konfi.source;

import java.util.Map;

/**
 * Factory methods for all kind of property sources
 */
public class PropertiesSources {
    private PropertiesSources() {
        super();
    }

    /**
     * creates a new environment variables source
     * @return Environment variables source
     */
    public static PropertiesSource newEnvironmentVariablesSource() {
        return new EnvironmentVariables();
    }

    /**
     * creates a new system properties source
     * @return System properties source
     */
    public static PropertiesSource newSystemPropertiesSource() {
        return new SystemProperties();
    }

    /**
     * creates a new file properties source
     * @param path file path
     * @return File properties source
     */
    public static PropertiesSource newExternalFileSource(String path) {
        return new ExternalPropertiesFile(path);
    }

    /**
     * creates a new Map properties source
     * @param propertiesMap properties map
     * @return Map source
     */
    public static PropertiesSource newMapSource(Map<String, String> propertiesMap) {
        return new PropertiesMap(propertiesMap);
    }

    /**
     * creates a new resources properties source
     * @param path resource path
     * @return Resources properties source
     */
    public static PropertiesSource newInternalFileSource(String path) {
        return new InternalPropertiesFile(path);
    }
}
