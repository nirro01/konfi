package com.nirro.konfi.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SystemPropertiesSourceTest {

    @Test
    void read() {
        var source = Sources.newSystemPropertiesSource();
        var properties = source.get();
        Assertions.assertEquals(System.getProperties(), properties);
    }
}
