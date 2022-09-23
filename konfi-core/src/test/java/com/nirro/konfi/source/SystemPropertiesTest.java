package com.nirro.konfi.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SystemPropertiesTest {

    @Test
    void read() {
        var source = PropertiesSources.newSystemPropertiesSource();
        var properties = source.get();
        Assertions.assertEquals(System.getProperties(), properties);
    }
}
