package com.nirro.konfi.source;

import java.util.Map;

/**
 * Factory methods for all kind of sources
 */
public class Sources {
    private Sources() {
        super();
    }

    /**
     * @return Environment variables source
     */
    public static Source newEnvironmentVariablesSource() {
        return new EnvironmentVariablesSource();
    }

    /**
     * @return System properties source
     */
    public static Source newSystemPropertiesSource() {
        return new StaticSource(new SystemPropertiesSource());
    }

    /**
     * @param path file path
     * @return File properties source
     */
    public static Source newFileSource(String path) {
        return new FileSource(path);
    }

    /**
     * @param propertiesMap properties map
     * @return Map source
     */
    public static Source newMapSource(Map<String, String> propertiesMap) {
        return new MapSource(propertiesMap);
    }

    /**
     * @param path resource path
     * @return Resources properties source
     */
    public static Source newResourcesSource(String path) {
        return new StaticSource(new ResourcesSource(path));
    }
}
