package com.nirro.konfi.source;

import java.util.Map;

public class Sources {
    private Sources() {
        super();
    }

    public static Source newEnvironmentVariablesSource() {
        return new EnvironmentVariablesSource();
    }

    public static Source newSystemPropertiesSource() {
        return new StaticSource(new SystemPropertiesSource());
    }

    public static Source newFileSource(String path) {
        return new FileSource(path);
    }

    public static Source newMapSource(Map<String, String> propertiesMap) {
        return new MapSource(propertiesMap);
    }

    public static Source newResourcesSource(String path) {
        return new StaticSource(new ResourcesSource(path));
    }
}
