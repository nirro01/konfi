package com.nirro.konfi.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

class MapSourceTest {

    @Test
    void read() {
        var source = Sources.newMapSource(Map.of("value", "a"));
        var properties = source.get();
        Assertions.assertEquals("a", properties.getProperty("value"));
        Assertions.assertNull(properties.getProperty("missing"));
    }
}
