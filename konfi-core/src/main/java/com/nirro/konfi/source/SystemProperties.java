package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Properties;

/**
 * System properties source
 */
public class SystemProperties implements PropertiesSource {

    /**
     * Creates a new SystemPropertiesSupplier
     */
    public SystemProperties() {
        // Do nothing
    }

    @Override
    public Properties get() throws SourceAccessException {
        return System.getProperties();
    }
}
