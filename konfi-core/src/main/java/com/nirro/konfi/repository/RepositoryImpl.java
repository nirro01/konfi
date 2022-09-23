package com.nirro.konfi.repository;

import com.nirro.konfi.source.PropertiesSource;

import java.util.Properties;

/**
 * Thread safe repository that replace the properties
 * on every refresh
 */
public class RepositoryImpl implements Repository {

    private Properties properties;

    private final PropertiesSource propertiesSource;

    /**
     * Construct a new Repository
     * @param propertiesSource properties source
     */
    public RepositoryImpl(PropertiesSource propertiesSource) {
        this.propertiesSource = propertiesSource;
    }


    @Override
    public void refresh() {
        properties = propertiesSource.get();
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
