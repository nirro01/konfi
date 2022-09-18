package com.nirro.konfi.source;

import com.nirro.konfi.exception.SourceAccessException;

import java.util.Properties;

public class SystemPropertiesSource implements Source {

    @Override
    public Properties get() throws SourceAccessException {
        return System.getProperties();
    }
}
