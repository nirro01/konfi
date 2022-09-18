package com.nirro.konfi.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnvironmentVariablesSourceTest {

    @Test
    void read() {
        var source = Sources.newEnvironmentVariablesSource();
        var properties = source.get();
        Assertions.assertEquals(System.getenv(), properties);
    }
}
