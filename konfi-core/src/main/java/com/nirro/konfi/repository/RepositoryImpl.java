package com.nirro.konfi.repository;

import com.nirro.konfi.source.Source;

import java.util.Properties;

/**
 * Thread safe repository that replace the properties
 * on every refresh
 */
public class RepositoryImpl implements Repository {

    private Properties properties;

    private final Source source;

    /**
     * @param source properties source
     */
    public RepositoryImpl(Source source) {
        this.source = source;
    }


    @Override
    public void refresh() {
        properties = source.get();
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
