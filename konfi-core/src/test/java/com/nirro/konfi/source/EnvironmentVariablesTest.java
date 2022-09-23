package com.nirro.konfi.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnvironmentVariablesTest {

    @Test
    void read() {
        var source = PropertiesSources.newEnvironmentVariablesSource();
        var properties = source.get();
        Assertions.assertEquals(System.getenv(), properties);
    }
}
