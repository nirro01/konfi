package com.nirro.konfi.repository;

import com.nirro.konfi.source.Source;

import java.util.Properties;

public class RepositoryImpl implements Repository {

    private Properties properties;

    private final Source supplier;

    public RepositoryImpl(Source supplier) {
        this.supplier = supplier;
    }


    @Override
    public void refresh() {
        properties = supplier.get();
    }

    @Override
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
