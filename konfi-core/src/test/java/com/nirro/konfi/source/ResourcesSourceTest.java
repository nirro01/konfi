package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ResourcesSourceTest {

    @Test
    void read() {
        var source = Sources.newResourcesSource("my.properties");
        var properties = source.get();
        Assertions.assertEquals("a", properties.getProperty("value"));
        Assertions.assertNull(properties.getProperty("missing"));
    }

    @Test
    void readMissingResource() {
        var resourcesSource = Sources.newResourcesSource("missing.properties");
        Assertions.assertThrows(SourceAccessException.class, resourcesSource::get);
    }
}
