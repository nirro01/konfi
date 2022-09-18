package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InternalPropertiesFileTest {

    @Test
    void read() {
        var source = PropertiesSources.newInternalFileSource("my.properties");
        var properties = source.get();
        Assertions.assertEquals("a", properties.getProperty("value"));
        Assertions.assertNull(properties.getProperty("missing"));
    }

    @Test
    void readMissingResource() {
        var resourcesSource = PropertiesSources.newInternalFileSource("missing.properties");
        Assertions.assertThrows(SourceAccessException.class, resourcesSource::get);
    }
}
